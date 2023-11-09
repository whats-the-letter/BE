package com.dearnewyear.dny.user.controller;

import com.dearnewyear.dny.common.dto.response.ApiResponse;
import com.dearnewyear.dny.common.dto.response.ErrorResponse;
import com.dearnewyear.dny.common.error.exception.CustomException;
import com.dearnewyear.dny.user.dto.request.SignupRequest;
import com.dearnewyear.dny.user.service.UserService;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

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
}
