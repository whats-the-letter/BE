package com.dearnewyear.dny.user.service;

import com.dearnewyear.dny.common.error.ErrorCode;
import com.dearnewyear.dny.common.error.exception.CustomException;
import com.dearnewyear.dny.common.jwt.JwtTokenProvider;
import com.dearnewyear.dny.user.domain.User;
import com.dearnewyear.dny.user.dto.request.SignupRequest;
import com.dearnewyear.dny.user.repository.UserRepository;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    public void signupAndLoginUser(SignupRequest request, HttpServletResponse response) {
        if (userRepository.findByEmail(request.getEmail()).isPresent())
            throw new CustomException(ErrorCode.USER_ALREADY_EXIST);

        User user = new User(request);
        userRepository.save(user);

        String accessToken = jwtTokenProvider.createAccessToken(user);
        String refreshToken = jwtTokenProvider.createRefreshToken(user);

        response.setHeader("Authorization", "Bearer " + accessToken);
        response.setHeader("DNY-Refresh", refreshToken);
    }

    public String chkAndLoginUser(String email, HttpServletResponse response) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            String accessToken = jwtTokenProvider.createAccessToken(user.get());
            String refreshToken = jwtTokenProvider.createRefreshToken(user.get());

            response.setHeader("Authorization", "Bearer " + accessToken);
            response.setHeader("DNY-Refresh", refreshToken);
            return "success";
        } else {
            return email;
        }
    }

    public void renewToken(String refreshToken, HttpServletResponse response) {
        String newAccessToken = jwtTokenProvider.renewAccessToken(refreshToken);
        response.setHeader("Authorization", "Bearer " + newAccessToken);
    }
}
