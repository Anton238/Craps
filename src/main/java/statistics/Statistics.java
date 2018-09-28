package statistics;


import players.Player;

import java.util.HashSet;
import java.util.LinkedList;

public class Statistics {

    final private static int CAPACITY = 7;
    final private static int CAPACITY2 = 2;
    private static LinkedList<StatisticsEntry> statistics = new LinkedList<>();
    private static LinkedList<HashSet<PlayerTotalRollEntry>> playerRollMoney = new LinkedList<>();

    private static void removeFirst() {
        statistics.removeFirst();
    }

    private static StatisticsEntry getLast() {
        return statistics.getLast();
    }

    public static LinkedList<StatisticsEntry> getStatistics() {
        return statistics;
    }

    public static LinkedList<HashSet<PlayerTotalRollEntry>> getPlayerRollMoney() {
        return playerRollMoney;
    }

    public static boolean isLastWin(Player p) {
        return Statistics.getLast().isWinner(p);
    }

    public static boolean isLastLose(Player p) {
        return Statistics.getLast().isLooser(p);
    }

    public static void addStatisticEntry(StatisticsEntry entry) {
        if (entry != null)
            statistics.add(entry);
        if (statistics.size() > CAPACITY)
            removeFirst();
    }

    public static void addRollEntry(HashSet<PlayerTotalRollEntry> entry) {
        if (entry != null)
            playerRollMoney.add(entry);
        if (playerRollMoney.size() > CAPACITY2)
            playerRollMoney.removeFirst();
    }

    public static boolean isLastRollWin(Player p) {
        if (playerRollMoney.size() > 0) {
            HashSet<PlayerTotalRollEntry> entry = playerRollMoney.getLast();
            for (PlayerTotalRollEntry e : entry) {
                if (e.getPlayer().equals(p)) {
                    return e.getTotalSum() >= 0;
                }
            }
        }
        return false;
    }


}
