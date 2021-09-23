package academy.pocu.comp3500.assignment1.utils;

import academy.pocu.comp3500.assignment1.pba.Player;

public final class findPlayerShootingPercentageUtil {

    public static void quickSort(Player[] targets) {
        quickSortRecursive(targets, 0, targets.length - 1);
    }

    private static void quickSortRecursive(Player[] targets, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivotPos = partition(targets, left, right);

        quickSortRecursive(targets, left, pivotPos - 1);
        quickSortRecursive(targets, pivotPos + 1, right);
    }

    private static int partition(Player[] targets, int left, int right) {
        Player pivot = targets[right];

        int i = (left - 1);
        for (int j = left; j < right; ++j) {
            if (targets[j].getShootingPercentage() < pivot.getShootingPercentage()) {
                ++i;
                swap(targets, i, j);
            }
        }

        int pivotPos = i + 1;
        swap(targets, pivotPos, right);

        return pivotPos;
    }

    private static void swap(Player[] targets, int one, int theOther) {
        Player temp = targets[one];
        targets[one] = targets[theOther];
        targets[theOther] = temp;
    }
}
