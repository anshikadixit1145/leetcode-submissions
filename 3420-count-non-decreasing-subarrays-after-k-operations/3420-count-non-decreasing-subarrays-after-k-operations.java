import java.util.*;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        Deque<int[]> dq = new ArrayDeque<>();
        long cost = 0;
        long answer = 0;
        int right = n - 1;

        for (int left = n - 1; left >= 0; left--) {
            int num = nums[left];
            int count = 1;

            while (!dq.isEmpty() && dq.peekLast()[0] < num) {
                int[] seg = dq.pollLast();
                count += seg[1];
                cost += (long) (num - seg[0]) * seg[1];
            }
            dq.addLast(new int[]{num, count});

            while (cost > k) {
                int[] front = dq.peekFirst();
                cost -= front[0] - nums[right];
                front[1]--;
                if (front[1] == 0) {
                    dq.pollFirst();
                }
                right--;
            }

            answer += right - left + 1;
        }

        return answer;
    }
}