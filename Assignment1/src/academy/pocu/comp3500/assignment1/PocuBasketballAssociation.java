package academy.pocu.comp3500.assignment1;

import academy.pocu.comp3500.assignment1.pba.Player;
import academy.pocu.comp3500.assignment1.pba.GameStat;

import academy.pocu.comp3500.assignment1.utils.processGameStatsUtil;

public final class PocuBasketballAssociation {
    private PocuBasketballAssociation() {
    }

    public static void processGameStats(final GameStat[] gameStats, final Player[] outPlayers) {
        processGameStatsUtil.quickSort(gameStats);

        int outIndex = 0;
        int gameCount = 0;
        String name = "";
        int sumPoints = 0, sumAssists = 0, sumPasses = 0, sumGoals = 0, sumAttempts = 0;
        for (int i = 0; i < gameStats.length; i++) {
            if (i > 1 && !gameStats[i].getPlayerName().equals(name)) {
                processGameStatsUtil.updateGameStat(outPlayers[outIndex], gameCount, name, 
                    sumPoints, sumAssists, sumPasses, sumGoals, sumAttempts);

                outIndex++;
                gameCount = 0;
                sumPoints = sumAssists = sumPasses = sumGoals = sumAttempts = 0;
            }

            gameCount++;
            name = gameStats[i].getPlayerName();
            sumPoints += gameStats[i].getPoints();
            sumAssists += gameStats[i].getAssists();
            sumPasses += gameStats[i].getNumPasses();
            sumGoals += gameStats[i].getGoals();
            sumAttempts += gameStats[i].getGoalAttempts();

            if (i == gameStats.length - 1) {
                if (!gameStats[i].getPlayerName().equals(name)) {
                    outIndex++;
                    name = gameStats[i].getPlayerName();
                }
                processGameStatsUtil.updateGameStat(outPlayers[outIndex], gameCount, name, 
                    sumPoints, sumAssists, sumPasses, sumGoals, sumAttempts);
            }
        }
    }

    public static Player findPlayerPointsPerGame(final Player[] players, int targetPoints) {
        return null;
    }

    public static Player findPlayerShootingPercentage(final Player[] players, int targetShootingPercentage) {
        return null;
    }

    public static long find3ManDreamTeam(final Player[] players, final Player[] outPlayers, final Player[] scratch) {
        return -1;
    }

    public static long findDreamTeam(final Player[] players, int k, final Player[] outPlayers, final Player[] scratch) {
        return -1;
    }

    public static int findDreamTeamSize(final Player[] players, final Player[] scratch) {
        return -1;
    }
}