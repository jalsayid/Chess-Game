package com.chesslab;

public class NotationUtil {

    // A tiny utility that duplicates small parts of PGNWriter/Parser logic
    public static String squareToString(Square s) {
        if (s == null) return "--";
        return "" + (char)('a' + s.getCol()) + (s.getRow() + 1);
    }

    public static Square stringToSquare(Board b, String s) {
        if (s == null || s.length() < 2) return null;
        int col = s.charAt(0) - 'a';
        int row = s.charAt(1) - '1';
        return b.get(row, col);
    }
}
