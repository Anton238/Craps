package crapsBets.onerollBet;

import casino.Dice;

public class SevenBet extends PropositionsBet {
    SevenBet() {
        super();
        this.odd = 4;
        this.sum = 5;
    }

    @Override
    public boolean isWin(Dice dice) {
        return dice.getSum() == 7;
    }

    @Override
    public String toString() {
        return "SevenBet{" +
                "sum = " + sum +
                ", odd = " + odd +
                '}';
    }
}
