package com.dearnewyear.dny.user.controller;

import com.dearnewyear.dny.common.dto.response.ApiResponse;
import com.dearnewyear.dny.user.service.KakaoOAuth2Service;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth")
public class OauthController {

    private final KakaoOAuth2Service kakaoOAuth2Service;

    @GetMapping("/login/kakao")
    public RedirectView getAuthorizationCode(HttpServletResponse response) throws IOException {
        String authorizationUri = kakaoOAuth2Service.getAuthorizationUri();
        return new RedirectView(authorizationUri);
    }

    @GetMapping("/login/kakao/code")
    public ResponseEntity<ApiResponse> kakaoLogin(@RequestParam("code") String code, HttpServletResponse response) {
        String accessToken = kakaoOAuth2Service.getAccessToken(code);
        response.setHeader("Content-Type", "application/json");

        if (kakaoOAuth2Service.getKakaoUser(accessToken, response)) {
            System.out.println("AccessToken: " + response.getHeader("Authorization"));
            System.out.println("RefreshToken: " + response.getHeader("RefreshToken"));

            ApiResponse res = new ApiResponse(200, "카카오 로그인 성공", null);
            return ResponseEntity.ok(res);
        } else {
            ApiResponse res = new ApiResponse(401, "회원가입 필요", null);
            return ResponseEntity.status(401).body(res);
        }
    }
}
