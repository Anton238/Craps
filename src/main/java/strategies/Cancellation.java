package strategies;

import crapsBets.CrapBet;
import players.Player;
import statistics.Statistics;

import java.util.Arrays;
import java.util.LinkedList;

public class Cancellation extends Strategy{

    final LinkedList<Integer> baseNums;
    LinkedList<Integer> currentNums;

    public Cancellation(int baseBet) {
        super(baseBet);
        baseNums = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        this.currentNums = new LinkedList<>(baseNums);
    }

    @Override
    public CrapBet nextBet(Player p) {
        CrapBet nextBet = getNextBetType();
        nextBet.setSum(nextBetSum(p));
        return nextBet;
    }

    @Override
    public CrapBet getNextBetType() {
        // random type
        return super.getNextBetTypeDef();
    }

    @Override
    public int nextBetSum(Player p) {
        if (Statistics.getStatistics().size() > 0) {
            if (currentNums.size() < 2)
                currentNums = new LinkedList<>(baseNums);
            if (Statistics.isLastWin(p)) {
                currentNums.removeFirst();
                currentNums.removeLast();
            } else {
                if(Statistics.isLastLose(p))
                    currentNums.add(currentNums.getFirst() + currentNums.getLast());
            }
        }
        return currentNums.getFirst() + currentNums.getLast();
    }

    @Override
    public String toString() {
        return "Cancellation{" +
                '}';
    }
}

