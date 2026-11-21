import java.util.*;

class Solution {
    private List<Integer>[] tree;
    private int[] lastSeen;
    private int[] ans;
    private String s;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        this.s = s;
        this.ans = new int[n];
        this.lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);

        // Build adjacency list for the initial tree
        tree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            tree[parent[i]].add(i);
        }

        // Run a single DFS from root (node 0)
        dfs(0);
        return ans;
    }

    private void dfs(int u) {
        ans[u] = 1; // Count the current node itself
        int charIdx = s.charAt(u) - 'a';
        
        // Save the previous ancestor with the same character and update to current node
        int prevAncestor = lastSeen[charIdx];
        lastSeen[charIdx] = u;

        for (int v : tree[u]) {
            dfs(v);
            // If child 'v' has an ancestor with the same character, attach its subtree there;
            // otherwise, attach it to its original parent 'u'
            int childCharIdx = s.charAt(v) - 'a';
            int targetParent = (lastSeen[childCharIdx] != -1) ? lastSeen[childCharIdx] : u;
            ans[targetParent] += ans[v];
        }

        // Backtrack: restore the previous ancestor state before returning up the tree
        lastSeen[charIdx] = prevAncestor;
    }
}
