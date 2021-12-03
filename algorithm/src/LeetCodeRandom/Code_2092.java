package LeetCodeRandom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Code_2092 {
    //题目链接:https://leetcode-cn.com/problems/find-all-people-with-secret/
    public class UnionFind1 {
        public int[] father;
        public boolean[] sect;

        public UnionFind1(int n, int first) {
            father = new int[n];
            sect = new boolean[n];
            for (int i = 1; i < n; i++) {
                father[i] = i;
            }
            father[first] = 0;
            sect[0] = true;
        }

        private int find(int i) {
            if (father[i] != i) {
                father[i] = find(father[i]);
            }
            return father[i];
        }

        public void union(int i, int j) {
            int fatheri = find(i);
            int fatherj = find(j);
            if (fatheri != fatherj) {
                father[fatherj] = fatheri;
                sect[fatheri] |= sect[fatherj];
            }
        }

        public boolean know(int i) {
            return sect[find(i)];
        }

        //并查集的孤立
        public void isolate(int i) {
            father[i] = i;
        }
    }

    public class UnionFind2 {
        int[] p;

        public UnionFind2(int n, int first) {
            p = new int[n];
            for (int i = 0; i < n; i++) {
                p[i] = i;
            }
            p[first] = 0;
        }

        public int find(int i) {
            if (i != p[i]) {
                p[i] = find(p[i]);
            }
            return p[i];
        }

        public void union(int i, int j) {
            int iRoot = find(i);
            int jRoot = find(j);
            if (iRoot != jRoot) p[iRoot] = jRoot;
        }

        public boolean connected(int i, int j) {
            return find(i) == find(j);
        }

        public void isolate(int i) {
            p[i] = i;
        }
    }

    public class UnionFind3 {
        int[] p;
        boolean[] secret;

        public UnionFind3(int n, int first) {
            p = new int[n];
            secret = new boolean[n];
            for (int i = 0; i < n; i++) {
                p[i] = i;
            }
            p[first] = 0;
            secret[0] = true;
        }

        public int find(int i) {
            if (i != p[i]) {
                p[i] = find(p[i]);
            }
            return p[i];
        }

        public void union(int i, int j) {
            int iRoot = find(i);
            int jRoot = find(j);
            if (iRoot != jRoot) {
                p[iRoot] = jRoot;
                secret[jRoot] |= secret[iRoot];
            }
        }

        public boolean know(int i) {
            return secret[find(i)];
        }

        public void isolate(int i) {
            p[i] = i;
        }
    }

    //左神版本的
    public List<Integer> findAllPeople1(int n, int[][] meetings, int firstPerson) {
        UnionFind1 uf = new UnionFind1(n, firstPerson);
        int m = meetings.length;
        Arrays.sort(meetings, (a, b) -> a[2] - b[2]);
        int[] help = new int[m << 1];
        help[0] = meetings[0][0];
        help[1] = meetings[0][1];
        int size = 2;
        for (int i = 1; i < m; i++) {
            if (meetings[i][2] != meetings[i - 1][2]) {
                setSecrets(help, size, uf);
                help[0] = meetings[i][0];
                help[1] = meetings[i][1];
                size = 2;
            } else {
                help[size++] = meetings[i][0];
                help[size++] = meetings[i][1];
            }
        }
        setSecrets(help, size, uf);
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (uf.know(i)) {
                ans.add(i);
            }
        }
        return ans;
    }

    public void setSecrets(int[] help, int size, UnionFind1 uf) {
        for (int i = 0; i < size; i += 2) {
            uf.union(help[i], help[i + 1]);
        }
        for (int i = 0; i < size; i++) {
            if (!uf.know(help[i])) {
                //这里的孤立是因为不知道秘密的团体在该时刻是一起的，下个集合就不是了，所以要解散
                uf.isolate(help[i]);
            }
        }
    }

    //参考外文站帖子写的稍微简洁的
    public List<Integer> findAllPeople2(int n, int[][] meetings, int firstPerson) {
        UnionFind2 uf = new UnionFind2(n, firstPerson);
        int m = meetings.length;
        Arrays.sort(meetings, (a, b) -> a[2] - b[2]);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < m; ) {
            list.clear();
            int num = meetings[i][2];
            for (; i < m && num == meetings[i][2]; i++) {
                uf.union(meetings[i][0], meetings[i][1]);
                list.add(meetings[i][0]);
                list.add(meetings[i][1]);
            }
            for (Integer in : list) {
                if (!uf.connected(0, in)) uf.isolate(in);
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (uf.connected(0, i)) ans.add(i);
        }
        return ans;
    }

    //整合自己最熟悉的版本
    public List<Integer> findAllPeople3(int n, int[][] meetings, int firstPerson) {
        UnionFind3 uf = new UnionFind3(n, firstPerson);
        int m = meetings.length;
        Arrays.sort(meetings, (a, b) -> a[2] - b[2]);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < m; ) {
            list.clear();
            int num = meetings[i][2];
            for (; i < m && num == meetings[i][2]; i++) {
                uf.union(meetings[i][0], meetings[i][1]);
                list.add(meetings[i][0]);
                list.add(meetings[i][1]);
            }
            for (Integer j : list) {
                if (!uf.know(j)) uf.isolate(j);
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (uf.know(i)) ans.add(i);
        }
        return ans;
    }
}
