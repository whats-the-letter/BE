package com.dearnewyear.dny.music.dto.request;

import com.dearnewyear.dny.music.domain.constant.Category;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
@ApiModel(value = "음악 추가 요청 모델")
public class AddMusicRequest {

    @ApiModelProperty(value = "음악 이름", required = true)
    private final String musicName;

    @ApiModelProperty(value = "아티스트", required = true)
    private final String musicArtist;

    @ApiModelProperty(value = "youtube url id", required = true)
    private final String youtubeUrlId;

    @ApiModelProperty(value = "썸네일", required = true)
    private final MultipartFile thumbnail;

    @ApiModelProperty(value = "카테고리", required = true)
    private final String category;
}
