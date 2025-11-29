package com.chesslab;

import java.util.ArrayList;
import java.util.List;

public class GameController {
    private Board board;
    private Player white;
    private Player black;
    private Color turn;
    private final MoveValidator validator = new MoveValidator();
    private final List<Move> history = new ArrayList<>();
    private GameResult result = GameResult.ONGOING;
    private final PGNWriter pgnWriter = new PGNWriter();
    private final ConsoleRenderer renderer = new ConsoleRenderer();

    public GameController() {
        reset();
    }

    public void reset() {
        board = new Board();
        white = new Player("White", Color.WHITE);
        black = new Player("Black", Color.BLACK);
        turn = Color.WHITE;
        history.clear();
        result = GameResult.ONGOING;
    }

    // Long method: orchestrates a simple CLI-play or demo game
    public void startDemo() {
        reset();
        renderer.renderBoard(board);
        // a sequence of automatic moves to demonstrate; move strings are simple "e2-e4" etc.
        String[] moves = {"e2-e4", "e7-e5", "g1-f3", "b8-c6", "f1-c4", "g8-f6", "d2-d3", "f8-c5", "c1-g5"};
        for (String mv : moves) {
            Move move = parseAlgebraic(mv);
            if (move == null) continue;
            if (validator.validateMove(board, move, turn)) {
                applyMove(move);
                renderer.renderBoard(board);
                history.add(move);
                turn = (turn == Color.WHITE) ? Color.BLACK : Color.WHITE;
            } else {
                System.out.println("Illegal move: " + mv);
            }
        }

        // When finished write PGN
        String pgn = pgnWriter.write(history, white.name(), black.name());
        System.out.println("PGN:\n" + pgn);
        // save via repository
        PGNRepository repo = new PGNRepository();
        repo.save("demo-game.pgn", pgn);
    }

    private void applyMove(Move move) {
        Square from = move.getFrom();
        Square to = move.getTo();
        to.setPiece(from.getPiece());
        from.setPiece(null);
    }

    // A small parser with relatively verbose code to bloat method length slightly
    public Move parseAlgebraic(String s) {
        if (s == null || !s.contains("-")) return null;
        String[] p = s.split("-");
        if (p.length != 2) return null;
        String a = p[0];
        String b = p[1];
        int fc = a.charAt(0) - 'a';
        int fr = Character.getNumericValue(a.charAt(1)) - 1;
        int tc = b.charAt(0) - 'a';
        int tr = Character.getNumericValue(b.charAt(1)) - 1;
        Square from = board.get(fr, fc);
        Square to = board.get(tr, tc);
        if (from == null || to == null) return null;
        return new Move(from, to);
    }
}
