import casino.CheckWinPassBet;
import casino.Dice;
import crapsBets.PassBet;
import game.CrapGame;
import game.GameState;
import org.junit.Assert;
import org.junit.Test;

public class SimulategameTest {
    @Test
    public void fixedDicePointTest(){
        CrapGame game = new CrapGame();
        PassBet passBet = new PassBet(1);
        passBet.setSum(5);
        CheckWinPassBet checkWinPassBet = new CheckWinPassBet();
        Dice d1 = new Dice(2,2);
        checkWinPassBet.checkPassBets(d1, game, passBet);
        Assert.assertTrue(checkWinPassBet.getLoseBet() == null);
        Assert.assertTrue(checkWinPassBet.getWinBet() == null);
        Assert.assertTrue(checkWinPassBet.getShouldSwitchTo() == GameState.PointON);

    }
}
