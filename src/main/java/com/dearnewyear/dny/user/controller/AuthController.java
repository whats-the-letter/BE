package com.dearnewyear.dny.user.controller;

import com.dearnewyear.dny.common.error.exception.CustomException;
import com.dearnewyear.dny.user.dto.UserInfo;
import com.dearnewyear.dny.user.dto.request.LoginRequest;
import com.dearnewyear.dny.user.dto.request.SignupRequest;
import com.dearnewyear.dny.user.dto.response.UserInfoResponse;
import com.dearnewyear.dny.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"Auth"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @ApiOperation(value = "회원가입")
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 201, message = "회원가입 성공"),
            @io.swagger.annotations.ApiResponse(code = 400, message = "회원가입 실패 또는 유효성 검사 실패"),
    })
    @PostMapping("/signup")
    public ResponseEntity<UserInfoResponse> signup(@ModelAttribute @Valid SignupRequest request, HttpServletResponse response) {
        try {
            UserInfo userInfo = userService.signupAndLoginUser(request, response);
            return ResponseEntity.status(201).body(new UserInfoResponse(userInfo, null));
        } catch (CustomException e) {
            return ResponseEntity.status(e.getErrorCode().getStatus()).body(new UserInfoResponse(null, e.getMessage()));
        }
    }

    @ApiOperation(value = "로그인")
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "로그인 성공"),
            @io.swagger.annotations.ApiResponse(code = 404, message = "회원가입 필요")
    })
    @PostMapping("/login")
    public ResponseEntity<UserInfoResponse> login(@RequestBody @Valid LoginRequest request, HttpServletResponse response) {
        try {
            UserInfo userInfo = userService.loginUser(request, response);
            return ResponseEntity.ok(new UserInfoResponse(userInfo, null));
        } catch (CustomException e) {
            return ResponseEntity.status(e.getErrorCode().getStatus()).body(new UserInfoResponse(null, e.getMessage()));
        }
    }

    @ApiOperation(value = "RefreshToken을 통한 AccessToken 갱신")
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "AccessToken 갱신 성공"),
            @io.swagger.annotations.ApiResponse(code = 401, message = "AccessToken 갱신 실패")
    })
    @PostMapping("/renew")
    public ResponseEntity<String> renewToken(@RequestHeader("DNY-Refresh") String refreshToken, HttpServletResponse response) {
        try {
            userService.renewToken(refreshToken, response);
            return ResponseEntity.ok("AccessToken 갱신 성공");
        } catch (CustomException e) {
            return ResponseEntity.status(e.getErrorCode().getStatus()).body(e.getMessage());
        }
    }
}
