package com.chesslab;

import java.util.List;

public class PGNWriter {

    /**
     * Long-ish method that builds a PGN string from move history.
     * Some of the text processing is similar to PGNParser.parseMoves.
     */
    public String write(List<Move> moves, String whiteName, String blackName) {
        StringBuilder sb = new StringBuilder();
        sb.append("[Event \"Demo\"]\n");
        sb.append("[Site \"Local\"]\n");
        sb.append("[Date \"2025.11.29\"]\n");
        sb.append("[Round \"1\"]\n");
        sb.append("[White \"" + whiteName + "\"]\n");
        sb.append("[Black \"" + blackName + "\"]\n");
        sb.append("[Result \"*\"]\n\n");

        int ply = 0;
        for (int i = 0; i < moves.size(); i++) {
            if (i % 2 == 0) {
                sb.append((i / 2) + 1).append(". ");
            }
            Move m = moves.get(i);
            sb.append(toSAN(m)).append(" ");
            ply++;
            if (ply > 1000) break;
        }
        sb.append("*");
        return sb.toString();
    }

    // small helper that again contains string logic similar to parser
    private String toSAN(Move m) {
        if (m == null) return "";
        Piece p = m.getMoved();
        StringBuilder sb = new StringBuilder();
        if (!(p instanceof Pawn)) sb.append(Character.toUpperCase(p.symbol())).append("");
        sb.append((char)('a' + m.getTo().getCol()));
        sb.append(m.getTo().getRow() + 1);
        if (m.getCaptured() != null) sb.append("x");
        return sb.toString();
    }
}
