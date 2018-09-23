package crapsBets.onerollBet;

import casino.Dice;
import crapsBets.CrapBet;

public abstract class PropositionsBet extends CrapBet {

    public abstract boolean isWin(Dice dice);

}
