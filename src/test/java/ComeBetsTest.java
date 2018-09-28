import crapsBets.ComeBets;
import crapsBets.CrapBet;
import org.junit.Assert;
import org.junit.Test;

public class ComeBetsTest {
    @Test
    public void betCreaionTest() {
        CrapBet comeBets = new ComeBets();
        Assert.assertTrue(comeBets.odd == 1 && comeBets.getSum() == 5);
    }

}
