package game;

import casino.Bank;
import casino.Dice;
import casino.House;
import crapsBets.CrapBet;
import players.Player;
import statistics.PlayerTotalRollEntry;
import statistics.Statistics;
import statistics.StatisticsEntry;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CrapGame extends Game {
    public List<Player> players;
    private Dice dice;
    private Bank bank;
    private GameState state;
    private int point;
    private int comePoint;
    private ShooterService shooterService;


    public CrapGame() {
        this.players = new LinkedList<>();
        this.dice = new Dice();
        this.bank = new Bank();
        this.state = GameState.PointOff;
        this.shooterService = new ShooterService();

    }

    public GameState getState() {
        return state;
    }

    public int getComePoint() {
        return comePoint;
    }

    public void setComePoint(int comePoint) {
        this.comePoint = comePoint;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void switchStateTo(GameState newState) {
        if (state != newState) state = newState;

    }

    public List<Player> getPlayers() {
        return players;
    }

    void addPlayer(Player p) {
        players.add(p);
    }

    @Override
    public void start(House house) {
        Player shooter;

        while (players.size() > 0) {
            shooter = shooterService.chooseShooter(players, dice);
            int currSize;
            for (int i = 0; i < players.size() && players.size() != 0; i++) {
                currSize = players.size();
                Player p = players.get(i);
                collectBet(p, house.getMinBet(), house.getMaxBet());
                if (currSize != players.size())
                    i--;
            }

            for (Map.Entry<Player, List<CrapBet>> entry : bank.getPlayerBetMap().entrySet()) {
                System.out.println("Bank has:");
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
            System.out.println();

            if (players.size() == 0) {
                bank.dealWithHouse(house);
                System.out.println("Game over");
                break;
            }

            int diceResult = shooter.throwDice(dice);
            System.out.println("Dice result: " + diceResult + ", double: " + dice.isHardWays());
            bank.checkWin(dice, this);
            // change shooter if nessesary
            bank.dealWithHouse(house);
            bank.clearBankBets();

            System.out.println("Players after cycle:");
            for (Player p : players)
                System.out.println(p);
            System.out.println();

            System.out.println("Full Statistics:");
            for (StatisticsEntry entry : Statistics.getStatistics())
                System.out.println(entry.toString());
            System.out.println();

            System.out.println("Statistics for players money in roll:");
            for (HashSet<PlayerTotalRollEntry> entry : Statistics.getPlayerRollMoney())
                System.out.println(entry.toString());
            System.out.println();

            System.out.println("MONEY IN HOUSE = " + house.getHouseMoney());
        }
    }

    private void collectBet(Player p, int minBet, int maxBet) {
        List<CrapBet> bets = p.getNextBets(minBet, maxBet, this);
        if (bets == null)
            return;
        bank.addPlayerBet(p, bets);
    }

    public void removePlayer(Player p) {
        players.remove(p);
    }


}
