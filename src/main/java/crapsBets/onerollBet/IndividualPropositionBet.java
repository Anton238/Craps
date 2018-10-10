package crapsBets.onerollBet;

import casino.Dice;

import java.util.ArrayList;
import java.util.Arrays;

public class IndividualPropositionBet extends PropositionsBet {
    private int number;
    private ArrayList<Integer> numberList;

    public IndividualPropositionBet() {
        numberList = new ArrayList(Arrays.asList(2, 3, 12, 11));
        number = getRandomNumber();
        this.odd = getOddForNumber(number);
        this.sum = 5;
    }

    public IndividualPropositionBet(int number) {
        numberList = new ArrayList(Arrays.asList(2, 3, 12, 11));
        this.number = number;
        this.odd = getOddForNumber(number);
    }

    @Override
    public boolean isWin(Dice dice) {
        return dice.getSum() == this.number;
    }

    private int getRandomNumber() {
        int index = (int) (Math.random() * 4);
        return numberList.get(index);
    }

    private int getOddForNumber(int num) {
        if (num == 3 || num == 11) {
            return 16;
        } else return 31;
    }

    @Override
    public String toString() {
        return "IndividualPropositionBet{" +
                "number = " + number +
                ", sum = " + sum +
                ", odd = " + odd +
                '}';
    }
}
