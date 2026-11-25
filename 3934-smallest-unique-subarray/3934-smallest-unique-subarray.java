
import java.util.*;

class Solution {

    public int smallestUniqueSubarray(int[] nums) {

        int n = nums.length;

        long base = 19;
        long mod = 1_000_000_007L;

        long[] power = new long[n + 1];
        power[0] = 1;

        for (int i = 1; i <= n; i++) {
            power[i] = (power[i - 1] * base) % mod;
        }

        int low = 1;
        int high = n;
        int ans = n;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (check(nums, mid, base, mod, power)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    private boolean check(int[] nums, int len,
                          long base, long mod, long[] power) {

        int n = nums.length;

        HashMap<Long, Integer> freq = new HashMap<>();

        long hash = 0;

        // Calculate hash of first window
        for (int i = 0; i < len; i++) {
            hash = (hash * base + nums[i]) % mod;
        }

        freq.put(hash, 1);

        // Slide the window
        for (int i = 1; i <= n - len; i++) {

            hash = (hash - (power[len - 1] * nums[i - 1]) % mod + mod) % mod;

            hash = (hash * base + nums[i + len - 1]) % mod;

            freq.put(hash, freq.getOrDefault(hash, 0) + 1);
        }

        // Check whether any subarray occurs exactly once
        for (int count : freq.values()) {
            if (count == 1) {
                return true;
            }
        }

        return false;
    }
}
