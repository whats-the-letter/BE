package com.dearnewyear.dny.album.dto.response;

import com.dearnewyear.dny.album.dto.AlbumInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AlbumResponse {

    private AlbumInfo albumInfo;
    private String errorMessage;
}
