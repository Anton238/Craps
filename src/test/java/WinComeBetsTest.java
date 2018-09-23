import casino.Bank;
import casino.CheckComeBets;
import casino.Dice;
import crapsBets.ComeBets;
import game.CrapGame;
import players.Player;
import statistics.PlayerTotalRollEntry;
import org.junit.Assert;
import org.junit.Test;

public class WinComeBetsTest {

    @Test
    public void checkCrapsComeBet(){
        CheckComeBets checkComeBets = new CheckComeBets();
        CrapGame game = new CrapGame();
        Player player = new Player(50, 5);
        PlayerTotalRollEntry entry = new PlayerTotalRollEntry(player);
        ComeBets bet = new ComeBets(true,0);
        player.setComeBets(bet);
        Bank bank = new Bank();
        Dice dice = new Dice(1,1);
        bank.resolveComeBets(dice,  bet,  game, player,  checkComeBets, entry);
        Assert.assertTrue(player.getPlayerMoney() == 45);

        dice = new Dice(6,6);
        bank.resolveComeBets(dice,  bet,  game, player,  checkComeBets, entry);
        Assert.assertTrue(player.getPlayerMoney() == 40);

        dice = new Dice(1,2);
        bank.resolveComeBets(dice,  bet,  game, player,  checkComeBets, entry);
        Assert.assertTrue(player.getPlayerMoney() == 35);
    }

    @Test
    public void checkCrapsDontComeBet(){
        CheckComeBets checkComeBets = new CheckComeBets();
        CrapGame game = new CrapGame();
        Player player = new Player(50, 5);
        PlayerTotalRollEntry entry = new PlayerTotalRollEntry(player);
        ComeBets bet = new ComeBets(false,0);
        player.setComeBets(bet);
        Bank bank = new Bank();
        Dice dice = new Dice(1,1);
        bank.resolveComeBets(dice,  bet,  game, player,  checkComeBets, entry );
        Assert.assertTrue(player.getPlayerMoney() == 60);

        dice = new Dice(6,6);
        bank.resolveComeBets(dice,  bet,  game, player,  checkComeBets, entry);
        System.out.println(player.getPlayerMoney());
        Assert.assertTrue(player.getPlayerMoney() == 60);

        dice = new Dice(1,2);
        bank.resolveComeBets(dice,  bet,  game, player,  checkComeBets ,entry);
        Assert.assertTrue(player.getPlayerMoney() == 70);
    }

    @Test
    public void check7Come(){
        CheckComeBets checkComeBets = new CheckComeBets();
        CrapGame game = new CrapGame();
        Player player = new Player(50, 5);
        PlayerTotalRollEntry entry = new PlayerTotalRollEntry(player);
        ComeBets bet = new ComeBets(true,0);
        player.setComeBets(bet);
        Bank bank = new Bank();
        Dice dice = new Dice(1,6);
        bank.resolveComeBets(dice,  bet,  game, player,  checkComeBets,entry);
        Assert.assertTrue(player.getPlayerMoney() == 45);
    }

    @Test
    public void check11Come(){
        CheckComeBets checkComeBets = new CheckComeBets();
        CrapGame game = new CrapGame();
        Player player = new Player(50, 5);
        PlayerTotalRollEntry entry = new PlayerTotalRollEntry(player);
        ComeBets bet = new ComeBets(true,0);
        player.setComeBets(bet);
        Bank bank = new Bank();
        Dice dice = new Dice(5,6);
        bank.resolveComeBets(dice,  bet,  game, player,  checkComeBets, entry);
        Assert.assertTrue(player.getPlayerMoney() == 60);
    }

    @Test
    public void check7DontCome(){
        CheckComeBets checkComeBets = new CheckComeBets();
        CrapGame game = new CrapGame();
        Player player = new Player(50, 5);
        PlayerTotalRollEntry entry = new PlayerTotalRollEntry(player);
        ComeBets bet = new ComeBets(false,0);
        player.setComeBets(bet);
        Bank bank = new Bank();
        Dice dice = new Dice(1,6);
        bank.resolveComeBets(dice,  bet,  game, player,  checkComeBets, entry);
        Assert.assertTrue(player.getPlayerMoney() == 60);
    }


    @Test
    public void check11DontCome(){
        CheckComeBets checkComeBets = new CheckComeBets();
        CrapGame game = new CrapGame();
        Player player = new Player(50, 5);
        PlayerTotalRollEntry entry = new PlayerTotalRollEntry(player);
        ComeBets bet = new ComeBets(false,0);
        player.setComeBets(bet);
        Bank bank = new Bank();
        Dice dice = new Dice(5,6);
        bank.resolveComeBets(dice,  bet,  game, player,  checkComeBets ,entry);
        Assert.assertTrue(player.getPlayerMoney() == 45);
    }

    @Test
    public void checkComePoint(){
        CheckComeBets checkComeBets = new CheckComeBets();
        CrapGame game = new CrapGame();
        Player player = new Player(50, 5);
        PlayerTotalRollEntry entry = new PlayerTotalRollEntry(player);
        ComeBets bet = new ComeBets(true,0);
        player.setComeBets(bet);
        Bank bank = new Bank();
        Dice dice = new Dice(2,2);
        bank.resolveComeBets(dice,  bet,  game, player,  checkComeBets ,entry);
        Assert.assertTrue(player.getPlayerMoney() == 50);
        Assert.assertTrue(bet.moveToPoint == 4);
        Assert.assertTrue(game.getComePoint() == 4);

        dice = new Dice(2,2);
        bank.resolveComeBets(dice,  bet,  game, player,  checkComeBets ,entry);
        Assert.assertTrue(player.getPlayerMoney() == 55);
        Assert.assertTrue(bet.moveToPoint == 0);
        Assert.assertTrue(game.getComePoint() == 0);

        dice = new Dice(2,5);
        bank.resolveComeBets(dice,  bet,  game, player,  checkComeBets ,entry);
        Assert.assertTrue(player.getPlayerMoney() == 50);
        Assert.assertTrue(bet.moveToPoint == 0);
        Assert.assertTrue(game.getComePoint() == 0);

    }


    @Test
    public void checkDontComePoint(){
        CheckComeBets checkComeBets = new CheckComeBets();
        CrapGame game = new CrapGame();
        Player player = new Player(50, 5);
        ComeBets bet = new ComeBets(false,0);
        PlayerTotalRollEntry entry = new PlayerTotalRollEntry(player);
        player.setComeBets(bet);
        Bank bank = new Bank();
        Dice dice = new Dice(2,2);
        bank.resolveComeBets(dice,  bet,  game, player,  checkComeBets, entry);
        Assert.assertTrue(player.getPlayerMoney() == 50);
        Assert.assertTrue(bet.moveToPoint == 4);
        Assert.assertTrue(game.getComePoint() == 4);

        dice = new Dice(2,2);
        bank.resolveComeBets(dice,  bet,  game, player,  checkComeBets, entry);
        Assert.assertTrue(player.getPlayerMoney() == 45);
        Assert.assertTrue(bet.moveToPoint == 0);
        Assert.assertTrue(game.getComePoint() == 0);


        bet.setMoveToPoint(6);
        dice = new Dice(2,5);
        bank.resolveComeBets(dice,  bet,  game, player,  checkComeBets, entry);
        Assert.assertTrue(player.getPlayerMoney() == 40);
        Assert.assertTrue(bet.moveToPoint == 0);
        Assert.assertTrue(game.getComePoint() == 0);

    }
}
