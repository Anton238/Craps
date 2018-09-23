package game;

import casino.House;
import players.Player;

import java.util.Scanner;


public class GameManager {
    public CrapGame craps;
    private House house;


    public GameManager() {
        this.house = new House(5, 1000);
        this.craps = new CrapGame();
        house.addToGames(craps);

    }

    public static void main(String[] args) {
        GameManager gameManager = new GameManager();
        gameManager.createPlayersForCrapsGame();
        System.out.println(gameManager.craps.getPlayers());
        gameManager.craps.start(gameManager.house);

    }

    public void createPlayersForCrapsGame() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of auto players: ");
        int auto = scanner.nextInt();
        if (auto > 0) {
            System.out.print("Enter initial amount of money for Players: ");
            int initMoneyForPlayer = scanner.nextInt();
            for (int i = 0; i < auto; i++) {
                Player p = new Player(initMoneyForPlayer, house.getMinBet());
                craps.addPlayer(p);
            }
        }
    }
}
