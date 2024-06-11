package com.dearnewyear.dny.common.error;

import lombok.Getter;

@Getter
public enum ErrorCode {

	// Server Error
	INTERNAL_SERVER_ERROR(500, "S001", "서버에 문제가 발생하였습니다."),
	CLOUD_SERVER_ERROR(500, "S002", "클라우드 서버에 문제가 발생하였습니다."),
	DATABASE_ERROR(500, "S003", "데이터베이스에 문제가 발생하였습니다."),

	// Client Error
	METHOD_NOT_ALLOWED(405, "C001", "허용되지 않은 메소드입니다."),
	INVALID_INPUT_VALUE(400, "C002", "입력값이 올바르지 않습니다."),
	INVALID_INPUT_TYPE(400, "C003", "입력값의 타입이 올바르지 않습니다."),
	INVALID_INPUT_LENGTH(400, "C004", "입력값의 길이가 올바르지 않습니다."),
	BAD_REQUEST(400, "C005", "잘못된 요청입니다."),
	ACCESS_DENIED(403, "C006", "요청 권한이 없습니다."),
	NOT_FOUND(404, "C007", "리소스를 찾을 수 없습니다."),

	/**
	 * User Domain
	 */
	USER_NOT_FOUND(404, "U001", "사용자를 찾을 수 없습니다."),
	USER_ALREADY_EXIST(400, "U002", "이미 존재하는 사용자입니다."),
	USER_PARAMS_REQUIRED(400, "U003", "사용자 정보가 필요합니다."),
	INVALID_USER_PARAMS(400, "U004", "사용자 정보가 올바르지 않습니다."),
	KAKAO_OAUTH2_ERROR(400, "U005", "카카오 OAuth2 인증에 실패하였습니다."),
	USER_NAME_ALREADY_EXIST(400, "U006", "이미 존재하는 사용자 이름입니다."),
	USER_NOT_LOGIN(401, "U007", "로그인이 필요합니다."),
	ACCESS_TOKEN_REQUIRED(401, "U008", "Access Token이 누락되었습니다."),
	INVALID_TOKEN(401, "U009", "유효하지 않은 토큰입니다."),
	INVALID_MAIN_ASSET(400, "U010", "메인 에셋 정보가 올바르지 않습니다."),

	/**
	 * Album Domain
	 */
	ALBUM_NOT_FOUND(404, "A001", "앨범을 찾을 수 없습니다."),
	ALBUM_ALREADY_EXIST(400, "A002", "이미 존재하는 앨범입니다."),
	ALBUM_NOT_AUTHORIZED(401, "A003", "앨범 접근 권한이 없습니다."),
	INVALID_ALBUM_ASSET(400, "A004", "앨범 에셋 정보가 올바르지 않습니다."),
	INVALID_ALBUM_USER(400, "A005", "앨범 사용자 정보가 올바르지 않습니다."),
	INVALID_ALBUM_NAME(400, "A006", "앨범을 보내는/받는 사용자 닉네임 형식이 올바르지 않습니다."),
	INVALID_CONTENT_LENGTH(400, "A007", "앨범 내용의 길이가 올바르지 않습니다."),
	INVALID_MUSIC(400, "A008", "앨범 음악 정보가 올바르지 않습니다."),

	/**
	 * Music Domain
	 */
	MUSIC_NOT_FOUND(404, "M001", "음악을 찾을 수 없습니다."),
	INVALID_MUSIC_NAME(400, "M002", "음악 이름 형식이 올바르지 않습니다."),
	INVALID_MUSIC_LINK(400, "M003", "음악 링크 형식이 올바르지 않습니다."),
	MUSIC_ALREADY_EXIST(400, "M004", "이미 존재하는 음악입니다.");

	private final int status;
	private final String code;
	private final String message;

	ErrorCode(int status, String code, String message) {
		this.status = status;
		this.code = code;
		this.message = message;
	}
}
