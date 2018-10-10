package crapsBets.onerollBet;

import casino.Dice;

import java.util.ArrayList;
import java.util.Arrays;

public class AnyHorn extends PropositionsBet {
    AnyHorn() {
        super();
        this.odd = 8;
        this.sum = 5;
    }

    @Override
    public boolean isWin(Dice dice) {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(2, 3, 11, 12));
        return list.contains(dice.getSum());
    }

    @Override
    public String toString() {
        return "AnyHorn{" +
                "sum = " + sum +
                ", odd = " + odd +
                '}';
    }
}
