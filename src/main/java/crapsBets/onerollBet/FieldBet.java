package crapsBets.onerollBet;

import casino.Dice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FieldBet extends PropositionsBet {

    FieldBet() {
        super();
        this.odd = 1;
        this.sum = 5;
    }

    @Override
    public boolean isWin(Dice dice) {
        List<Integer> list = new ArrayList<>(Arrays.asList(2, 3, 4, 9, 10, 11, 12));
        return list.contains(dice.getSum());
    }

    @Override
    public String toString() {
        return "FieldBet{" +
                "sum = " + sum +
                ", odd = " + odd +
                '}';
    }
}
