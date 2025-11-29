package com.chesslab;

import java.util.ArrayList;
import java.util.List;

/**
 * Minimal PGN parser (supports a subset of PGN). It intentionally duplicates
 * string-parsing logic you'll also find in PGNWriter to create similar code paths.
 */
public class PGNParser {

    public List<Move> parseMoves(String pgn) {
        if (pgn == null) return new ArrayList<>();
        // naive parser: find movetext after blank line and parse simple tokens
        String[] parts = pgn.split("\n\n");
        String movetext = pgn;
        if (parts.length >= 2) movetext = parts[1];
        movetext = movetext.replaceAll("\\{.*?\\}", ""); // strip comments
        String[] tokens = movetext.split("\\s+");
        List<Move> moves = new ArrayList<>();
        // this parser assumes algebraic like e2-e4 tokens separated by spaces (nonstandard but simple)
        Board board = new Board();
        for (String t : tokens) {
            if (t.matches("\\d+\\.")) continue;
            if (t.contains("-")) {
                // parse e2-e4
                String[] pp = t.split("-");
                if (pp.length != 2) continue;
                int fr = pp[0].charAt(1) - '1';
                int fc = pp[0].charAt(0) - 'a';
                int tr = pp[1].charAt(1) - '1';
                int tc = pp[1].charAt(0) - 'a';
                Square from = board.get(fr, fc);
                Square to = board.get(tr, tc);
                if (from != null && to != null) {
                    Move m = new Move(from, to);
                    moves.add(m);
                    // apply roughly
                    to.setPiece(from.getPiece());
                    from.setPiece(null);
                }
            }
        }
        return moves;
    }
}
