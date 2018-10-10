package crapsBets;

import java.util.ArrayList;
import java.util.Arrays;


public class HardWaysBet extends CrapBet {
    private int number;
    private ArrayList<Integer> numberList;

    public HardWaysBet() {
        numberList = new ArrayList(Arrays.asList(4, 6, 8, 10));
        number = getRandomNumber();
        this.odd = getOddForNumber(number);
        this.sum = 5;
    }

    public HardWaysBet(int number) {
        numberList = new ArrayList(Arrays.asList(4, 6, 8, 10));
        this.number = number;
        this.odd = getOddForNumber(number);
    }

    private int getRandomNumber() {
        int index = (int) (Math.random() * 4);
        return numberList.get(index);
    }

    private int getOddForNumber(int num) {
        if (num == 4 || num == 10)
            return 8;
        else return 10;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "HardWaysBet{" +
                "sum = " + sum +
                ", odd = " + odd +
                ", number = " + number +
                '}';
    }
}
