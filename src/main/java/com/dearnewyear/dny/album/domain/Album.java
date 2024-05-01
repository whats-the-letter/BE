package com.dearnewyear.dny.album.domain;

import com.dearnewyear.dny.album.domain.constant.AlbumBackground;
import com.dearnewyear.dny.album.domain.constant.AlbumCover;
import com.dearnewyear.dny.album.domain.constant.AlbumPhrases;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "album")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Album {

    @Id
    private String albumId;

    @Field(name = "album_cover")
    @NotNull
    private AlbumCover albumCover;

    @Field(name = "album_phrases")
    @NotNull
    private AlbumPhrases albumPhrases;

    @Field(name = "album_background")
    @NotNull
    private AlbumBackground albumBackground;

    @Field(name = "music_id")
    @NotNull
    private String musicId;

    @Field(name = "from_user_id")
    @NotNull
    private String fromUserId;

    @Field(name = "to_user_id")
    private String toUserId;

    @Field(name = "from_name")
    @NotNull
    @Size(min = 1, max = 10)
    private String fromName;

    @Field(name = "to_name")
    @NotNull
    @Size(min = 1, max = 10)
    private String toName;

    @Field(name = "letter")
    @NotNull
    @Size(min = 1, max = 250)
    private String letter;

    @Field(name = "front_image")
    @NotNull
    private String frontImage;

    @Field(name = "back_image")
    @NotNull
    private String backImage;

    @Field(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    public void updateTo(String toUserId) {
        this.toUserId = toUserId;
    }
}
