package casino;


import game.Game;

import java.util.LinkedList;
import java.util.List;

public class House {
    private int maxBet;
    private int minBet;
    private int houseMoney;
    private List<Game> games;

    public House(int minBet, int maxBet) {
        games = new LinkedList<>();
        this.maxBet = maxBet;
        this.minBet = minBet;
    }

    public int getMaxBet() {
        return maxBet;
    }

    public int getMinBet() {
        return minBet;
    }

    public int getHouseMoney() {
        return houseMoney;
    }

    void addHouseMoney(int add) {
        this.houseMoney += add;
    }

    public void addToGames(Game game) {
        games.add(game);
    }
}
