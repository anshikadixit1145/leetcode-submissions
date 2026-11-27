class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int[] dp = new int[budget + 1];
        int minPrice = Integer.MAX_VALUE;

        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];
            minPrice = Math.min(minPrice, price);

            // Count total items gained by buying the first copy of this item
            // (1 for itself + 1 for every item j where factor divides factor_j)
            int gain = 0;
            for (int[] other : items) {
                if (other[0] % factor == 0) {
                    gain++;
                }
            }

            // 0/1 Knapsack transition for buying the first copy of an item
            for (int b = budget; b >= price; b--) {
                dp[b] = Math.max(dp[b], dp[b - price] + gain);
            }
        }

        // Spend any remaining budget on extra copies of the cheapest item
        int maxItems = 0;
        for (int spent = 0; spent <= budget; spent++) {
            int extraCopies = (budget - spent) / minPrice;
            maxItems = Math.max(maxItems, dp[spent] + extraCopies);
        }

        return maxItems;
    }
}
