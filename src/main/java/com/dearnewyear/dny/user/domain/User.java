package com.dearnewyear.dny.user.domain;

import com.dearnewyear.dny.user.domain.constant.MainBackground;
import com.dearnewyear.dny.user.domain.constant.MainLp;
import com.dearnewyear.dny.user.dto.request.SignupRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

@Document(collection = "user")
@Getter
@NoArgsConstructor
@Component
public class User {

    @Id
    private String userId;

    @Field(name = "user_name")
    @NotNull
    @Size(min = 2, max = 6)
    private String userName;

    @Field(name = "email")
    @NotNull
    @Email
    private String email;

    @Field(name = "main_background")
    @NotNull
    private MainBackground mainBackground;

    @Field(name = "main_lp")
    @NotNull
    private MainLp mainLp;

    @Field(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    @Field(name = "sent_album_ids")
    private List<String> sentAlbumIds = new ArrayList<>();

    @Field(name = "received_album_ids")
    private List<String> receivedAlbumIds = new ArrayList<>();

    public User(SignupRequest request) {
        this.userName = request.getUserName();
        this.email = request.getEmail();
        this.mainBackground = request.getMainBackground();
        this.mainLp = request.getMainLp();
    }
}
