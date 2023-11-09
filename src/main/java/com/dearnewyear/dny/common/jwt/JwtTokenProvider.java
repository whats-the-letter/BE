package com.dearnewyear.dny.common.jwt;

import com.dearnewyear.dny.common.error.ErrorCode;
import com.dearnewyear.dny.common.error.exception.CustomException;
import com.dearnewyear.dny.user.domain.RefreshToken;
import com.dearnewyear.dny.user.domain.User;
import com.dearnewyear.dny.user.repository.RefreshTokenRepository;
import com.dearnewyear.dny.user.repository.UserRepository;
import com.dearnewyear.dny.user.dto.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
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

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    private Key getSignKey(String secretKey) {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    private String createToken(User user, long ms) {
        log.info("토큰 생성 시작");
        Claims claims = Jwts.claims();
        Date now = new Date();
        Key key = getSignKey(secretKey);

        claims
                .setSubject(Long.toString(user.getUserId()))
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + ms));

        claims.put("userId", user.getUserId());
        claims.put("userName", user.getUserName());
        claims.put("email", user.getEmail());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(key)
                .compact();
    }

    public String createAccessToken(User user) {
        return createToken(user, accessTokenExpireMs);
    }

    public String createRefreshToken(User user) {
        String refreshToken = createToken(user, refreshTokenExpireMs);

        RefreshToken rt = new RefreshToken(user, refreshToken);
        refreshTokenRepository.save(rt);

        return refreshToken;
    }

    public String getAccessToken(HttpServletRequest request) {
        return request.getHeader("Access-Token");
    }

    public String getRefreshToken(HttpServletRequest request) {
        return request.getHeader("Refresh-Token");
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

    public Long getUserId(String token) {
        JwtParser parser = Jwts.parserBuilder()
                .setSigningKey(getSignKey(secretKey))
                .build();
        return parser.parseClaimsJws(token).getBody().get("userId", Long.class);
    }

    public Authentication getAuthentication(String token) {
        User user = userRepository.findById(getUserId(token))
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        UserDetails userDetails = new CustomUserDetails(user);
        return new PreAuthenticatedAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}
