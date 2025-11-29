package com.chesslab;

public class Knight extends Piece {
    public Knight(Color color) { super(color); }
    @Override
    public char symbol() { return color == Color.WHITE ? 'N' : 'n'; }

    @Override
    public boolean canMove(Board board, Square from, Square to) {
        if (to == null) return false;
        int dr = Math.abs(from.getRow() - to.getRow());
        int dc = Math.abs(from.getCol() - to.getCol());
        return (dr == 2 && dc == 1) || (dr == 1 && dc == 2);
    }
}
