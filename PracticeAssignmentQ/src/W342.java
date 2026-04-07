import java.util.*;

public class W342{

    static class Client {
        String name;
        int riskScore;
        double accountBalance;

        Client(String name, int riskScore, double accountBalance) {
            this.name = name;
            this.riskScore = riskScore;
            this.accountBalance = accountBalance;
        }

        public String toString() {
            return name + ":" + riskScore;
        }
    }

    public static int bubbleSortByRiskAsc(Client[] arr) {
        int n = arr.length;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].riskScore > arr[j + 1].riskScore) {
                    Client temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swaps++;
                    swapped = true;
                }
            }

            if (!swapped) break;
        }

        return swaps;
    }

    public static void insertionSortByRiskDesc(Client[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Client key = arr[i];
            int j = i - 1;

            while (j >= 0 && compare(arr[j], key) < 0) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }

    private static int compare(Client a, Client b) {
        if (a.riskScore != b.riskScore) {
            return Integer.compare(a.riskScore, b.riskScore);
        }
        return Double.compare(a.accountBalance, b.accountBalance);
    }

    public static List<Client> topN(Client[] arr, int n) {
        List<Client> result = new ArrayList<>();
        for (int i = 0; i < Math.min(n, arr.length); i++) {
            result.add(arr[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        Client[] clients = {
                new Client("clientC", 80, 5000),
                new Client("clientA", 20, 10000),
                new Client("clientB", 50, 7000)
        };

        Client[] bubbleArray = Arrays.copyOf(clients, clients.length);
        int swaps = bubbleSortByRiskAsc(bubbleArray);
        System.out.println("Bubble (asc): " + Arrays.toString(bubbleArray));
        System.out.println("Swaps: " + swaps);

        Client[] insertionArray = Arrays.copyOf(clients, clients.length);
        insertionSortByRiskDesc(insertionArray);
        System.out.println("Insertion (desc): " + Arrays.toString(insertionArray));

        List<Client> topClients = topN(insertionArray, 10);
        System.out.println("Top risks: " + topClients);
    }
}