package statistics;

import players.Player;

public class PlayerTotalRollEntry {
    private Player player;
    private int totalSum;

    public PlayerTotalRollEntry(Player p) {
        this.player = p;
        this.totalSum = 0;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    int getTotalSum() {
        return totalSum;
    }

    public void addRemoveTotalAmount(int sum) {
        this.totalSum = totalSum + sum;
    }

    @Override
    public String toString() {
        return "PlayerTotalRollEntry{" +
                "player = " + player +
                ", totalSum = " + totalSum +
                '}';
    }
}


