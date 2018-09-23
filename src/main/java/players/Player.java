package players;

import casino.Dice;
import crapsBets.ComeBets;
import crapsBets.CrapBet;
import crapsBets.HardWaysBet;
import crapsBets.onerollBet.PropositionBetRandomGenerator;
import game.CrapGame;
import game.GameState;
import strategies.Cancellation;
import strategies.Martingale;
import strategies.Strategy;
import strategies.StrategyType;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Player {
    private Strategy strategy;
    private String name;
    private int playerMoney;
    private boolean isShooter;
    private HardWaysBet hardWaysBet;
    private ComeBets comeBets;

    public Player(int initialMoney, int baseBet) {
        this.name = PlayerNameGenerator.generateName();
        this.playerMoney = initialMoney;
        this.strategy = getRandomStrategy(baseBet);
    }

    public void setComeBets(ComeBets comeBets) {
        this.comeBets = comeBets;
    }

    public boolean isShooter() {
        return isShooter;
    }

    public void setShooter(boolean shooter) {
        isShooter = shooter;
    }

    public String getName() {
        return name;
    }

    public void setHardWaysBet(HardWaysBet hardWaysBet) {
        this.hardWaysBet = hardWaysBet;
    }

    private void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public List<CrapBet> getNextBets(int minBet, int maxBet, CrapGame game) {
        List<CrapBet> crapBets = new LinkedList<>();
        CrapBet passBet = getNextBet(minBet, maxBet, game);
        crapBets.add(passBet);

        if (hardWaysBet != null)
            crapBets.add(hardWaysBet);
        if (comeBets != null)
            crapBets.add(comeBets);

        addAdditionalBets(passBet, game, crapBets, minBet);
        return crapBets;
    }

    private CrapBet getNextBet(int minBet, int maxBet, CrapGame game) {

        if (playerMoney < minBet) {
            System.out.println(" PLAYER " + this + "HAVE NOT ENOUGH MONEY");
            game.removePlayer(this);
            return null;
        }

        CrapBet bet = strategy.nextBet(this);

        if (bet != null) {
            if (playerMoney == minBet) {
                bet.setSum(minBet);
                return bet;
            }
            if (bet.getSum() < minBet || bet.getSum() > maxBet) {
                resetStrategy();
                bet = getNextBet(minBet, maxBet, game);
            }
            if (bet.getSum() > playerMoney && bet.getSum() < minBet) {
                resetStrategy();
                bet = getNextBet(minBet, maxBet, game);
            }
        }
        return bet;
    }

    private Strategy getRandomStrategy(int baseBet) {
        int index = new Random().nextInt(StrategyType.values().length);
        StrategyType strategyType = StrategyType.values()[index];
        switch (strategyType) {
            case MARTINGALE:
                return new Martingale(baseBet);
            case CANCELLATION:
                return new Cancellation(baseBet);
        }
        return null;
    }

    private void resetStrategy() {
        setStrategy(getRandomStrategy(strategy.getBaseBet()));
    }

    public void transferMoney(int substract) {
        playerMoney = playerMoney - substract;
    }

    public void addMoney(int add) {
        playerMoney = playerMoney + add;
    }

    public int throwDice(Dice dice) {
        return dice.getRandomDicesResult();
    }

    private boolean IsWillingToDoAdditionalBets(int sumOfCurrentBet, int minBet) {
        if (this.playerMoney - sumOfCurrentBet - minBet >= 0) {
            return (int) (Math.random() * 2 + 1) == 1;
        }
        return false;
    }

    public int getPlayerMoney() {
        return playerMoney;
    }

    private void addAdditionalBets(CrapBet passBet, CrapGame game, List<CrapBet> crapBets, int minBet) {
        if (passBet != null && game.getState() == GameState.PointON) {
            if (comeBets == null && IsWillingToDoAdditionalBets(passBet.getSum(), minBet)) {
                comeBets = new ComeBets();
                crapBets.add(comeBets);
            }
            if (IsWillingToDoAdditionalBets(passBet.getSum(), minBet)) {
                crapBets.add(new PropositionBetRandomGenerator().getRandomPropositionBet());
            }
            if (hardWaysBet == null && IsWillingToDoAdditionalBets(passBet.getSum(), minBet)) {
                hardWaysBet = new HardWaysBet();
                crapBets.add(hardWaysBet);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        return getName().equals(player.getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    @Override
    public String toString() {
        return "RobotPlayer{" +
                "name = '" + name + '\'' +
                ", playerMoney = " + playerMoney +
                ", strategy = " + strategy.toString() +
                " isShooter - " + isShooter() +
                '}';
    }
}


