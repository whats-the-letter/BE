package com.dearnewyear.dny.album.dto;

import com.dearnewyear.dny.album.domain.Album;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@ApiModel(value = "앨범 정보 모델")
public class AlbumInfo {

    @ApiModelProperty(value = "앨범 ID")
    private final Long albumId;

    @ApiModelProperty(value = "앨범 커버")
    private final String albumCover;

    @ApiModelProperty(value = "앨범 문구")
    private final String albumPhrases;

    @ApiModelProperty(value = "앨범 배경")
    private final String albumBackground;

    @ApiModelProperty(value = "음악 이름")
    private final String musicName;

    @ApiModelProperty(value = "음악 아티스트")
    private final String musicArtist;

    @ApiModelProperty(value = "음악 youtube url id")
    private final String youtubeUrlId;

    @ApiModelProperty(value = "보내는 사람")
    private final String fromName;

    @ApiModelProperty(value = "받는 사람")
    private final String toName;

    @ApiModelProperty(value = "편지 내용")
    private final String letter;

    public AlbumInfo(Album album) {
        this.albumId = album.getAlbumId();
        this.albumCover = album.getAlbumCover().name();
        this.albumPhrases = album.getAlbumPhrases().name();
        this.albumBackground = album.getAlbumBackground().name();
        this.musicName = album.getMusic().getMusicName();
        this.musicArtist = album.getMusic().getMusicArtist();
        this.youtubeUrlId = album.getMusic().getYoutubeUrlId();
        this.fromName = album.getFromName();
        this.toName = album.getToName();
        this.letter = album.getLetter();
    }
}
