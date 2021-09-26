package academy.pocu.comp3500.lab3;

import java.util.ArrayList;

public final class MissionControl {
    private MissionControl() {
    }

    public static int findMaxAltitudeTime(final int[] altitudes) {
        int left = 0;
        int right = altitudes.length - 1;
        int middle = (left + right) / 2;
        int i = 0;
        while (left < right) {
            if (middle + 1 < altitudes.length){
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
            i++;
        }
        if (left == right) {
            middle = left;
        }

        return middle;
    }

    public static ArrayList<Integer> findAltitudeTimes(final int[] altitudes, final int targetAltitude) {
        int left = 0;
        int right = altitudes.length - 1;
        boolean hasLeftSearched = false;
        boolean hasRightSearched = false;
        int leftIndex = -1;
        int rightIndex = -1;
        ArrayList<Integer> findAltitudes = new ArrayList<>();

        while (left < right && !(hasLeftSearched && hasRightSearched)) {
            if (altitudes[left] == targetAltitude) {
                leftIndex = left;
            }
            if (altitudes[right] == targetAltitude) {
                rightIndex = right;
            }
            if (left + 1 < altitudes.length) {
                if (altitudes[left] < altitudes[left + 1] && targetAltitude < altitudes[left + 1]) {
                    hasLeftSearched = true;
                } else if (altitudes[left] > altitudes[left + 1] && targetAltitude > altitudes[left + 1]) {
                    hasLeftSearched = true;
                } else {
                    left++;
                }
            }
            if (right - 1 > 0) {
                if (altitudes[right - 1] < altitudes[right] && targetAltitude > altitudes[right - 1]) {
                    hasRightSearched = true;
                } else if (altitudes[right - 1] > altitudes[right] && targetAltitude < altitudes[right - 1]) {
                    hasRightSearched = true;
                } else {
                    right--;
                }
            }
        }
        if (left == right && altitudes[left] == targetAltitude) {
            findAltitudes.add(left);
        } else {
            if (leftIndex != -1) {
                findAltitudes.add(leftIndex);
            }
            if (rightIndex != -1) {
                findAltitudes.add(rightIndex);
            }
        }

        return findAltitudes;
    }
}