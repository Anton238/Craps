package casino;

import crapsBets.ComeBets;
import crapsBets.CrapBet;
import crapsBets.HardWaysBet;
import crapsBets.PassBet;
import crapsBets.onerollBet.PropositionsBet;
import game.CrapGame;
import players.Player;
import statistics.PlayerTotalRollEntry;
import statistics.Statistics;
import statistics.StatisticsEntry;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Bank {
    private int totalAmount;
    private HashMap<Player, List<CrapBet>> playerBetMap;

    public Bank() {
        this.playerBetMap = new HashMap<>();
    }

    public void addPlayerBet(Player p, List<CrapBet> b) {
        playerBetMap.put(p, b);
    }

    public HashMap<Player, List<CrapBet>> getPlayerBetMap() {
        return playerBetMap;
    }

    public void clearBankBets() {
        playerBetMap.clear();
    }

    public void checkWin(Dice diceResult, CrapGame game) {
        CheckWinPassBet checkWinPassBet = new CheckWinPassBet();
        CheckComeBets checkComeBets = new CheckComeBets();
        HashSet<Player> winnerPlayersPassBets = new HashSet<>();
        HashSet<Player> looserPlayersPassBets = new HashSet<>();
        HashSet<PlayerTotalRollEntry> totalRollEntries = new HashSet<>();
        for (Map.Entry<Player, List<CrapBet>> entry : playerBetMap.entrySet()) {
            Player playerToCheck = entry.getKey();
            PlayerTotalRollEntry playerTotalRollEntry = new PlayerTotalRollEntry(playerToCheck);
            int countWinPassBet = 0;
            int countLosePassBet = 0;
            List<CrapBet> betsToCheck = entry.getValue();
            for (CrapBet bet : betsToCheck) {
                if (bet instanceof PassBet) {
                    checkWinPassBet.checkPassBets(diceResult, game, (PassBet) bet);
                }
                if (bet instanceof ComeBets) {
                    resolveComeBets(diceResult, (ComeBets) bet, game, playerToCheck, checkComeBets, playerTotalRollEntry);
                }
                if (bet instanceof PropositionsBet) {
                    resolvePropositionBet(diceResult, (PropositionsBet) bet, playerToCheck, playerTotalRollEntry);
                }
                if (bet instanceof HardWaysBet) {
                    resolveHardWaysBet(diceResult, (HardWaysBet) bet, playerToCheck, playerTotalRollEntry);
                }

                countWinPassBet = resolveWinPassBet(checkWinPassBet, countWinPassBet, playerToCheck, playerTotalRollEntry);
                countLosePassBet = resolveLosePassBet(checkWinPassBet, countLosePassBet, playerToCheck, playerTotalRollEntry);
            }
            if (countWinPassBet > 0) {
                winnerPlayersPassBets.add(playerToCheck);
            }
            if (countLosePassBet > 0) {
                looserPlayersPassBets.add(playerToCheck);
            }
            totalRollEntries.add(playerTotalRollEntry);

        }

        game.switchStateTo(checkWinPassBet.getShouldSwitchTo());
        System.out.println("switched " + checkWinPassBet.getShouldSwitchTo());
        System.out.println("CURRENT STATE " + game.getState());
        System.out.println("POINT IS " + game.getPoint());
        Dice d = new Dice(diceResult.getNumber1(), diceResult.getNumber2());
        Statistics.addStatisticEntry(new StatisticsEntry(d, winnerPlayersPassBets, looserPlayersPassBets));
        Statistics.addRollEntry(totalRollEntries);
    }

    private void payWinners(Player p, CrapBet bet, PlayerTotalRollEntry playerTotalRollEntry) {
        int sumToPayToWinner = 0;
        sumToPayToWinner += bet.getSum() * bet.getOdd();
        System.out.println("Player " + p.getName() + " won " + sumToPayToWinner + " with bet" + bet.toString());
        p.addMoney(sumToPayToWinner);
        totalAmount -= sumToPayToWinner;
        playerTotalRollEntry.addRemoveTotalAmount(sumToPayToWinner);

    }

    private void withdrawLoses(Player p, CrapBet bet, PlayerTotalRollEntry playerTotalRollEntry) {
        int sumToSubstractFromLoser = bet.getSum();
        System.out.println("Player " + p.getName() + " lose " + sumToSubstractFromLoser + " with bet" + bet.toString());
        p.transferMoney(sumToSubstractFromLoser);
        totalAmount += sumToSubstractFromLoser;
        playerTotalRollEntry.addRemoveTotalAmount(sumToSubstractFromLoser * (-1));
    }

    public void dealWithHouse(House house) {
        System.out.println("Bank TotalAmount after round: " + totalAmount);
        house.addHouseMoney(totalAmount);
        totalAmount = 0;
        System.out.println();
    }

    private int resolveWinPassBet(CheckWinPassBet checkWinPassBet, int countWinBet,
                                  Player playerToCheck, PlayerTotalRollEntry playerTotalRollEntry) {
        if (checkWinPassBet.getWinBet() != null) {
            countWinBet++;
            payWinners(playerToCheck, checkWinPassBet.getWinBet(), playerTotalRollEntry);
            checkWinPassBet.setWinBet(null);
        }
        return countWinBet;
    }

    private int resolveLosePassBet(CheckWinPassBet checkWinPassBet, int countLoseBet,
                                   Player playerToCheck, PlayerTotalRollEntry playerTotalRollEntry) {
        if (checkWinPassBet.getLoseBet() != null) {
            countLoseBet++;
            withdrawLoses(playerToCheck, checkWinPassBet.getLoseBet(), playerTotalRollEntry);
            checkWinPassBet.setLoseBet(null);
        }
        return countLoseBet;
    }

    public void resolveComeBets(Dice dice, ComeBets bet, CrapGame game,
                                Player playerTocheck, CheckComeBets checkComeBets,
                                PlayerTotalRollEntry playerTotalRollEntry) {
        checkComeBets.check(dice, bet, game);
        if ((checkComeBets.getWinBet()) != null) {
            payWinners(playerTocheck, checkComeBets.getWinBet(), playerTotalRollEntry);
            playerTocheck.setComeBets(null);
        }
        if (checkComeBets.getLoseBet() != null) {
            withdrawLoses(playerTocheck, bet, playerTotalRollEntry);
            playerTocheck.setComeBets(null);
        }

    }

    private void resolvePropositionBet(Dice diceResult, PropositionsBet propBet,
                                       Player playerToCheck, PlayerTotalRollEntry playerTotalRollEntry) {
        if (propBet.isWin(diceResult)) {
            payWinners(playerToCheck, propBet, playerTotalRollEntry);
        } else {
            withdrawLoses(playerToCheck, propBet, playerTotalRollEntry);
        }
    }

    public void resolveHardWaysBet(Dice diceResult, HardWaysBet bet,
                                   Player playerToCheck, PlayerTotalRollEntry playerTotalRollEntry) {
        if (isExactHardWaysMatch(diceResult, bet)) {
            payWinners(playerToCheck, bet, playerTotalRollEntry);
            playerToCheck.setHardWaysBet(null);
        }
        if (diceResult.getSum() == 7) {
            withdrawLoses(playerToCheck, bet, playerTotalRollEntry);
            playerToCheck.setHardWaysBet(null);
        }
        if ((!diceResult.isHardWays()) && (diceResult.getSum() == bet.getNumer())) {
            withdrawLoses(playerToCheck, bet, playerTotalRollEntry);
            playerToCheck.setHardWaysBet(null);
        }
    }

    private boolean isExactHardWaysMatch(Dice diceResult, HardWaysBet bet) {
        return diceResult.isHardWays() && ((bet.getNumer() / 2) == diceResult.getNumber2());
    }
}