package com.aditya.framework.enums;

public enum UserType {
    STANDARD("standard"),
    LOCKED("locked"),
    INVALID("invalid");

    private final String keyPrefix;

    UserType(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }

    public String prefix() {
        return keyPrefix;
    }

    public static UserType from(String s) {
        return UserType.valueOf(s.trim().toUpperCase());
    }
}