package crapsBets;

import java.util.ArrayList;
import java.util.Arrays;


public class HardWaysBet extends CrapBet {
    private int numer;
    private ArrayList<Integer>  numerList;

    public HardWaysBet() {
        numerList = new ArrayList(Arrays.asList(4, 6, 8, 10));
        numer = getRandomNumber();
        this.odd = getOddForNumber(numer);
        this.sum = 5;
    }

    public HardWaysBet(int numer) {
        numerList = new ArrayList(Arrays.asList(4, 6, 8, 10));
        this.numer = numer;
        this.odd = getOddForNumber(numer);
    }

    private int getRandomNumber() {
        int index = (int) (Math.random() * 4);
        return numerList.get(index);
    }

    private int getOddForNumber(int num) {
        if (num == 4 || num == 10)
            return 8;
        else return 10;
    }

    public int getNumer() {
        return numer;
    }

    @Override
    public String toString() {
        return "HardWaysBet{" +
                "sum = " + sum +
                ", odd = " + odd +
                ", number = " + numer +
                '}';
    }
}
