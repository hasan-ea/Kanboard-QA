package com.kanboard.qa.utils;

public class RandomDataUtils {
    public String uniqueProjectName(String prefix) {
        return prefix + System.currentTimeMillis();
    }
}
