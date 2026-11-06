import java.util.*;

class Solution{
    public int[] countServers(int n, int[][] logs, int x, int[] queries){
        Arrays.sort(logs, (a,b) -> Integer.compare(a[1], b[1]));

        int m = queries.length;
        int[][] sortedQueries = new int[m][2];
        for(int i=0; i<m; i++){
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }
        Arrays.sort(sortedQueries, (a,b) -> Integer.compare(a[0], b[0]));

        int[] count = new int[n+1];
        int active = 0;
        int left = 0;
        int right = 0;
        int[] ans = new int[m];

        for(int[] q: sortedQueries){
            int end = q[0];
            int start = end-x;
            int originalIndex = q[1];

            while(right < logs.length && logs[right][1] <= end){
                if(count[logs[right][0]] == 0){
                    active++;
                }
                count[logs[right][0]]++;
                right++;
            }
            while(left < right && logs[left][1] <start){
                count[logs[left][0]]--;
                if(count[logs[left][0]] == 0){
                    active--;
                }
                left++;
            }
            ans[originalIndex] = n-active;
        }
        return ans;
    }
}