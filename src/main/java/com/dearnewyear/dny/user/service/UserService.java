package com.dearnewyear.dny.user.service;

import com.dearnewyear.dny.album.domain.Album;
import com.dearnewyear.dny.album.repository.AlbumRepository;
import com.dearnewyear.dny.common.error.ErrorCode;
import com.dearnewyear.dny.common.error.CustomException;
import com.dearnewyear.dny.common.jwt.JwtTokenProvider;
import com.dearnewyear.dny.user.domain.User;
import com.dearnewyear.dny.user.dto.UserInfo;
import com.dearnewyear.dny.user.dto.request.SignupRequest;
import com.dearnewyear.dny.user.repository.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

	private final JwtTokenProvider jwtTokenProvider;
	private final UserRepository userRepository;
	private final AlbumRepository albumRepository;

	@Value("${auth.header.prefix}")
	private String authHeaderPrefix;

	@Value("${auth.header.authorization}")
	private String authHeader;

	@Value("${auth.header.refresh}")
	private String refreshHeader;

	private final int PLAYLIST_COUNT = 7;

	public UserInfo signupAndLoginUser(SignupRequest request, HttpServletResponse response) {
		if (userRepository.findByEmail(request.getEmail()).isPresent())
			throw new CustomException(ErrorCode.USER_ALREADY_EXIST);

		User user = new User(request);
		userRepository.save(user);

		return getUserInfo(response, user);
	}

	public UserInfo loginUser(Map<String, Object> kakaoUser, HttpServletResponse response) {
		String email = (String)kakaoUser.get("email");
		if (email == null) {
			throw new CustomException(ErrorCode.INVALID_USER_PARAMS);
		}

		Optional<User> user = userRepository.findByEmail(email);
		if (user.isPresent()) {
			return getUserInfo(response, user.get());
		} else {
			return UserInfo.builder()
				.email(email)
				.build();
		}
	}

	public void renewToken(String refreshToken, HttpServletResponse response) {
		String newAccessToken = jwtTokenProvider.renewAccessToken(refreshToken);
		response.setHeader(authHeader, authHeaderPrefix + " " + newAccessToken);
	}

	private UserInfo getUserInfo(HttpServletResponse response, User user) {
		String accessToken = jwtTokenProvider.createAccessToken(user);
		String refreshToken = jwtTokenProvider.createRefreshToken(user);

		response.setHeader(authHeader, authHeaderPrefix + " " + accessToken);
		response.setHeader(refreshHeader, refreshToken);
		return UserInfo.builder()
			.userId(user.getUserId())
			.userName(user.getUserName())
			.email(user.getEmail())
			.mainBackground(String.valueOf(user.getMainBackground()))
			.mainLp(String.valueOf(user.getMainLp()))
			.playlist(getPlaylist(user))
			.build();
	}

	public UserInfo getOtherUserInfo(String userId) {
		User user = userRepository.findById(userId)
			.orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
		return UserInfo.builder()
			.userId(user.getUserId())
			.userName(user.getUserName())
			.mainBackground(String.valueOf(user.getMainBackground()))
			.mainLp(String.valueOf(user.getMainLp()))
			.playlist(getPlaylist(user))
			.build();
	}

	private List<String> getPlaylist(User user) {
		List<Album> albums = albumRepository.findByToUserId(user.getUserId());
		Collections.shuffle(albums);

		return albums.stream()
			.limit(PLAYLIST_COUNT)
			.map(Album::getFromName)
			.collect(Collectors.toList());
	}
}
