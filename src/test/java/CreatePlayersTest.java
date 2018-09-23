import casino.Dice;
import game.GameManager;
import game.ShooterService;
import players.Player;
import org.junit.Test;

public class CreatePlayersTest {

    public static void main(String[] args) {
        GameManager manager = new GameManager();
        manager.createPlayersForCrapsGame();
        System.out.println(manager.craps.players);
        ShooterService s = new ShooterService();
        Player shooter = s.chooseShooter(manager.craps.players, new Dice(2,2));
        System.out.println(shooter.toString());
        System.out.println(manager.craps.players);
        shooter = s.chooseShooter(manager.craps.players, new Dice(2,2));
        System.out.println(shooter.toString());
        System.out.println(manager.craps.players);
        shooter = s.chooseShooter(manager.craps.players, new Dice(2,2));
        System.out.println(shooter.toString());
        System.out.println(manager.craps.players);
        shooter = s.chooseShooter(manager.craps.players, new Dice(2,2));
        System.out.println(shooter.toString());
        System.out.println(manager.craps.players);



    }

    @Test
    public void checkPlayers() {
        GameManager manager = new GameManager();
        manager.createPlayersForCrapsGame();
        System.out.println(manager.craps.players);
    }
}
