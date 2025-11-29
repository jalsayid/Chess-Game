package com.chesslab;

public class Utils {

    public static boolean isAlgebraic(String token) {
        return token != null && token.matches("[a-h][1-8]-[a-h][1-8]");
    }

    public static String normalizeName(String name) {
        if (name == null) return "Player";
        return name.trim();
    }
}
