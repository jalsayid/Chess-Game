package com.chesslab;

public class Player {
    private final String name;
    private final Color color;

    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
    }
    public String name() { return name; }
    public Color color() { return color; }
    @Override
    public String toString() { return name + " (" + color + ")"; }
}
