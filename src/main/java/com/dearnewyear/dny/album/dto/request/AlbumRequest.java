package com.dearnewyear.dny.album.dto.request;

import com.dearnewyear.dny.album.domain.constant.AlbumBackground;
import com.dearnewyear.dny.album.domain.constant.AlbumCover;
import com.dearnewyear.dny.album.domain.constant.AlbumPhrases;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.regex.Pattern;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@ApiModel(value = "앨범 보내기 요청 모델")
public class AlbumRequest {

    @NotBlank
    @ApiModelProperty(value = "앨범 커버", required = true)
    private final String albumCover;

    @NotBlank
    @ApiModelProperty(value = "앨범 문구", required = true)
    private final String albumPhrases;

    @NotBlank
    @ApiModelProperty(value = "앨범 배경", required = true)
    private final String albumBackground;

    @NotBlank
    @ApiModelProperty(value = "음악 ID", required = true)
    private final String musicId;

    @NotBlank
    @ApiModelProperty(value = "받는 사람 ID")
    private final String toUserId;

    @NotBlank
    @ApiModelProperty(value = "보내는 사람 이름", required = true)
    private final String fromName;

    @NotBlank
    @ApiModelProperty(value = "받는 사람 이름", required = true)
    private final String toName;

    @NotBlank
    @ApiModelProperty(value = "편지 내용", required = true)
    private final String letter;

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

    @AssertTrue(message = "음악 ID는 숫자여야 합니다.")
    public boolean isValidMusicId() {
        return Pattern.matches("^[0-9]*$", musicId);
    }

    @AssertTrue(message = "받는 사람 ID는 숫자여야 합니다.")
    public boolean isValidToUserId() {
        return Pattern.matches("^[0-9]*$", toUserId);
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
