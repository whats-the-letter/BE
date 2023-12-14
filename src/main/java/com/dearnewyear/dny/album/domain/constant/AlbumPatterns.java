package com.dearnewyear.dny.album.domain.constant;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AlbumPatterns {

    public static final String ALBUM_COVER_PATTERN =
            Stream.of(AlbumCover.values())
                    .map(Enum::name)
                    .collect(Collectors.joining("|"));

    public static final String ALBUM_PHRASES_PATTERN =
            Stream.of(AlbumPhrases.values())
                    .map(Enum::name)
                    .collect(Collectors.joining("|"));

    public static final String ALBUM_BACKGROUND_PATTERN =
            Stream.of(AlbumBackground.values())
                    .map(Enum::name)
                    .collect(Collectors.joining("|"));
}
