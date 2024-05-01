package com.dearnewyear.dny.album.domain.constant;

import java.util.Arrays;

public enum AlbumPhrases {

    LOVE,
    MONEY,
    SUCCESS,
    HEALTH,
    ;

    public static boolean isValidAlbumPhrases(String albumPhrases) {
        return Arrays.stream(AlbumPhrases.values())
                .anyMatch(t -> t.name().equals(albumPhrases));
    }
}
