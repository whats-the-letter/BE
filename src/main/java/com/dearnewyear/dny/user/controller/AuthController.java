package com.dearnewyear.dny.user.controller;

import com.dearnewyear.dny.common.dto.response.ApiResponse;
import com.dearnewyear.dny.common.dto.response.ErrorResponse;
import com.dearnewyear.dny.common.error.exception.CustomException;
import com.dearnewyear.dny.user.dto.request.SignupRequest;
import com.dearnewyear.dny.user.service.KakaoOAuth2Service;
import com.dearnewyear.dny.user.service.UserService;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final KakaoOAuth2Service kakaoOAuth2Service;
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signup(@RequestBody SignupRequest request, HttpServletResponse response) {
        try {
            userService.signupAndLoginUser(request, response);
            ApiResponse res = new ApiResponse(200, "회원가입 성공", null);
            return ResponseEntity.ok(res);
        } catch (CustomException e) {
            ErrorResponse res = ErrorResponse.of(e.getErrorCode());
            return ResponseEntity.status(e.getErrorCode().getStatus()).body(res);
        }
    }

    @GetMapping("/login/kakao")
    public RedirectView getAuthorizationCode() throws IOException {
        String authorizationUri = kakaoOAuth2Service.getAuthorizationUri();
        return new RedirectView(authorizationUri);
    }

    @GetMapping("/login/kakao/code")
    public ResponseEntity<ApiResponse> kakaoLogin(@RequestParam("code") String code, HttpServletResponse response) {
        try {
            response.setHeader("Content-Type", "application/json");
            String accessToken = kakaoOAuth2Service.getAccessToken(code);
            String email = kakaoOAuth2Service.getKakaoUser(accessToken, response);

            if (email.equals("success")) {
                ApiResponse res = new ApiResponse(200, "카카오 로그인 성공", null);
                return ResponseEntity.ok(res);
            } else {
                ApiResponse res = new ApiResponse(401, "회원가입 필요", email);
                return ResponseEntity.status(401).body(res);
            }
        } catch (CustomException e) {
            ErrorResponse res = ErrorResponse.of(e.getErrorCode());
            return ResponseEntity.status(e.getErrorCode().getStatus()).body(res);
        }
    }
}