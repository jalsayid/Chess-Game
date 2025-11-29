package com.chesslab;

import java.util.ArrayList;
import java.util.List;

public class Round {
    private final int number;
    private final List<Match> matches = new ArrayList<>();

    public Round(int number) { this.number = number; }
    public int getNumber() { return number; }
    public List<Match> getMatches() { return matches; }
}
