package com.dearnewyear.dny.common.jwt;

import com.dearnewyear.dny.common.error.ErrorCode;
import com.dearnewyear.dny.common.error.exception.CustomException;
import com.dearnewyear.dny.user.domain.User;
import com.dearnewyear.dny.user.repository.UserRepository;
import com.dearnewyear.dny.user.dto.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class JwtTokenProvider {

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.access-token-expire-ms}")
    private long accessTokenExpireMs;

    @Value("${jwt.refresh-token-expire-ms}")
    private long refreshTokenExpireMs;

    @Value("${auth.header.authorization}")
    private String authHeader;

    @Value("${auth.header.refresh}")
    private String refreshHeader;

    private final UserRepository userRepository;

    private Key getSignKey(String secretKey) {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    private String createToken(User user, long ms) {
        Claims claims = Jwts.claims();
        Date now = new Date();
        Key key = getSignKey(secretKey);

        claims
                .setSubject(user.getUserId())
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + ms));

        claims.put("userId", user.getUserId());
        claims.put("userName", user.getUserName());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(key)
                .compact();
    }

    public String createAccessToken(User user) {
        return createToken(user, accessTokenExpireMs);
    }

    public String createRefreshToken(User user) {
        return createToken(user, refreshTokenExpireMs);
    }

    public String renewAccessToken(String refreshToken) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSignKey(secretKey))
                .build()
                .parseClaimsJws(refreshToken)
                .getBody();

        String userId = claims.get("userId", String.class);
        String userName = claims.get("userName", String.class);

        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent() && userName.equals(user.get().getUserName())) {
            return createAccessToken(user.get());
        } else {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }
    }

    public String getAccessToken(HttpServletRequest request) {
        String accessToken = request.getHeader(authHeader);
        if (accessToken == null) {
            return null;
        } else {
            return accessToken.substring(7);
        }
    }

    public String getRefreshToken(HttpServletRequest request) {
        return request.getHeader(refreshHeader);
    }

    public boolean validateToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getSignKey(secretKey))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }
    }

    public String getUserId(String token) {
        JwtParser parser = Jwts.parserBuilder()
                .setSigningKey(getSignKey(secretKey))
                .build();
        return parser.parseClaimsJws(token).getBody().get("userId", String.class);
    }

    public Authentication getAuthentication(String token) {
        User user = userRepository.findById(getUserId(token))
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        UserDetails userDetails = new CustomUserDetails(user);
        return new PreAuthenticatedAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}
