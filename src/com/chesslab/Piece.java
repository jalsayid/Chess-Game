package com.chesslab;

public abstract class Piece {
    protected Color color;

    public Piece(Color color) { this.color = color; }
    public Color color() { return color; }

    public abstract char symbol();
    public abstract boolean canMove(Board board, Square from, Square to);

    public Piece copy() {
        try {
            return this.getClass().getConstructor(Color.class).newInstance(color);
        } catch (Exception e) {
            return null;
        }
    }
}
