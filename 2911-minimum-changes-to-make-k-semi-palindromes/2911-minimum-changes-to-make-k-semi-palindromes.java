import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        
        // Step 1: Precompute proper divisors for all possible substring lengths
        List<Integer>[] divisors = getDivisors(n);
        
        // Step 2: Precompute minimum changes to make substring s[i..j] a semi-palindrome
        int[][] cost = precomputeCosts(s, n, divisors);

        // Step 3: DP to partition prefix of length i into j semi-palindromes
        int[][] dp = new int[n + 1][k + 1];
        for (int[] row : dp) {
            Arrays.fill(row, n); // Use n as an upper bound (max possible changes < n)
        }
        dp[0][0] = 0;

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                // Partition point p; substring s[p..i-1] must have length >= 2
                for (int p = (j - 1) * 2; p <= i - 2; p++) {
                    if (dp[p][j - 1] != n) {
                        dp[i][j] = Math.min(dp[i][j], dp[p][j - 1] + cost[p][i - 1]);
                    }
                }
            }
        }

        return dp[n][k];
    }

    private List<Integer>[] getDivisors(int n) {
        @SuppressWarnings("unchecked")
        List<Integer>[] divisors = new ArrayList[n + 1];
        for (int len = 1; len <= n; len++) {
            divisors[len] = new ArrayList<>();
            for (int d = 1; d < len; d++) {
                if (len % d == 0) {
                    divisors[len].add(d);
                }
            }
        }
        return divisors;
    }

    private int[][] precomputeCosts(String s, int n, List<Integer>[] divisors) {
        int[][] cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int len = j - i + 1;
                int minChanges = len;

                for (int d : divisors[len]) {
                    int currentChanges = 0;
                    // Check each modulo group (0 to d - 1) as a separate palindrome
                    for (int mod = 0; mod < d; mod++) {
                        int left = i + mod;
                        int right = j - d + 1 + mod;
                        while (left < right) {
                            if (s.charAt(left) != s.charAt(right)) {
                                currentChanges++;
                            }
                            left += d;
                            right -= d;
                        }
                    }
                    minChanges = Math.min(minChanges, currentChanges);
                }
                cost[i][j] = minChanges;
            }
        }
        return cost;
    }
}
