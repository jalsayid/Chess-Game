package com.chesslab;

public class Move {
    private final Square from;
    private final Square to;
    private final Piece moved;
    private final Piece captured;

    public Move(Square from, Square to) {
        this.from = from;
        this.to = to;
        this.moved = from.getPiece();
        this.captured = to.getPiece();
    }

    public Square getFrom() { return from; }
    public Square getTo() { return to; }
    public Piece getMoved() { return moved; }
    public Piece getCaptured() { return captured; }

    public String toAlgebraic() {
        char f = (char)('a' + from.getCol());
        char t = (char)('a' + to.getCol());
        return "" + f + (from.getRow() + 1) + "-" + t + (to.getRow() + 1);
    }
}
