package crapsBets;


public abstract class CrapBet {
    public int sum;
    public int odd; //1:x

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getOdd() {
        return odd;
    }
}
