package com.chesslab;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple tournament manager. The setupTournament method is intentionally long and shares structure
 * with MatchScheduler.setupMatches to create duplicate logic.
 */
public class TournamentManager {
    private final List<Tournament> tournaments = new ArrayList<>();
    private final MatchScheduler scheduler = new MatchScheduler();

    public Tournament createSwiss(String name, List<Player> players) {
        Tournament t = new Tournament(name);
        t.setPlayers(players);
        // long setup
        setupTournament(t);
        tournaments.add(t);
        return t;
    }

    private void setupTournament(Tournament t) {
        // long method: assign rounds, pairings, time controls, and create matches
        t.setRounds(new ArrayList<>());
        int numRounds = (int) Math.ceil(Math.log(t.getPlayers().size()) / Math.log(2)) + 1;
        for (int r = 1; r <= numRounds; r++) {
            Round round = new Round(r);
            // pair players naively
            List<Player> ps = new ArrayList<>(t.getPlayers());
            while (ps.size() >= 2) {
                Player a = ps.remove(0);
                Player b = ps.remove(0);
                Match m = new Match(a, b);
                m.setRoundNumber(r);
                round.getMatches().add(m);
            }
            if (!ps.isEmpty()) {
                // bye
                Player alone = ps.remove(0);
                Match bye = new Match(alone, null);
                bye.setRoundNumber(r);
                round.getMatches().add(bye);
            }
            // assign time control defaults
            for (Match m : round.getMatches()) {
                m.setTimeControl("90+30");
            }
            t.getRounds().add(round);
        }

        // call scheduler for potential re-shuffling (scheduler contains duplicated code)
        scheduler.setupMatches(t);
    }
}
