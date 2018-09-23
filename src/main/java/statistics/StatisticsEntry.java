package statistics;

import casino.Dice;
import players.Player;

import java.util.HashSet;

public class StatisticsEntry {
    private Dice dice;

    private HashSet<Player> winPlayers;
    private HashSet<Player> losePlayers;

    public StatisticsEntry(Dice dice, HashSet<Player> winPlayers, HashSet<Player> losePlayers) {
        this.dice = dice;
        this.winPlayers = winPlayers;
        this.losePlayers = losePlayers;
    }

    boolean isWinner(Player p) {
        return winPlayers.contains(p);
    }

    boolean isLooser(Player p) {
        return losePlayers.contains(p);
    }


    @Override
    public String toString() {
        return "StatisticsEntry{" +
                "winCell = " + dice.toString() +
                ", winPlayers = " + winPlayers.toString() +
                ", losePlayers = " + losePlayers.toString() +
                '}';
    }
}
