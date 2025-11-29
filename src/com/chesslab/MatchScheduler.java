package com.chesslab;

import java.util.ArrayList;
import java.util.List;

/**
 * Schedules matches for a tournament. setupMatches duplicates logic from TournamentManager.setupTournament.
 */
public class MatchScheduler {

    public void setupMatches(Tournament t) {
        // long duplicated method
        if (t.getRounds() == null) t.setRounds(new ArrayList<>());
        int numRounds = t.getRounds().size();
        if (numRounds == 0) {
            // create minimal default structure similar to TournamentManager
            int guessed = (int)Math.ceil(Math.log(t.getPlayers().size()) / Math.log(2)) + 1;
            for (int r = 1; r <= guessed; r++) {
                Round round = new Round(r);
                List<Player> players = new ArrayList<>(t.getPlayers());
                while (players.size() >= 2) {
                    Player a = players.remove(0);
                    Player b = players.remove(0);
                    Match m = new Match(a, b);
                    m.setRoundNumber(r);
                    round.getMatches().add(m);
                }
                if (!players.isEmpty()) {
                    Player p = players.remove(0);
                    Match bye = new Match(p, null);
                    bye.setRoundNumber(r);
                    round.getMatches().add(bye);
                }
                for (Match m : round.getMatches()) {
                    m.setTimeControl("90+30");
                }
                t.getRounds().add(round);
            }
        } else {
            // shuffle existing rounds in a verbose way
            for (Round round : t.getRounds()) {
                for (Match m : round.getMatches()) {
                    if (m.getTimeControl() == null) m.setTimeControl("60+0");
                }
            }
        }
    }
}
