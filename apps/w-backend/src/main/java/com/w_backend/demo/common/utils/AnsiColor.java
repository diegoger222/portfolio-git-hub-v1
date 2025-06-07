package com.w_backend.demo.common.utils;

public enum AnsiColor {
    RESET("\u001B[0m"),
    BLACK("\u001B[30m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    BLUE("\u001B[34m"),
    PURPLE("\u001B[35m"),
    CYAN("\u001B[36m"),
    WHITE("\u001B[37m"),
    // Bright colors
    BOLD("\u001B[1m"),
    UNDERLINE("\u001B[4m"),
    BG_RED("\u001B[41m"),
    BG_GREEN("\u001B[42m"),;

    private final String code;

    AnsiColor(String code) {
        this.code = code;
    }

    public String get() {
        return code;
    }

    @Override
    public String toString() {
        return code;
    }
}
