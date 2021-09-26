package academy.pocu.comp3500.lab3;

import java.util.ArrayList;

public final class MissionControl {
    private MissionControl() {
    }

    public static int findMaxAltitudeTime(final int[] altitudes) {
        int left = 0;
        int right = altitudes.length - 1;
        int middle = (left + right) / 2;
        while (left < right) {
            if (middle + 1 < altitudes.length) {
                if (altitudes[middle] < altitudes[middle + 1]) {
                    left = middle + 1;
                } else if (altitudes[middle] > altitudes[middle + 1]) {
                    right = middle;
                }
            }
            if (middle - 1 >= 0) {
                if (altitudes[middle - 1] > altitudes[middle]) {
                    right = middle - 1;
                } else if (altitudes[middle] < altitudes[middle + 1]) {
                    left = middle + 1;
                }
            }

            middle = (left + right) / 2;
        }
        if (left == right) {
            middle = left;
        }

        return middle;
    }

    public static ArrayList<Integer> findAltitudeTimes(final int[] altitudes, final int targetAltitude) {
        int left = 0;
        int right = altitudes.length - 1;
        ArrayList<Integer> findAltitudes = new ArrayList<>();
        for (int i = 0; i < altitudes.length; i++) {
            if (altitudes[i] == targetAltitude) {
                findAltitudes.add(i);
            }
        }
        //findAltitudeTimesReclusive(findAltitudes, altitudes, targetAltitude, 0, right);

        return findAltitudes;
    }

    private static void findAltitudeTimesReclusive(ArrayList<Integer> findAltitudes, final int[] altitudes,
            final int targetAltitude, final int left, final int right) {
        if (left > right) {
            return;
        }

        int middle = (left + right) / 2;

        if (altitudes[middle] == targetAltitude) {
            findAltitudes.add(middle);
            return;
        }

        if (altitudes[left] <= altitudes[middle]) {
            if (altitudes[left] <= targetAltitude && targetAltitude <= altitudes[middle]) {
                findAltitudeTimesReclusive(findAltitudes, altitudes, targetAltitude, left, middle - 1);
            }
            findAltitudeTimesReclusive(findAltitudes, altitudes, targetAltitude, middle + 1, right);
            return;
        }

        if (altitudes[middle] <= targetAltitude && targetAltitude <= altitudes[right]) {
            findAltitudeTimesReclusive(findAltitudes, altitudes, targetAltitude, middle + 1, right);
        }
        findAltitudeTimesReclusive(findAltitudes, altitudes, targetAltitude, left, middle - 1);
        return;
    }
}