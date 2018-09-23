import casino.Dice;
import crapsBets.HardWaysBet;
import crapsBets.onerollBet.IndividualPropositionBet;
import crapsBets.onerollBet.PropositionBetRandomGenerator;
import org.junit.Test;

public class PropositionBetTest {
    @Test
    public void generateRandomProposBet() {
        PropositionBetRandomGenerator gen = new PropositionBetRandomGenerator();
        System.out.println(gen.getRandomPropositionBet().toString());
        System.out.println(gen.getRandomPropositionBet().toString());
        System.out.println(gen.getRandomPropositionBet().toString());
        System.out.println(gen.getRandomPropositionBet().toString());
        System.out.println(gen.getRandomPropositionBet().toString());
        System.out.println(gen.getRandomPropositionBet().toString());
        System.out.println(gen.getRandomPropositionBet().toString());
        System.out.println(gen.getRandomPropositionBet().toString());
        System.out.println(gen.getRandomPropositionBet().toString());
        System.out.println(gen.getRandomPropositionBet().toString());
    }

    @Test
    public void testIndivBetsCreation(){
        IndividualPropositionBet bet = new IndividualPropositionBet();
        System.out.println(bet);
        bet = new IndividualPropositionBet();
        System.out.println(bet);
        bet = new IndividualPropositionBet();
        System.out.println(bet);
        bet = new IndividualPropositionBet();
        System.out.println(bet);
        bet = new IndividualPropositionBet(2);
        System.out.println(bet);
        bet = new IndividualPropositionBet(3);
        System.out.println(bet);
        bet = new IndividualPropositionBet(11);
        System.out.println(bet);
        bet = new IndividualPropositionBet(12);
        System.out.println(bet);
    }

    @Test
    public void checkHardWaysBet(){
        HardWaysBet bet = new HardWaysBet();
        System.out.println(bet.toString());
        System.out.println( (new Dice(2,2)).isHardWays());
        bet = new HardWaysBet();
        System.out.println(bet.toString());
        System.out.println((new Dice(3,2)).isHardWays());
        bet = new HardWaysBet();
        System.out.println(bet.toString());
        System.out.println( (new Dice(3,3)).isHardWays());
    }


}
