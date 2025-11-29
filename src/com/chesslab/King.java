package com.chesslab;

public class King extends Piece {
    public King(Color color) { super(color); }

    @Override
    public char symbol() { return color == Color.WHITE ? 'K' : 'k'; }

    @Override
    public boolean canMove(Board board, Square from, Square to) {
        if (to == null) return false;
        int dr = Math.abs(from.getRow() - to.getRow());
        int dc = Math.abs(from.getCol() - to.getCol());
        return (dr <= 1 && dc <= 1);
    }
}
