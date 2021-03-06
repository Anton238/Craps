package strategies;

import crapsBets.CrapBet;
import crapsBets.PassBet;
import players.Player;

public abstract class Strategy {
    public int baseBet;

    Strategy(int baseBet) {
        this.baseBet = baseBet;
    }

    public abstract CrapBet nextBet(Player p);

    public abstract CrapBet getNextBetType();

    public abstract int nextBetSum(Player p);

    public int getBaseBet() {
        return baseBet;
    }

    public CrapBet getNextBetTypeDef() {
        return new PassBet((int) (Math.random() * 2 + 1));
    }
}
