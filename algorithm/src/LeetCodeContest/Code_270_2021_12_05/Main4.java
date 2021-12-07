package LeetCodeContest.Code_270_2021_12_05;

import java.util.*;

public class Main4 {

    //左神的解答
    //需要知道欧拉路径
    //题目链接:https://leetcode-cn.com/problems/valid-arrangement-of-pairs/
    public static int[][] validArrangement1(int[][] pairs) {
        HashMap<Integer, LinkedList<Integer>> nexts = new HashMap<>();
        HashMap<Integer, Integer> degrees = new HashMap<>();
        for (int[] pair : pairs) {
            nexts.putIfAbsent(pair[0], new LinkedList<>());
            nexts.putIfAbsent(pair[1], new LinkedList<>());
            degrees.putIfAbsent(pair[0], 0);
            degrees.putIfAbsent(pair[1], 0);
        }
        for (int[] pair : pairs) {
            nexts.get(pair[0]).add(pair[1]);
            degrees.put(pair[0], degrees.get(pair[0]) - 1);
            degrees.put(pair[1], degrees.get(pair[1]) + 1);
        }
        int any = pairs[0][0];
        Integer from = null;
        for (Integer cur : degrees.keySet()) {
            if (degrees.get(cur) == -1) {
                from = cur;
                break;
            }
        }
        ArrayList<int[]> record = new ArrayList<>();
        //hierholzer算法递归实现
        dfs(from != null ? from : any, nexts, record);
        int[][] ans = new int[record.size()][2];
        for (int i = 0, j = record.size() - 1; i < record.size(); i++, j--) {
            ans[j][0] = record.get(i)[0];
            ans[j][1] = record.get(i)[1];
        }
        return ans;
    }

    public static void dfs(int from, HashMap<Integer, LinkedList<Integer>> nexts, ArrayList<int[]> record) {
        LinkedList<Integer> next = nexts.get(from);
        while (!next.isEmpty()) {
            int to = next.poll();
            dfs(to, nexts, record);
            record.add(new int[]{from, to});
        }
    }

    //hierholzer算法非递归实现
    //参照波波老师的题解
    public static int[][] validArrangement2(int[][] pairs) {
        //建图数据结构
        HashMap<Integer, LinkedList<Integer>> nexts = new HashMap<>();
        HashMap<Integer, Integer> degrees = new HashMap<>();
        for (int[] pair : pairs) {
            nexts.putIfAbsent(pair[0], new LinkedList<>());
            nexts.putIfAbsent(pair[1], new LinkedList<>());
            degrees.putIfAbsent(pair[0], 0);
            degrees.putIfAbsent(pair[1], 0);
        }
        for (int[] pair : pairs) {
            nexts.get(pair[0]).add(pair[1]);
            degrees.put(pair[0], degrees.get(pair[0]) - 1);
            degrees.put(pair[1], degrees.get(pair[1]) + 1);
        }
        Integer from = pairs[0][0];
        for (Integer cur : degrees.keySet()) {
            if (degrees.get(cur) == -1) {
                from = cur;
                break;
            }
        }
        //非递归实现hierholzer
        Stack<Integer> stack = new Stack<>();
        ArrayList<Integer> record = new ArrayList<>();
        int cur = from;
        //无效数字随便填什么目的就是为了一开始栈不为空
        stack.push(cur);
        while (!stack.isEmpty()) {
            if (!nexts.get(cur).isEmpty()) {
                stack.push(cur);
                int w = nexts.get(cur).poll();
                cur = w;
            } else {
                record.add(cur);
                cur = stack.pop();
            }
        }
        int[][] ans = new int[record.size() - 1][2];
        for (int i = 1, j = ans.length - 1; i < record.size(); i++, j--) {
            ans[j][0]=record.get(i);
            ans[j][1]=record.get(i-1);
        }
        return ans;
    }
}
