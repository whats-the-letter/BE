package com.dearnewyear.dny.music.domain.constant;

import java.util.Arrays;

public enum Tag {

	LOVE,
	CHEERS,
	BIRTHDAY,
	OTHERS;

	public static boolean isValidTag(String tag) {
		return Arrays.stream(Tag.values())
			.anyMatch(t -> t.name().equals(tag));
	}
}
