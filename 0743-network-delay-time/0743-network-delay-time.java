class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        
        //Adjacency List
        List<int[]>[] adj = new ArrayList[n+1];
        for(int i=1; i<=n; i++){
            adj[i] = new ArrayList<>();
        }
        for(int[] edge:times){
            adj[edge[0]].add(new int[]{edge[1], edge[2]});
        }

        //check the neighbours
        for(int i=1; i<=n; i++){
            Collections.sort(adj[i], (a,b) -> a[1] - b[1]);
        }

        //min time for each node
        int[] minTime = new int[n+1];
        Arrays.fill(minTime, Integer.MAX_VALUE);

        //aaply DFS
        dfs(k, 0, adj, minTime);

        //find max time
        int maxDelay = 0;
        for(int i=1; i<=n; i++){
            if(minTime[i] == Integer.MAX_VALUE){
                return -1;
            }
            maxDelay = Math.max(maxDelay, minTime[i]);
        }
        return maxDelay;
    }
    private void dfs(int node, int currentTime, List<int[]>[] adj, int[] minTime){
        if(currentTime >= minTime[node]){
            return;
        }
        minTime[node] = currentTime;

        for(int[] edge : adj[node]){
            int nextNode = edge[0];
            int weight = edge[1];
            dfs(nextNode, currentTime + weight, adj, minTime);
        }
    }
}