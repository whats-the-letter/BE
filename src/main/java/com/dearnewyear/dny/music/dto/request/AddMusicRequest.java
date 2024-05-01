package com.dearnewyear.dny.music.dto.request;

import com.dearnewyear.dny.music.domain.constant.Tag;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@ApiModel(value = "음악 추가 요청 모델")
public class AddMusicRequest {

    @NotBlank
    @ApiModelProperty(value = "음악 이름", required = true)
    private final String musicName;

    @NotBlank
    @ApiModelProperty(value = "아티스트", required = true)
    private final String musicArtist;

    @NotBlank
    @ApiModelProperty(value = "youtube url id", required = true)
    private final String youtubeUrlId;

    @NotEmpty
    @ApiModelProperty(value = "태그", required = true)
    private final List<String> tags;

    @AssertTrue(message = "유효하지 않은 태그입니다.")
    public boolean isValidTags() {
        return tags.stream().allMatch(Tag::isValidTag);
    }

    public List<Tag> getTags() {
        return tags.stream()
                .map(Tag::valueOf)
                .collect(Collectors.toList());
    }
}
