import java.util.*;

public class W343{

    static class Trade {
        String id;
        int volume;

        Trade(String id, int volume) {
            this.id = id;
            this.volume = volume;
        }

        public String toString() {
            return id + ":" + volume;
        }
    }

    public static void mergeSort(Trade[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private static void merge(Trade[] arr, int left, int mid, int right) {
        Trade[] temp = new Trade[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (arr[i].volume <= arr[j].volume) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        while (j <= right) {
            temp[k++] = arr[j++];
        }

        for (int x = 0; x < temp.length; x++) {
            arr[left + x] = temp[x];
        }
    }

    public static void quickSortDesc(Trade[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSortDesc(arr, low, pi - 1);
            quickSortDesc(arr, pi + 1, high);
        }
    }

    private static int partition(Trade[] arr, int low, int high) {
        int pivot = arr[high].volume;
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j].volume > pivot) {
                i++;
                Trade temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        Trade temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    public static Trade[] mergeSortedLists(Trade[] a, Trade[] b) {
        Trade[] result = new Trade[a.length + b.length];
        int i = 0, j = 0, k = 0;

        while (i < a.length && j < b.length) {
            if (a[i].volume <= b[j].volume) {
                result[k++] = a[i++];
            } else {
                result[k++] = b[j++];
            }
        }

        while (i < a.length) {
            result[k++] = a[i++];
        }

        while (j < b.length) {
            result[k++] = b[j++];
        }

        return result;
    }

    public static int totalVolume(Trade[] arr) {
        int sum = 0;
        for (Trade t : arr) {
            sum += t.volume;
        }
        return sum;
    }

    public static void main(String[] args) {
        Trade[] trades = {
                new Trade("trade3", 500),
                new Trade("trade1", 100),
                new Trade("trade2", 300)
        };

        Trade[] mergeArray = Arrays.copyOf(trades, trades.length);
        mergeSort(mergeArray, 0, mergeArray.length - 1);
        System.out.println("MergeSort: " + Arrays.toString(mergeArray));

        Trade[] quickArray = Arrays.copyOf(trades, trades.length);
        quickSortDesc(quickArray, 0, quickArray.length - 1);
        System.out.println("QuickSort (desc): " + Arrays.toString(quickArray));

        Trade[] morning = {
                new Trade("m1", 100),
                new Trade("m2", 300)
        };

        Trade[] afternoon = {
                new Trade("a1", 200),
                new Trade("a2", 300)
        };

        mergeSort(morning, 0, morning.length - 1);
        mergeSort(afternoon, 0, afternoon.length - 1);

        Trade[] merged = mergeSortedLists(morning, afternoon);
        System.out.println("Merged: " + Arrays.toString(merged));

        int total = totalVolume(merged);
        System.out.println("Total Volume: " + total);
    }
}