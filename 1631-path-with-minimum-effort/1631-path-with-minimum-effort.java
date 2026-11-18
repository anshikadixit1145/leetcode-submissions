import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public int minimumEffortPath(int[][] heights) {
        int rows = heights.length;
        int cols = heights[0].length;

        // Track the minimum effort needed to reach each cell
        int[][] minEffort = new int[rows][cols];
        for (int[] row : minEffort) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        minEffort[0][0] = 0;

        // Min-heap storing {effort, row, col} sorted by smallest effort first
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[]{0, 0, 0});

        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int effort = current[0];
            int r = current[1];
            int c = current[2];

            // Early exit as soon as we pop the destination cell
            if (r == rows - 1 && c == cols - 1) {
                return effort;
            }

            // Skip outdated entries if a better path was already found
            if (effort > minEffort[r][c]) {
                continue;
            }

            for (int[] dir : directions) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
                    int stepDiff = Math.abs(heights[nr][nc] - heights[r][c]);
                    int newEffort = Math.max(effort, stepDiff);

                    if (newEffort < minEffort[nr][nc]) {
                        minEffort[nr][nc] = newEffort;
                        pq.offer(new int[]{newEffort, nr, nc});
                    }
                }
            }
        }

        return 0;
    }
}
