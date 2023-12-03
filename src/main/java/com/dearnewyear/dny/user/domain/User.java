package com.dearnewyear.dny.user.domain;

import com.dearnewyear.dny.album.domain.Album;
import com.dearnewyear.dny.user.domain.constant.MainBackground;
import com.dearnewyear.dny.user.domain.constant.MainLp;
import com.dearnewyear.dny.user.dto.request.SignupRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
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

    @Column(name = "user_name", nullable = false, length = 6)
    private String userName;

    @Column(name = "email", nullable = false, unique = true, length = 30)
    private String email;

    @Column(name = "main_background", nullable = false)
    @Enumerated(EnumType.STRING)
    private MainBackground mainBackground;

    @Column(name = "main_lp", nullable = false)
    @Enumerated(EnumType.STRING)
    private MainLp mainLp;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "fromUser", cascade = CascadeType.ALL)
    private List<Album> sentAlbums = new ArrayList<>();

    @OneToMany(mappedBy = "toUser", cascade = CascadeType.ALL)
    private List<Album> receivedAlbums = new ArrayList<>();

    public User(SignupRequest request) {
        this.userName = request.getUserName();
        this.email = request.getEmail();
        this.mainBackground = MainBackground.valueOf(request.getMainBackground());
        this.mainLp = MainLp.valueOf(request.getMainLp());
    }
}
