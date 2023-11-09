package com.dearnewyear.dny.user.service;

import com.dearnewyear.dny.common.jwt.JwtTokenProvider;
import com.dearnewyear.dny.user.domain.User;
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

    public boolean chkAndLoginUser(String email, HttpServletResponse response) {
        Optional<User> user = userRepository.findByEmail(email);
        System.out.println("user: " + user);
        if (user.isPresent()) {
            String accessToken = jwtTokenProvider.createAccessToken(user.get());
            String refreshToken = jwtTokenProvider.createRefreshToken(user.get());

            response.setHeader("Authorization", accessToken);
            response.setHeader("RefreshToken", refreshToken);
            return true;
        }
        return false;
    }
}
