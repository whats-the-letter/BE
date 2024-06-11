package com.dearnewyear.dny.common.jwt;

import com.dearnewyear.dny.common.error.ErrorCode;
import com.dearnewyear.dny.common.error.CustomException;
import com.dearnewyear.dny.user.domain.User;
import com.dearnewyear.dny.user.repository.UserRepository;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Collections;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
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

	@Value("${auth.header.prefix}")
	private String authHeaderPrefix;

	@Value("${auth.header.refresh}")
	private String refreshHeader;

	private final UserRepository userRepository;
	private Key key;
	private JwtParser jwtParser;

	@PostConstruct
	public void init() {
		key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
		jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
	}

	public String createAccessToken(User user) {
		return createToken(user, accessTokenExpireMs);
	}

	public String createRefreshToken(User user) {
		return createToken(user, refreshTokenExpireMs);
	}

	private String createToken(User user, long ms) {
		Date now = new Date();
		Date expiration = new Date(now.getTime() + ms);

		return Jwts.builder()
			.setSubject(user.getUserId())
			.setIssuedAt(now)
			.setExpiration(expiration)
			.claim("userId", user.getUserId())
			.claim("userName", user.getUserName())
			.signWith(key)
			.compact();
	}

	public Authentication getAuthentication(String token) {
		User user = userRepository.findById(getUserId(token))
			.orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
		return new PreAuthenticatedAuthenticationToken(user, null, Collections.emptyList());
	}

	public boolean validateToken(String token) {
		try {
			jwtParser.parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			throw new CustomException(ErrorCode.INVALID_TOKEN);
		}
	}

	public String getUserId(String token) {
		try {
			return jwtParser.parseClaimsJws(token).getBody().get("userId", String.class);
		} catch (Exception e) {
			throw new CustomException(ErrorCode.INVALID_TOKEN);
		}
	}

	public String getAccessToken(HttpServletRequest request) {
		String accessToken = request.getHeader(authHeader);
		if (accessToken != null && accessToken.startsWith(authHeaderPrefix + " ")) {
			return accessToken.substring(authHeaderPrefix.length() + 1);
		}
		return null;
	}

	public String getRefreshToken(HttpServletRequest request) {
		return request.getHeader(refreshHeader);
	}

	public String renewAccessToken(String refreshToken) {
		if (!validateToken(refreshToken)) {
			throw new CustomException(ErrorCode.INVALID_TOKEN);
		}

		String userId = getUserId(refreshToken);
		User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
		return createAccessToken(user);
	}
}
