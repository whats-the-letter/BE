package com.dearnewyear.dny.user.service;

import com.dearnewyear.dny.common.error.ErrorCode;
import com.dearnewyear.dny.common.error.CustomException;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class KakaoOAuth2Service {

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String kakaoClientId;

    @Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
    private String kakaoClientSecret;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String kakaoRedirectUri;

    @Value("${spring.security.oauth2.client.provider.kakao.token-uri}")
    private String kakaoTokenUri;

    @Value("${spring.security.oauth2.client.provider.kakao.user-info-uri}")
    private String kakaoUserInfoUri;

    @Value("${auth.header.prefix}")
    private String authHeaderPrefix;

    @Value("${auth.header.authorization}")
    private String authHeader;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getAccessToken(String code) {
        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("client_id", kakaoClientId);
        params.add("client_secret", kakaoClientSecret);
        params.add("redirect_uri", kakaoRedirectUri);
        params.add("code", code);
        params.add("grant_type", "authorization_code");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(kakaoTokenUri, request,
                Map.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return (String) response.getBody().get("access_token");
        } else {
            throw new CustomException(ErrorCode.INVALID_USER_PARAMS);
        }
    }

    public Map<String, Object> getKakaoUser(String token) {
        HttpHeaders headers = getHeaders(token);
        ResponseEntity<Map> kakaoResponse = restTemplate.postForEntity(kakaoUserInfoUri,
                new HttpEntity<>(headers), Map.class);

        if (kakaoResponse.getStatusCode() != HttpStatus.OK) {
            throw new CustomException(ErrorCode.KAKAO_OAUTH2_ERROR);
        }
        return (Map<String, Object>) kakaoResponse.getBody().get("kakao_account");
    }

    public HttpHeaders getHeaders(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(authHeader, authHeaderPrefix + " " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        return headers;
    }
}
