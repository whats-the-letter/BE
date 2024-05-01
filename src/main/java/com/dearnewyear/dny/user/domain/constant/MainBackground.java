package com.dearnewyear.dny.user.domain.constant;

import java.util.Arrays;

public enum MainBackground {

    PINK,
    BLUE,
    WHITE,
    GOLD;

    public static boolean isValidMainBackground(String mainBackground) {
        return Arrays.stream(MainBackground.values())
                .anyMatch(t -> t.name().equals(mainBackground));
    }
}
