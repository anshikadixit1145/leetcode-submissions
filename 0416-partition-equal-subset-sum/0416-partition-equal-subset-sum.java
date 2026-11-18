class Solution {
    public boolean canPartition(int[] nums) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        // Odd total sum cannot be split into two equal subsets
        if (totalSum % 2 != 0) {
            return false;
        }

        int target = totalSum / 2;
        // Use Boolean wrapper class so null represents an uncomputed state
        Boolean[][] memo = new Boolean[nums.length][target + 1];

        return dfs(nums, 0, target, memo);
    }

    private boolean dfs(int[] nums, int index, int remaining, Boolean[][] memo) {
        // Base cases
        if (remaining == 0) {
            return true;
        }
        if (index >= nums.length || remaining < 0) {
            return false;
        }

        // Return cached result if already computed
        if (memo[index][remaining] != null) {
            return memo[index][remaining];
        }

        // Choice 1: Include nums[index] in the subset
        // Choice 2: Exclude nums[index] from the subset
        boolean result = dfs(nums, index + 1, remaining - nums[index], memo) 
                      || dfs(nums, index + 1, remaining, memo);

        memo[index][remaining] = result;
        return result;
    }
}
