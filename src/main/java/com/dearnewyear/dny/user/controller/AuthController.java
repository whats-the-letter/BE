package com.dearnewyear.dny.user.controller;

import com.dearnewyear.dny.user.dto.UserInfo;
import com.dearnewyear.dny.user.dto.request.SignupRequest;
import com.dearnewyear.dny.user.dto.response.UserInfoResponse;
import com.dearnewyear.dny.user.service.KakaoOAuth2Service;
import com.dearnewyear.dny.user.service.UserService;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

	private final KakaoOAuth2Service kakaoOAuth2Service;
	private final UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<UserInfoResponse> signup(@ModelAttribute @Valid SignupRequest request,
		HttpServletResponse response) {
		UserInfo userInfo = userService.signupAndLoginUser(request, response);
		return ResponseEntity.status(201).body(new UserInfoResponse(userInfo, null));
	}

	@GetMapping("/login/kakao/code")
	public ResponseEntity<UserInfoResponse> kakaoLogin(@RequestParam("code") String code,
		HttpServletResponse response) {
		response.setHeader("Content-Type", "application/json");
		String accessToken = kakaoOAuth2Service.getAccessToken(code);
		Map<String, Object> kakaoUser = kakaoOAuth2Service.getKakaoUser(accessToken);
		UserInfo userInfo = userService.loginUser(kakaoUser, response);

		if (userInfo.getUserName() == null)
			return ResponseEntity.status(404).body(new UserInfoResponse(userInfo, "회원가입 필요"));
		return ResponseEntity.ok(new UserInfoResponse(userInfo, null));

	}

	@PostMapping("/renew")
	public ResponseEntity<String> renewToken(
		@RequestHeader("#{@refreshHeader}") String refreshToken, HttpServletResponse response) {
		userService.renewToken(refreshToken, response);
		return ResponseEntity.ok("AccessToken 갱신 성공");
	}
}
