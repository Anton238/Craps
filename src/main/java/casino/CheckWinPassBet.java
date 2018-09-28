package casino;

import crapsBets.PassBet;
import game.CrapGame;
import game.GameState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckWinPassBet {
    private List<Integer> points;
    private List<Integer> craps;
    private GameState shouldSwitchTo;
    private PassBet winBet;
    private PassBet loseBet;


    public CheckWinPassBet() {
        points = new ArrayList<>(Arrays.asList(4, 5, 6, 8, 9, 10));
        craps = new ArrayList<>(Arrays.asList(2, 3, 12));
        this.shouldSwitchTo = GameState.PointOff;
    }

    public PassBet getWinBet() {
        return winBet;
    }

    void setWinBet(PassBet winBet) {
        this.winBet = winBet;
    }

    public PassBet getLoseBet() {
        return loseBet;
    }

    void setLoseBet(PassBet loseBet) {
        this.loseBet = loseBet;
    }

    public GameState getShouldSwitchTo() {
        return shouldSwitchTo;
    }

    public void checkPassBets(Dice dice, CrapGame game, PassBet passBet) {
        shouldSwitchTo = game.getState();
        if (game.getState() == GameState.PointOff) {
            if (points.contains(dice.getSum())) {
                game.setPoint(dice.getSum());
                shouldSwitchTo = GameState.PointON;
            }
            winBet = null;
            loseBet = null;
            if (craps.contains(dice.getSum())) {
                if (passBet.getPassOrNotType() == 2)
                    winBet = passBet;
                else
                    loseBet = passBet;
            }
            if (dice.getSum() == 7 || dice.getSum() == 11) {
                if (passBet.getPassOrNotType() == 1)
                    winBet = passBet;
                else
                    loseBet = passBet;
            }

        }

        if (game.getState() == GameState.PointON) {
            winBet = null;
            loseBet = null;
            if (dice.getSum() == 7) {
                shouldSwitchTo = GameState.PointOff;
                loseBet = passBet;
                game.setPoint(0);
            }
            if (dice.getSum() == game.getPoint()) {
                if (passBet.getPassOrNotType() == 1)
                    winBet = passBet;
                else loseBet = passBet;

                shouldSwitchTo = GameState.PointOff;
                System.out.println("state changed in point = dice");
            }
        }
    }

    @Override
    public String toString() {
        return "CheckWinPassBet{" +
                "shouldSwitchTo = " + shouldSwitchTo +
                ", winBet = " + winBet +
                ", loseBet = " + loseBet +
                '}';
    }
}
