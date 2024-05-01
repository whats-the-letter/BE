package com.dearnewyear.dny.album.dto;

import com.dearnewyear.dny.album.domain.Album;
import com.dearnewyear.dny.music.domain.Music;
import com.dearnewyear.dny.music.domain.constant.Tag;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@ApiModel(value = "앨범 정보 모델")
public class AlbumInfo {

    @ApiModelProperty(value = "앨범 ID")
    private final String albumId;

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

    @ApiModelProperty(value = "음악 tags")
    private final List<Tag> tags;

    @ApiModelProperty(value = "보내는 사람")
    private final String fromName;

    @ApiModelProperty(value = "받는 사람")
    private final String toName;

    @ApiModelProperty(value = "편지 내용")
    private final String letter;

    public AlbumInfo(Album album, Music music) {
        this.albumId = album.getAlbumId();
        this.albumCover = album.getAlbumCover().name();
        this.albumPhrases = album.getAlbumPhrases().name();
        this.albumBackground = album.getAlbumBackground().name();
        this.musicName = music.getMusicName();
        this.musicArtist = music.getMusicArtist();
        this.youtubeUrlId = music.getYoutubeUrlId();
        this.tags = music.getTags();
        this.fromName = album.getFromName();
        this.toName = album.getToName();
        this.letter = album.getLetter();
    }
}
