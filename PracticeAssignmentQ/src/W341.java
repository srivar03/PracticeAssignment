import java.util.*;

public class W341{

    static class Transaction {
        String id;
        double fee;
        String timestamp;

        Transaction(String id, double fee, String timestamp) {
            this.id = id;
            this.fee = fee;
            this.timestamp = timestamp;
        }

        public String toString() {
            return id + ":" + fee + "@" + timestamp;
        }
    }

    static class BubbleSortResult {
        int passes;
        int swaps;

        BubbleSortResult(int passes, int swaps) {
            this.passes = passes;
            this.swaps = swaps;
        }
    }

    public static BubbleSortResult bubbleSortByFee(List<Transaction> list) {
        int n = list.size();
        int passes = 0, swaps = 0;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            passes++;

            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).fee > list.get(j + 1).fee) {
                    Transaction temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swaps++;
                    swapped = true;
                }
            }

            if (!swapped) break;
        }

        return new BubbleSortResult(passes, swaps);
    }

    public static void insertionSortByFeeAndTimestamp(List<Transaction> list) {
        for (int i = 1; i < list.size(); i++) {
            Transaction key = list.get(i);
            int j = i - 1;

            while (j >= 0 && compare(list.get(j), key) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }

            list.set(j + 1, key);
        }
    }

    private static int compare(Transaction a, Transaction b) {
        if (a.fee != b.fee) {
            return Double.compare(a.fee, b.fee);
        }
        return a.timestamp.compareTo(b.timestamp);
    }

    public static List<Transaction> findOutliers(List<Transaction> list) {
        List<Transaction> outliers = new ArrayList<>();

        for (Transaction t : list) {
            if (t.fee > 50) {
                outliers.add(t);
            }
        }

        return outliers;
    }

    public static void main(String[] args) {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("id1", 10.5, "10:00"));
        transactions.add(new Transaction("id2", 25.0, "09:30"));
        transactions.add(new Transaction("id3", 5.0, "10:15"));

        List<Transaction> smallBatch = new ArrayList<>(transactions);
        BubbleSortResult result = bubbleSortByFee(smallBatch);
        System.out.println("Bubble Sorted (fee): " + smallBatch);
        System.out.println("Passes: " + result.passes + ", Swaps: " + result.swaps);

        List<Transaction> mediumBatch = new ArrayList<>(transactions);
        insertionSortByFeeAndTimestamp(mediumBatch);
        System.out.println("Insertion Sorted (fee+timestamp): " + mediumBatch);

        List<Transaction> outliers = findOutliers(transactions);
        System.out.println("High-fee outliers: " + outliers);
    }
}