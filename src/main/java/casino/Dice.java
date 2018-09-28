package casino;

public class Dice {
    private int number1;
    private int number2;
    private int sum;

    public Dice() {
    }

    public Dice(int number1, int number2) {
        this.number1 = number1;
        this.number2 = number2;
        this.sum = number1 + number2;
    }

    public int getRandomDicesResult() {
        number1 = (int) (1 + Math.random() * 6);
        number2 = (int) (1 + Math.random() * 6);
        sum = number1 + number2;
        return sum;
    }

    int getNumber1() {
        return number1;
    }

    int getNumber2() {
        return number2;
    }

    public int getSum() {
        return sum;
    }

    public boolean isHardWays() {
        return getNumber1() == getNumber2();
    }

    @Override
    public String toString() {
        return "Dice{" +
                "number1 = " + number1 +
                ", number2 = " + number2 +
                ", sum = " + sum +
                ", is double - " + isHardWays() +
                '}';
    }
}
