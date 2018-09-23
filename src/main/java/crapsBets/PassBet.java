package crapsBets;


public class PassBet extends CrapBet {

    private int passOrNotType; //1 pass 2 not pass

    public PassBet(int passOrNot) {
        this.passOrNotType = passOrNot;
        odd = 2;
    }

    public int getPassOrNotType() {
        return passOrNotType;
    }

    @Override
    public String toString() {
        return "PassBet{" +
                "sum = " + sum +
                ", odd = " + odd +
                " pass/no 1 or 2 - " + passOrNotType +
                '}';
    }
}

