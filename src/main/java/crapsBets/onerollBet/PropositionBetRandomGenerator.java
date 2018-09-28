package crapsBets.onerollBet;

import java.util.ArrayList;
import java.util.Arrays;


public class PropositionBetRandomGenerator {

    private ArrayList<PropositionsBet> propositionBetList = new ArrayList<>
            (Arrays.asList(
                    new AnyCraps(),
                    new AnyHorn(),
                    new FieldBet(),
                    new SevenBet(),
                    new IndividualPropositionBet()));

    public PropositionsBet getRandomPropositionBet() {
        int index = (int) (Math.random() * 5);
        return propositionBetList.get(index);
    }
}
