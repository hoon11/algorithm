package academy.pocu.comp3500.assignment1;

import academy.pocu.comp3500.assignment1.pba.GameStat;
import academy.pocu.comp3500.assignment1.pba.Player;

public final class ProcessGameStatsUtil {

    public static void updateGameStat(Player player, int gameCount, String name, int sumPointsPerGame, int sumAssistsPerGame, 
            int sumPassesPerGame, int sumGoals, int sumGoalAttempts) {        
        player.setName(name);
        player.setPointsPerGame(sumPointsPerGame / gameCount);
        player.setAssistsPerGame(sumAssistsPerGame / gameCount);
        player.setPassesPerGame(sumPassesPerGame / gameCount);
        player.setShootingPercentage(calculateShootingPercentage(sumGoals, sumGoalAttempts));
    }

    private static int calculateShootingPercentage(int sumGoals, int sumGoalAttempts) {
        return 100 * sumGoals / sumGoalAttempts;
    }

    public static void quickSort(GameStat[] targets) {
        quickSortRecursive(targets, 0, targets.length - 1);
    }

    private static void quickSortRecursive(GameStat[] targets, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivotPos = partition(targets, left, right);

        quickSortRecursive(targets, left, pivotPos - 1);
        quickSortRecursive(targets, pivotPos + 1, right);
    }

    private static int partition(GameStat[] targets, int left, int right) {
        GameStat pivot = targets[right];

        int i = (left - 1);
        for (int j = left; j < right; ++j) {
            if (targets[j].getPlayerName().compareTo(pivot.getPlayerName()) < 0) {
                ++i;
                swap(targets, i, j);
            }
        }

        int pivotPos = i + 1;
        swap(targets, pivotPos, right);

        return pivotPos;
    }

    private static void swap(GameStat[] targets, int one, int theOther) {
        GameStat temp = targets[one];
        targets[one] = targets[theOther];
        targets[theOther] = temp;
    }
}
