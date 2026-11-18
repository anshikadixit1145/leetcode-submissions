// Implementation using BELLMAN FORD ALGORITHM

class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        
        //track min time
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;

        //relax function
        for(int i = 1; i<n; i++){
            boolean updated = false;

            for(int[] edge: times){
                int u = edge[0];
                int v = edge[1];
                int w = edge[2];

                if(dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]){
                    dist[v] = dist[u] + w;
                    updated = true;
                }
            }
            if(!updated){
                break;
            }
        }
        int maxDelay = 0;
        for(int i=1; i<=n; i++){
            if(dist[i] == Integer.MAX_VALUE){
                return -1;
            }
            maxDelay = Math.max(maxDelay, dist[i]);
        }
        return maxDelay;
    }
}