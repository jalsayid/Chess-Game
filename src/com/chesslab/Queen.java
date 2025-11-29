package com.chesslab;

public class Queen extends Piece {
    public Queen(Color color) { super(color); }
    @Override
    public char symbol() { return color == Color.WHITE ? 'Q' : 'q'; }

    @Override
    public boolean canMove(Board board, Square from, Square to) {
        if (to == null) return false;
        int dr = Math.abs(from.getRow() - to.getRow());
        int dc = Math.abs(from.getCol() - to.getCol());
        return dr == dc || from.getRow() == to.getRow() || from.getCol() == to.getCol();
    }
}
