package com.dearnewyear.dny.common.jwt;

import com.dearnewyear.dny.common.error.ErrorCode;
import com.dearnewyear.dny.common.error.ErrorResponse;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws IOException {
        try {
            String accessToken = jwtTokenProvider.getAccessToken(request);
            if (accessToken != null && jwtTokenProvider.validateToken(accessToken)) {
                setAuthentication(accessToken);
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(new ErrorResponse(ErrorCode.INVALID_TOKEN).toString());
        }
    }

    private void setAuthentication(String accessToken) {
        Authentication auth = jwtTokenProvider.getAuthentication(accessToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
