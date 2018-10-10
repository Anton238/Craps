package crapsBets.onerollBet;

import casino.Dice;

import java.util.ArrayList;
import java.util.Arrays;

public class AnyCraps extends PropositionsBet {
    AnyCraps() {
        super();
        this.odd = 8;
        this.sum = 5;
    }

    @Override
    public boolean isWin(Dice dice) {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(12, 2, 3));
        return list.contains(dice.getSum());
    }

    @Override
    public String toString() {
        return "AnyCraps{" +
                "sum = " + sum +
                ", odd = " + odd +
                '}';
    }
}
