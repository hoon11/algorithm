package academy.pocu.comp3500.lab6;

import academy.pocu.comp3500.lab6.leagueofpocu.Player;

public final class Sort {
    private Sort() {
    }

    public static void quickSort(final Player[] players) {
        quickSortRecursive(players, 0, players.length - 1);
    }

    private static void quickSortRecursive(Player[] players, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivotPos = partition(players, left, right);
        quickSortRecursive(players, left, pivotPos - 1);
        quickSortRecursive(players, pivotPos + 1, right);
    }

    private static int partition(Player[] players, int left, int right) {
        final Player pivot = players[right];

        int i = left;
        for (int j = left; j < right; ++j) {
            if (players[j].getRating() < pivot.getRating()) {
                swap(players, i, j);
                ++i;
            }
        }

        int pivotPos = i;
        swap(players, pivotPos, right);

        return pivotPos;
    }

    private static void swap(Player[] players, int i, int j) {
        Player temp = players[i];
        players[i] = players[j];
        players[j] = temp;
    }

}