package com.chesslab;

public class Bishop extends Piece {
    public Bishop(Color color) { super(color); }
    @Override
    public char symbol() { return color == Color.WHITE ? 'B' : 'b'; }

    @Override
    public boolean canMove(Board board, Square from, Square to) {
        if (to == null) return false;
        int dr = Math.abs(from.getRow() - to.getRow());
        int dc = Math.abs(from.getCol() - to.getCol());
        return dr == dc;
    }
}
