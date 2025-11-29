package com.chesslab;

import java.util.Arrays;

public class Board {
    private final Square[][] grid = new Square[8][8];

    public Board() {
        initEmpty();
        setupDefault();
    }

    private void initEmpty() {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                grid[r][c] = new Square(r, c, null);
            }
        }
    }

    private void setupDefault() {
        // Pawns
        for (int c = 0; c < 8; c++) {
            grid[1][c].setPiece(new Pawn(Color.WHITE));
            grid[6][c].setPiece(new Pawn(Color.BLACK));
        }
        // Rooks
        grid[0][0].setPiece(new Rook(Color.WHITE));
        grid[0][7].setPiece(new Rook(Color.WHITE));
        grid[7][0].setPiece(new Rook(Color.BLACK));
        grid[7][7].setPiece(new Rook(Color.BLACK));
        // Knights
        grid[0][1].setPiece(new Knight(Color.WHITE));
        grid[0][6].setPiece(new Knight(Color.WHITE));
        grid[7][1].setPiece(new Knight(Color.BLACK));
        grid[7][6].setPiece(new Knight(Color.BLACK));
        // Bishops
        grid[0][2].setPiece(new Bishop(Color.WHITE));
        grid[0][5].setPiece(new Bishop(Color.WHITE));
        grid[7][2].setPiece(new Bishop(Color.BLACK));
        grid[7][5].setPiece(new Bishop(Color.BLACK));
        // Queens and Kings
        grid[0][3].setPiece(new Queen(Color.WHITE));
        grid[0][4].setPiece(new King(Color.WHITE));
        grid[7][3].setPiece(new Queen(Color.BLACK));
        grid[7][4].setPiece(new King(Color.BLACK));
    }

    public Square get(int r, int c) {
        if (r < 0 || r >= 8 || c < 0 || c >= 8) return null;
        return grid[r][c];
    }

    public Square[][] getGrid() {
        return grid;
    }

    public Board copy() {
        Board b = new Board();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece p = grid[r][c].getPiece();
                if (p != null) b.grid[r][c].setPiece(p.copy());
                else b.grid[r][c].setPiece(null);
            }
        }
        return b;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int r = 7; r >= 0; r--) {
            sb.append(r + 1).append(" ");
            for (int c = 0; c < 8; c++) {
                Piece p = grid[r][c].getPiece();
                sb.append(p == null ? "." : p.symbol()).append(" ");
            }
            sb.append("\n");
        }
        sb.append("  a b c d e f g h\n");
        return sb.toString();
    }
}
