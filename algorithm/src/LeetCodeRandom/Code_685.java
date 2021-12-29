package LeetCodeRandom;

public class Code_685 {
    //题目链接:https://leetcode-cn.com/problems/redundant-connection-ii/
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        UnionFind uf = new UnionFind(n);
        //记录当前节点是从哪来的，pre[0]=1代表1->0
        int[] pre = new int[n + 1];
        int first[] = null;
        int second[] = null;
        int circle[] = null;
        for (int i = 0; i < n; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            if (pre[to] == 0) {
                //第一次到达
                pre[to] = from;
                if (uf.same(from, to)) {
                    circle = new int[]{from, to};
                } else {
                    uf.union(from, to);
                }
            } else {
                //不是第一次到达
                first = new int[]{pre[to], to};
                second = edges[i];
            }
        }
        return first != null ? (circle != null ? first : second) : circle;
    }

    //并查集
    public class UnionFind {
        int[] parent;
        public UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        //寻找根节点加上路径压缩
        public int find(int i) {
            if (i != parent[i]) {
                parent[i] = find(parent[i]);
            }
            return parent[i];
        }
        //联合
        public void union(int i, int j) {
            int iRoot = find(i);
            int jRoot = find(j);
            if (iRoot != jRoot) parent[iRoot] = jRoot;
        }
        //是否同一集合
        public boolean same(int i, int j) {
            return find(i) == find(j);
        }
    }
}
