package com.dearnewyear.dny.common.jwt;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        String accessToken = jwtTokenProvider.getAccessToken(request);

        log.info("accessToken: " + accessToken);
        log.info("refreshToken: " + refreshToken);

        if (accessToken != null && jwtTokenProvider.validateToken(accessToken)) {
            log.info("액세스 토큰 유효");
            setAuthentication(accessToken);
        }
        if (refreshToken != null && jwtTokenProvider.validateToken(refreshToken)) {
            log.info("리프레시 토큰 유효");
            setAuthentication(refreshToken);
        }
        filterChain.doFilter(request, response);
    }

    private void setAuthentication(String accessToken) {
        Authentication auth = jwtTokenProvider.getAuthentication(accessToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
