package com.chesslab;

public class Match {
    private final Player white;
    private final Player black;
    private int roundNumber;
    private String timeControl;
    private GameResult result = GameResult.ONGOING;

    public Match(Player white, Player black) {
        this.white = white;
        this.black = black;
    }

    public Player getWhite() { return white; }
    public Player getBlack() { return black; }
    public int getRoundNumber() { return roundNumber; }
    public void setRoundNumber(int roundNumber) { this.roundNumber = roundNumber; }
    public String getTimeControl() { return timeControl; }
    public void setTimeControl(String timeControl) { this.timeControl = timeControl; }
    public GameResult getResult() { return result; }
    public void setResult(GameResult result) { this.result = result; }
}
