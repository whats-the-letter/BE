package com.dearnewyear.dny.user.controller;

import com.dearnewyear.dny.user.dto.UserInfo;
import com.dearnewyear.dny.user.dto.response.UserInfoResponse;
import com.dearnewyear.dny.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"User"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "다른 사용자 메인 정보")
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "메인 조회 성공"),
            @io.swagger.annotations.ApiResponse(code = 400, message = "메인 조회 권한 없음"),
            @io.swagger.annotations.ApiResponse(code = 404, message = "메인 조회 실패")
    })
    @GetMapping("/main/{userId}")
    public ResponseEntity<UserInfoResponse> getMainInfo(@PathVariable String userId) {
        UserInfo userInfo = userService.getOtherUserInfo(userId);
        return ResponseEntity.ok(new UserInfoResponse(userInfo, null));
    }
}
