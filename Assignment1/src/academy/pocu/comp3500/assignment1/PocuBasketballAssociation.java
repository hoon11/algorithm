package academy.pocu.comp3500.assignment1;

import academy.pocu.comp3500.assignment1.pba.GameStat;
import academy.pocu.comp3500.assignment1.pba.Player;

public final class PocuBasketballAssociation {
    private PocuBasketballAssociation() {
    }

    public static void processGameStats(final GameStat[] gameStats, final Player[] outPlayers) {
        ProcessGameStatsUtil.quickSort(gameStats);

        int outIndex = 0;
        int gameCount = 0;
        String name = "";
        int sumPoints = 0;
        int sumAssists = 0;
        int sumPasses = 0;
        int sumGoals = 0;
        int sumAttempts = 0;
        for (int i = 0; i < gameStats.length; i++) {
            if (i > 1 && !gameStats[i].getPlayerName().equals(name)) {
                ProcessGameStatsUtil.updateGameStat(outPlayers[outIndex], gameCount, name, sumPoints, sumAssists, sumPasses, sumGoals, sumAttempts);
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
                ProcessGameStatsUtil.updateGameStat(outPlayers[outIndex], gameCount, name, sumPoints, sumAssists, sumPasses, sumGoals, sumAttempts);
            }
        }
    }

    public static Player findPlayerPointsPerGame(final Player[] players, int targetPoints) {
        int findIndex = 0;
        int minDistance = targetPoints;
        int subtractResult = targetPoints;
        int currentDistance = targetPoints;
        for (int i = 0; i < players.length; i++) {
            subtractResult = players[i].getPointsPerGame() - targetPoints;
            currentDistance = subtractResult;
            if (subtractResult < 0) {
                currentDistance *= -1;
            }
            if ((currentDistance < minDistance) || (currentDistance == minDistance && players[findIndex].getPointsPerGame() < players[i].getPointsPerGame())
            ) {
                findIndex = i;
                minDistance = currentDistance;
            }
        }

        return players[findIndex];
    }

    public static Player findPlayerShootingPercentage(final Player[] players, int targetShootingPercentage) {
        int findIndex = 0;
        int minDistance = targetShootingPercentage;
        int subtractResult = targetShootingPercentage;
        int currentDistance = targetShootingPercentage;
        for (int i = 0; i < players.length; i++) {
            subtractResult = players[i].getShootingPercentage() - targetShootingPercentage;
            currentDistance = subtractResult;
            if (subtractResult < 0) {
                currentDistance *= -1;
            }
            if ((currentDistance < minDistance) || (currentDistance == minDistance && players[findIndex].getShootingPercentage() < players[i].getShootingPercentage())
            ) {
                findIndex = i;
                minDistance = currentDistance;
            }
        }

        return players[findIndex];
    }

    public static long find3ManDreamTeam(final Player[] players, final Player[] outPlayers, final Player[] scratch) {
        return -1;
    }

    public static long findDreamTeam(final Player[] players, int k, final Player[] outPlayers, final Player[] scratch) {
        return -1;
    }

    public static int findDreamTeamSize(final Player[] players, final Player[] scratch) {
        FindDreamTeamSizeUtil.quickSort(players);
        int bestTeamWork = 0;
        int totalPass = 0;
        int i = players.length - 1;
        int scratchIndex = 0;
        while (i >= 0) {
            if (bestTeamWork < players[i].getAssistsPerGame() * (totalPass + players[i].getPassesPerGame())) {
                totalPass += players[i].getPassesPerGame();
                bestTeamWork = players[i].getAssistsPerGame() * totalPass;
                scratch[scratchIndex] = players[i];
                scratchIndex++;
            }
            i--;
        }
        

        return -1;
    }
}