import java.util.*;

public class W345{

    public static int linearFirst(String[] arr, String target, int[] comparisons) {
        comparisons[0] = 0;
        for (int i = 0; i < arr.length; i++) {
            comparisons[0]++;
            if (arr[i].equals(target)) return i;
        }
        return -1;
    }

    public static int linearLast(String[] arr, String target, int[] comparisons) {
        comparisons[0] = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            comparisons[0]++;
            if (arr[i].equals(target)) return i;
        }
        return -1;
    }

    public static int binarySearch(String[] arr, String target, int[] comparisons) {
        int low = 0, high = arr.length - 1;
        comparisons[0] = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons[0]++;

            int cmp = arr[mid].compareTo(target);

            if (cmp == 0) return mid;
            else if (cmp < 0) low = mid + 1;
            else high = mid - 1;
        }

        return -1;
    }

    public static int countOccurrences(String[] arr, String target) {
        int first = firstOccurrence(arr, target);
        int last = lastOccurrence(arr, target);
        if (first == -1) return 0;
        return last - first + 1;
    }

    private static int firstOccurrence(String[] arr, String target) {
        int low = 0, high = arr.length - 1, result = -1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid].compareTo(target) >= 0) {
                if (arr[mid].equals(target)) result = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return result;
    }

    private static int lastOccurrence(String[] arr, String target) {
        int low = 0, high = arr.length - 1, result = -1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid].compareTo(target) <= 0) {
                if (arr[mid].equals(target)) result = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String[] logs = {"accB", "accA", "accB", "accC"};

        int[] comp = new int[1];

        int first = linearFirst(logs, "accB", comp);
        System.out.println("Linear first accB: index " + first + " (" + comp[0] + " comparisons)");

        int last = linearLast(logs, "accB", comp);
        System.out.println("Linear last accB: index " + last + " (" + comp[0] + " comparisons)");

        Arrays.sort(logs);

        int index = binarySearch(logs, "accB", comp);
        int count = countOccurrences(logs, "accB");

        System.out.println("Binary accB: index " + index + " (" + comp[0] + " comparisons), count=" + count);
    }
}