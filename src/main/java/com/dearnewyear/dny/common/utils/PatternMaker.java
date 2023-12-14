package com.dearnewyear.dny.common.utils;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PatternMaker {

    public static <E extends Enum<E>> String makePattern(Class<E> enumClass) {
        return Stream.of(enumClass.getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.joining("|"));
    }
}
