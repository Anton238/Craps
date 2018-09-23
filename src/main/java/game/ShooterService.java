package game;

import casino.Dice;
import players.Player;
import statistics.Statistics;

import java.util.Iterator;
import java.util.List;

public class ShooterService {

    public ShooterService() {
    }

    public Player chooseShooter(List<Player> players, Dice dice) {
        Player oldShooter = returnExistingShooter(players);
        Player newShooter = null;
        if (oldShooter == null) {
            newShooter = createInitialShooter(players, dice);
            return newShooter;
        } else {
            if (Statistics.isLastRollWin(oldShooter))
                return oldShooter;
            else {
                oldShooter.setShooter(false);
                newShooter = nextPlayer(oldShooter, players);
                newShooter.setShooter(true);
                return newShooter;
            }
        }
    }

    private Player returnExistingShooter(List<Player> players) {
        for (Player p : players)
            if (p.isShooter()) return p;

        return null;
    }

    private Player nextPlayer(Player player, List<Player> players) {
        Iterator<Player> playerIterator = players.iterator();
        while (playerIterator.hasNext()) {
            if (playerIterator.next().equals(player))
                if (playerIterator.hasNext())
                    return playerIterator.next();
                else return players.get(0);
        }
        return players.get(0);
    }

    private Player createInitialShooter(List<Player> players, Dice dice) {
        int maxResult = 0;
        Player shooter = null;
        for (Player p : players) {
            int currentResult = dice.getRandomDicesResult();
            if (maxResult < currentResult) {
                maxResult = currentResult;
                shooter = p;
            }
        }
        if (shooter != null)
            shooter.setShooter(true);

        return shooter;
    }

}
