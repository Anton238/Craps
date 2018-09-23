package crapsBets.onerollBet;

import casino.Dice;

import java.util.ArrayList;
import java.util.Arrays;

public class IndividualPropositionBet extends PropositionsBet {
    private int numer;
    private ArrayList<Integer> numerList;

    public IndividualPropositionBet() {
        numerList = new ArrayList(Arrays.asList(2, 3, 12, 11));
        numer = getRandomNumber();
        this.odd = getOddForNumber(numer);
        this.sum = 5;
    }

    public IndividualPropositionBet(int numer) {
        numerList = new ArrayList(Arrays.asList(2, 3, 12, 11));
        this.numer = numer;
        this.odd = getOddForNumber(numer);
    }

    @Override
    public boolean isWin(Dice dice) {
        return dice.getSum() == this.numer;
    }

    private int getRandomNumber() {
        int index = (int) (Math.random() * 4);
        return numerList.get(index);
    }

    private int getOddForNumber(int num) {
        if (num == 3 || num == 11) {
            return 16;
        } else return 31;
    }

    @Override
    public String toString() {
        return "IndividualPropositionBet{" +
                "number = " + numer +
                ", sum = " + sum +
                ", odd = " + odd +
                '}';
    }
}
