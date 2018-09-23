package crapsBets;

public class ComeBets extends CrapBet {
    public int moveToPoint;
    // true comeBet false DontCome
    public boolean isComeBet;
    public ComeBets() {
        this.moveToPoint = 0;
        this.odd = 1;
        this.isComeBet = getRandomComeNotCome();
        this.sum = 5;
    }

    public ComeBets(boolean isComeBet, int moveToPoint) {
        this.moveToPoint = moveToPoint;
        this.odd = 1;
        this.isComeBet = isComeBet;
        this.sum = 5;
    }

    private boolean getRandomComeNotCome(){
        return (int)(Math.random()*2 + 1) == 1;
    }

    public void setMoveToPoint(int moveToPoint) {
        this.moveToPoint = moveToPoint;
    }

    public void setOdd(int newOdd){
        this.odd = newOdd;
    }

    @Override
    public String toString() {
        return "ComeBets{" +
                "moveToPoint = " + moveToPoint +
                ", isComeBet = " + isComeBet +
                ", sum = " + sum +
                ", odd = " + odd +
                '}';
    }
}
