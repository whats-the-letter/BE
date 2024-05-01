package com.dearnewyear.dny.album.domain.constant;

import java.util.Arrays;

public enum AlbumBackground {

    COLORFUL,
    PARTICLES,
    CIRCLES,
    ;

    public static boolean isValidAlbumBackground(String albumBackground) {
        return Arrays.stream(AlbumBackground.values())
                .anyMatch(t -> t.name().equals(albumBackground));
    }
}
