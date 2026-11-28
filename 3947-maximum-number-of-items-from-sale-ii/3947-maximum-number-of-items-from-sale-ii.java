import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int minPrice = Integer.MAX_VALUE;
        int maxFactor = 0;

        // 1. Find the cheapest item price and maximum factor
        for (int[] item : items) {
            maxFactor = Math.max(maxFactor, item[0]);
            minPrice = Math.min(minPrice, item[1]);
        }

        // 2. Count frequencies of each factor
        int[] freq = new int[maxFactor + 1];
        for (int[] item : items) {
            freq[item[0]]++;
        }

        // 3. Calculate total multiples for each factor using a harmonic sieve
        int[] multiplesCount = new int[maxFactor + 1];
        for (int i = 1; i <= maxFactor; i++) {
            if (freq[i] > 0) {
                for (int j = i; j <= maxFactor; j += i) {
                    multiplesCount[i] += freq[j];
                }
            }
        }

        // 4. Group profitable "buy-one-get-one-free" opportunities by price
        // Buying an item with a freebie is only better than buying the cheapest item
        // twice if its price is strictly less than 2 * minPrice.
        TreeMap<Integer, Long> costToFreebiePairs = new TreeMap<>();
        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];
            
            // Exclude the item itself from its multiples
            int availableFreebies = multiplesCount[factor] - 1; 

            if (availableFreebies > 0 && price < 2L * minPrice) {
                costToFreebiePairs.put(
                    price, 
                    costToFreebiePairs.getOrDefault(price, 0L) + availableFreebies
                );
            }
        }

        // 5. Greedily buy the cheapest items that grant freebies first
        long totalItems = 0;
        long remainingBudget = budget;

        for (Map.Entry<Integer, Long> entry : costToFreebiePairs.entrySet()) {
            long price = entry.getKey();
            long maxPairs = entry.getValue();

            if (remainingBudget < price) {
                break;
            }

            long bought = Math.min(maxPairs, remainingBudget / price);
            totalItems += bought * 2; // 1 bought + 1 free
            remainingBudget -= bought * price;
        }

        // 6. Spend any leftover budget on the overall cheapest item
        totalItems += remainingBudget / minPrice;

        return (int) totalItems;
    }
}
