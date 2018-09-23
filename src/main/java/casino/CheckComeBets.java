package casino;

import crapsBets.ComeBets;
import game.CrapGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CheckComeBets {
    private List<Integer> points;
    private List<Integer> craps;
    private ComeBets winBet;
    private ComeBets loseBet;

    ComeBets getWinBet() {
        return winBet;
    }

    ComeBets getLoseBet() {
        return loseBet;
    }

    public CheckComeBets() {
        points = new ArrayList<>(Arrays.asList(4, 5, 6, 8, 9, 10));
        craps = new ArrayList<>(Arrays.asList(2, 3, 12));
    }


    void check(Dice dice, ComeBets bet, CrapGame game) {
        winBet = null;
        loseBet = null;
        if (bet.moveToPoint == 0) {
            if (craps.contains(dice.getSum())) {
                if (bet.isComeBet)
                    loseBet = bet;
                else
                    if (dice.getSum() == 2 || dice.getSum() == 3) {
                        winBet = bet;
                        winBet.setOdd(2);
                    }
            }
            if (dice.getSum() == 11) {
                if (bet.isComeBet) {
                    winBet = bet;
                    winBet.setOdd(2);
                } else loseBet = bet;
            }
            if (dice.getSum() == 7) {
                if (bet.isComeBet)
                    loseBet = bet;
                else {
                    winBet = bet;
                    winBet.setOdd(2);
                }
            }

        }

        if (points.contains(dice.getSum())) {
            if (game.getComePoint() == 0) {
                game.setComePoint(dice.getSum());
                bet.setMoveToPoint(dice.getSum());
            } else {
                if (bet.moveToPoint == dice.getSum()) {
                    if (bet.isComeBet)
                        winBet = bet;
                    else loseBet = bet;

                    game.setComePoint(0);
                    bet.setMoveToPoint(0);
                }

            }
        }
        if (dice.getSum() == 7 && bet.moveToPoint != 0) {
            loseBet = bet;
            game.setComePoint(0);
            bet.setMoveToPoint(0);
        }
    }
}
