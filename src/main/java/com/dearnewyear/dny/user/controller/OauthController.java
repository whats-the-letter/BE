package com.dearnewyear.dny.user.controller;

import com.dearnewyear.dny.common.dto.response.ApiResponse;
import com.dearnewyear.dny.common.jwt.JwtTokenProvider;
import com.dearnewyear.dny.user.domain.User;
import com.dearnewyear.dny.user.service.KakaoOAuth2Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth")
public class OauthController {

    private final JwtTokenProvider jwtTokenProvider;
    private final KakaoOAuth2Service kakaoOAuth2Service;

    @RequestMapping("/kakao")
    public ResponseEntity<ApiResponse> kakaoLogin(@RequestParam String code, HttpServletResponse response)
            throws JsonProcessingException {

        String token = kakaoOAuth2Service.getAccessToken(code);
        User kakaoUser = kakaoOAuth2Service.getKakaoUser(token);

        String accessToken = jwtTokenProvider.createAccessToken(kakaoUser);
        String refreshToken = jwtTokenProvider.createRefreshToken(kakaoUser);

        response.setHeader("Authorization", accessToken);
        response.setHeader("RefreshToken", refreshToken);

        return ResponseEntity.ok(ApiResponse.builder()
                .status(200)
                .message("카카오 로그인 성공")
                .data(null)
                .build());
    }
}
