package com.dearnewyear.dny.user.domain;

import com.dearnewyear.dny.user.domain.constant.MainBackground;
import com.dearnewyear.dny.user.domain.constant.MainLp;
import com.dearnewyear.dny.user.dto.request.SignupRequest;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "USER")
@Getter
@NoArgsConstructor
@Component
public class User {

    @Id @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "main_background", nullable = false)
    private MainBackground mainBackground;

    @Column(name = "main_lp", nullable = false)
    private MainLp mainLp;

    public User(SignupRequest request) {
        this.userName = request.getUserName();
        this.email = request.getEmail();
        this.mainBackground = MainBackground.valueOf(request.getMainBackground());
        this.mainLp = MainLp.valueOf(request.getMainLp());
    }
}
