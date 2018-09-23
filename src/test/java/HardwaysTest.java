import casino.Bank;
import casino.Dice;
import crapsBets.HardWaysBet;
import game.CrapGame;
import players.Player;
import statistics.PlayerTotalRollEntry;
import org.junit.Assert;
import org.junit.Test;

public class HardwaysTest {
    @Test
    public void testExactHardways(){

        Dice dice = new Dice(2,2);
        Player p = new Player(20,5);
        PlayerTotalRollEntry entry = new PlayerTotalRollEntry(p);
        HardWaysBet bet = new HardWaysBet(4);
        bet.setSum(10);
        Bank bank = new Bank();
        bank.resolveHardWaysBet(dice, bet, p, entry );
        System.out.println(p.toString());
        Assert.assertTrue(p.getPlayerMoney() == 20 + (bet.getSum() * bet.getOdd()));
    }

    @Test
    public void testSummNotHardways(){
        Dice dice = new Dice(1,3);
        Player p = new Player(20,5);
        PlayerTotalRollEntry entry = new PlayerTotalRollEntry(p);
        HardWaysBet bet = new HardWaysBet(4);
        bet.setSum(10);
        Bank bank = new Bank();
        bank.resolveHardWaysBet(dice, bet, p, entry );
        System.out.println(p.toString());
        Assert.assertTrue(p.getPlayerMoney() == 10);
    }

    @Test
    public void testNotWinNoLoseHardways(){
        Dice dice = new Dice(2,6);
        Player p = new Player(20,5);
        PlayerTotalRollEntry entry = new PlayerTotalRollEntry(p);
        HardWaysBet bet = new HardWaysBet(4);
        bet.setSum(10);
        Bank bank = new Bank();
        bank.resolveHardWaysBet(dice, bet, p, entry );
        System.out.println(p.toString());
        Assert.assertTrue(p.getPlayerMoney() == 20);
    }

    @Test
    public void test7InHardways(){
        Dice dice = new Dice(1,6);
        Player p = new Player(20,5);
        PlayerTotalRollEntry entry = new PlayerTotalRollEntry(p);
        HardWaysBet bet = new HardWaysBet(4);
        bet.setSum(10);
        Bank bank = new Bank();
        bank.resolveHardWaysBet(dice, bet, p, entry );
        System.out.println(p.toString());
        Assert.assertTrue(p.getPlayerMoney() == 10);
    }

    @Test
    public void remainHardBetAfterPoint(){
        CrapGame game = new CrapGame();
        Dice dice = new Dice(5,4);
        HardWaysBet bet = new HardWaysBet(4);
        bet.setSum(5);
        Player p = new Player(20,5);
        PlayerTotalRollEntry entry = new PlayerTotalRollEntry(p);
        p.setHardWaysBet(bet);
        Bank bank = new Bank();
        bank.resolveHardWaysBet(dice, bet,p, entry);
        Assert.assertTrue(p.getPlayerMoney() == 20);
        System.out.println(p.getNextBets(5, 1000, game));

    }

    @Test
    public void remainHardBetAfterPoint1(){
        CrapGame game = new CrapGame();
        Dice dice = new Dice(4,4);
        HardWaysBet bet = new HardWaysBet(8);
        bet.setSum(5);
        Player p = new Player(20,5);
        PlayerTotalRollEntry entry = new PlayerTotalRollEntry(p);
        p.setHardWaysBet(bet);
        Bank bank = new Bank();
        bank.resolveHardWaysBet(dice, bet, p, entry);
        Assert.assertTrue(p.getPlayerMoney() == 70);
        System.out.println(p.getNextBets(5, 1000, game));

    }
    @Test
    public void remainHardBetAfterSummNotMatch(){
        CrapGame game = new CrapGame();
        Dice dice = new Dice(5,3);
        HardWaysBet bet = new HardWaysBet(8);
        bet.setSum(5);
        Player p = new Player(20,5);
        PlayerTotalRollEntry entry = new PlayerTotalRollEntry(p);
        p.setHardWaysBet(bet);
        Bank bank = new Bank();
        bank.resolveHardWaysBet(dice, bet, p, entry);
        Assert.assertTrue(p.getPlayerMoney() == 15);
        System.out.println(p.getNextBets(5, 1000, game));

    }
}
