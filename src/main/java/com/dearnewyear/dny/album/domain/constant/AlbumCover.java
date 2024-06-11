package com.dearnewyear.dny.album.domain.constant;

import java.util.Arrays;

public enum AlbumCover {

	LOVE,
	MONEY,
	SUCCESS,
	HEALTH,
	;

	public static boolean isValidAlbumCover(String albumCover) {
		return Arrays.stream(AlbumCover.values())
			.anyMatch(t -> t.name().equals(albumCover));
	}
}
