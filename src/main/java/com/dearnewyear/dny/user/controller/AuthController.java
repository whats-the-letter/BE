package com.dearnewyear.dny.user.controller;

import com.dearnewyear.dny.common.error.exception.CustomException;
import com.dearnewyear.dny.user.dto.UserInfo;
import com.dearnewyear.dny.user.dto.request.SignupRequest;
import com.dearnewyear.dny.user.dto.response.AuthResponse;
import com.dearnewyear.dny.user.service.KakaoOAuth2Service;
import com.dearnewyear.dny.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@Api(tags = {"Auth"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final KakaoOAuth2Service kakaoOAuth2Service;
    private final UserService userService;

    @ApiOperation(value = "회원가입")
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "회원가입 성공"),
            @io.swagger.annotations.ApiResponse(code = 400, message = "회원가입 실패")
    })
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@ModelAttribute SignupRequest request, HttpServletResponse response) {
        try {
            UserInfo userInfo = userService.signupAndLoginUser(request, response);
            return ResponseEntity.ok(new AuthResponse(userInfo, null));
        } catch (CustomException e) {
            return ResponseEntity.status(e.getErrorCode().getStatus()).body(new AuthResponse(null, e.getMessage()));
        }
    }

    @ApiOperation(value = "카카오 로그인 링크로 redirect")
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "카카오 로그인 링크로 접속 성공")
    })
    @GetMapping("/login/kakao")
    public RedirectView getAuthorizationCode() {
        String authorizationUri = kakaoOAuth2Service.getAuthorizationUri();
        return new RedirectView(authorizationUri);
    }

    @ApiOperation(value = "카카오 로그인 후 얻은 코드로 DNY 로그인 !")
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "로그인 성공"),
            @io.swagger.annotations.ApiResponse(code = 404, message = "회원가입 필요"),
            @io.swagger.annotations.ApiResponse(code = 400, message = "유효하지 않은 코드")
    })
    @GetMapping("/login/kakao/code")
    public ResponseEntity<AuthResponse> kakaoLogin(@RequestParam("code") String code, HttpServletResponse response) {
        try {
            response.setHeader("Content-Type", "application/json");
            String accessToken = kakaoOAuth2Service.getAccessToken(code);
          
            UserInfo userInfo = kakaoOAuth2Service.getKakaoUser(accessToken, response);
            if (userInfo.getUserName() == null)
                return ResponseEntity.status(404).body(new AuthResponse(userInfo, "회원가입 필요"));
            return ResponseEntity.ok(new AuthResponse(userInfo, null));
        } catch (CustomException e) {
            return ResponseEntity.status(e.getErrorCode().getStatus()).body(new AuthResponse(null, e.getMessage()));
        }
    }

    @ApiOperation(value = "RefreshToken을 통한 AccessToken 갱신")
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "AccessToken 갱신 성공"),
            @io.swagger.annotations.ApiResponse(code = 401, message = "AccessToken 갱신 실패")
    })
    @PostMapping("/renew")
    public ResponseEntity<String> renewToken(@RequestParam("DNY-Refresh") String refreshToken, HttpServletResponse response) {
        try {
            userService.renewToken(refreshToken, response);
            return ResponseEntity.ok("AccessToken 갱신 성공");
        } catch (CustomException e) {
            return ResponseEntity.status(e.getErrorCode().getStatus()).body(e.getMessage());
        }
    }
}
