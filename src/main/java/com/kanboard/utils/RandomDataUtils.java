package com.kanboard.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RandomDataUtils {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    public static String generateProjectName(String prefix) {
        return prefix + "_" + LocalDateTime.now().format(FORMATTER);
    }

    public static String generateIdentifier(String prefix) {
        return (prefix + "-" + LocalDateTime.now().format(FORMATTER)).toLowerCase();
    }
}