import java.util.*;

public class W346{

    public static int linearSearch(int[] arr, int target, int[] comparisons) {
        comparisons[0] = 0;
        for (int i = 0; i < arr.length; i++) {
            comparisons[0]++;
            if (arr[i] == target) return i;
        }
        return -1;
    }

    public static int binarySearchInsertionPoint(int[] arr, int target, int[] comparisons) {
        int low = 0, high = arr.length - 1;
        comparisons[0] = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons[0]++;

            if (arr[mid] == target) return mid;
            else if (arr[mid] < target) low = mid + 1;
            else high = mid - 1;
        }

        return low;
    }

    public static int floor(int[] arr, int target, int[] comparisons) {
        int low = 0, high = arr.length - 1;
        int result = -1;
        comparisons[0] = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons[0]++;

            if (arr[mid] == target) return arr[mid];
            else if (arr[mid] < target) {
                result = arr[mid];
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return result;
    }

    public static int ceiling(int[] arr, int target, int[] comparisons) {
        int low = 0, high = arr.length - 1;
        int result = -1;
        comparisons[0] = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons[0]++;

            if (arr[mid] == target) return arr[mid];
            else if (arr[mid] > target) {
                result = arr[mid];
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] risks = {10, 25, 50, 100};
        int target = 30;

        int[] comp = new int[1];

        int linearIndex = linearSearch(risks, target, comp);
        System.out.println("Linear: threshold=" + target + " → index " + linearIndex + " (" + comp[0] + " comparisons)");

        int insertion = binarySearchInsertionPoint(risks, target, comp);
        System.out.println("Binary insertion index: " + insertion + " (" + comp[0] + " comparisons)");

        int floorVal = floor(risks, target, comp);
        int ceilVal = ceiling(risks, target, comp);

        System.out.println("Floor(" + target + "): " + floorVal);
        System.out.println("Ceiling(" + target + "): " + ceilVal);
    }
}