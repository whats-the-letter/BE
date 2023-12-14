package com.dearnewyear.dny.album.domain.constant;

import com.dearnewyear.dny.common.utils.PatternMaker;

public class AlbumPatterns {

    public static final String ALBUM_COVER_PATTERN =
            PatternMaker.makePattern(AlbumCover.class);

    public static final String ALBUM_PHRASES_PATTERN =
            PatternMaker.makePattern(AlbumPhrases.class);

    public static final String ALBUM_BACKGROUND_PATTERN =
            PatternMaker.makePattern(AlbumBackground.class);
}
