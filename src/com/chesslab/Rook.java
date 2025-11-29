package com.chesslab;

public class Rook extends Piece {
    public Rook(Color color) { super(color); }
    @Override
    public char symbol() { return color == Color.WHITE ? 'R' : 'r'; }

    @Override
    public boolean canMove(Board board, Square from, Square to) {
        if (to == null) return false;
        return from.getRow() == to.getRow() || from.getCol() == to.getCol();
    }
}
