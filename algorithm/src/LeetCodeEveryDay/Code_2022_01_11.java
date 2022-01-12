package LeetCodeEveryDay;

import java.util.*;

public class Code_2022_01_11 {
    final int MAX = (int) 1e6;
    int step = 0;
    int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    Set<String> set = new HashSet<>();

    //题目链接:https://leetcode-cn.com/problems/escape-a-large-maze/solution/bian-cheng-xiong-bfs-acmjin-pai-ti-jie-b-dquc/
    //思路参照ACM金牌题解
    //根据数据10^6次方显然不能直接BFS(时间复杂度O(10^12)必然超时)
    //所以突破点在于最多200的障碍
    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        for (int[] block : blocked) {
            set.add(block[0] + "_" + block[1]);
        }
        step = blocked.length * (blocked.length - 1) / 2;
        //同时从终点和起点出发bfs
        return isArrive(source, target) && isArrive(target, source);
    }

    public boolean isArrive(int[] source, int[] target) {
        //存储访问过的点
        Set<String> visited = new HashSet<>();
        Queue<int[]> q = new LinkedList<>();
        visited.add(source[0] + "_" + source[1]);
        q.add(source);
        while (!q.isEmpty()) {
            int[] poll = q.poll();
            //比较数组元素是否一致的API调用
            //1.在不超过step步数情况下能到终点则能到
            //2.超过步数则跳出了包围，接下来可以直通终点
            if (Arrays.equals(poll, target)) return true;
            if (visited.size() > step) return true;
            for (int i = 0; i < 4; i++) {
                int nx = poll[0] + dir[i][0];
                int ny = poll[1] + dir[i][1];
                String s = nx + "_" + ny;
                if (nx < 0 || nx >= MAX || ny < 0 || ny >= MAX || visited.contains(s) || set.contains(s)) {
                    continue;
                }
                q.add(new int[]{nx, ny});
                visited.add(s);
            }
        }
        return false;
    }
}
