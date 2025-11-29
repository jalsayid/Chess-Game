package com.chesslab;

import java.util.ArrayList;
import java.util.List;

public class Tournament {
    private final String name;
    private List<Player> players = new ArrayList<>();
    private List<Round> rounds = new ArrayList<>();

    public Tournament(String name) { this.name = name; }
    public String getName() { return name; }
    public List<Player> getPlayers() { return players; }
    public void setPlayers(List<Player> players) { this.players = players; }
    public List<Round> getRounds() { return rounds; }
    public void setRounds(List<Round> rounds) { this.rounds = rounds; }
}
