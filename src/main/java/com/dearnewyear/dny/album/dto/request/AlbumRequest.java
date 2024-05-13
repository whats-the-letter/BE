package com.dearnewyear.dny.album.dto.request;

import com.dearnewyear.dny.album.domain.constant.AlbumBackground;
import com.dearnewyear.dny.album.domain.constant.AlbumCover;
import com.dearnewyear.dny.album.domain.constant.AlbumPhrases;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
public class AlbumRequest {

    @NotBlank
    private final String albumCover;

    @NotBlank
    private final String albumPhrases;

    @NotBlank
    private final String albumBackground;

    @NotBlank
    private final String musicId;

    private final String toUserId;

    @NotBlank
    private final String fromName;

    @NotBlank
    private final String toName;

    @NotBlank
    private final String letter;

    private final MultipartFile frontImage;

    private final MultipartFile backImage;

    @AssertTrue(message = "유효하지 않은 앨범 커버입니다.")
    public boolean isValidAlbumCover() {
        return AlbumCover.isValidAlbumCover(albumCover);
    }

    @AssertTrue(message = "유효하지 않은 앨범 문구입니다.")
    public boolean isValidAlbumPhrases() {
        return AlbumPhrases.isValidAlbumPhrases(albumPhrases);
    }

    @AssertTrue(message = "유효하지 않은 앨범 배경입니다.")
    public boolean isValidAlbumBackground() {
        return AlbumBackground.isValidAlbumBackground(albumBackground);
    }

    public AlbumCover getAlbumCover() {
        return AlbumCover.valueOf(albumCover);
    }

    public AlbumPhrases getAlbumPhrases() {
        return AlbumPhrases.valueOf(albumPhrases);
    }

    public AlbumBackground getAlbumBackground() {
        return AlbumBackground.valueOf(albumBackground);
    }
}
