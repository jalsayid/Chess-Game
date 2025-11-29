package com.chesslab;

public class ConsoleRenderer {
    /**
     * Long method that renders the board to console with ASCII art.
     * This is structurally very similar to the rendering in CLI.renderBoard to create a clone smell.
     */
    public void renderBoard(Board board) {
        System.out.println("  +-----------------+");
        for (int r = 7; r >= 0; r--) {
            System.out.print((r + 1) + " | ");
            for (int c = 0; c < 8; c++) {
                Square s = board.get(r, c);
                Piece p = s.getPiece();
                char ch = p == null ? '.' : p.symbol();
                System.out.print(ch + " ");
            }
            System.out.println("|");
        }
        System.out.println("  +-----------------+");
        System.out.println("    a b c d e f g h");
    }
}
