package com.chesslab;

public class Pawn extends Piece {
    public Pawn(Color color) { super(color); }
    @Override
    public char symbol() { return color == Color.WHITE ? 'P' : 'p'; }

    @Override
    public boolean canMove(Board board, Square from, Square to) {
        if (to == null) return false;
        int dir = color == Color.WHITE ? 1 : -1;
        int dr = to.getRow() - from.getRow();
        int dc = Math.abs(to.getCol() - from.getCol());
        if (dc == 0) {
            if (dr == dir) return true;
            if ((color == Color.WHITE && from.getRow() == 1 || color == Color.BLACK && from.getRow() == 6) && dr == 2 * dir) return true;
        } else if (dc == 1 && dr == dir) {
            Piece p = board.get(to.getRow(), to.getCol()).getPiece();
            return p != null && p.color() != this.color();
        }
        return false;
    }
}
