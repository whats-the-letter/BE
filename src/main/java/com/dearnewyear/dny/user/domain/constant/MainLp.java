package com.dearnewyear.dny.user.domain.constant;

import java.util.Arrays;

public enum MainLp {

	WTL,
	HIGH,
	LOW,
	HEADPHONES,
	;

	public static boolean isValidMainLp(String mainLp) {
		return Arrays.stream(MainLp.values())
			.anyMatch(t -> t.name().equals(mainLp));
	}
}
