package com.dearnewyear.dny.user.controller;

import com.dearnewyear.dny.user.dto.UserInfo;
import com.dearnewyear.dny.user.dto.response.UserInfoResponse;
import com.dearnewyear.dny.user.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

	private final UserService userService;

	@GetMapping("/main/{userId}")
	public ResponseEntity<UserInfoResponse> getMainInfo(@PathVariable String userId) {
		UserInfo userInfo = userService.getOtherUserInfo(userId);
		return ResponseEntity.ok(new UserInfoResponse(userInfo, null));
	}
}
