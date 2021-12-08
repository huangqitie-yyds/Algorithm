package LeetCodeRandom;

import java.util.HashSet;
import java.util.Set;

public class Code_753 {
    //题目链接:https://leetcode.com/problems/cracking-the-safe/
    //欧拉路径
    //仍然使用Hierholzer算法
    static Set<Integer> visit = new HashSet<>();
    static StringBuilder ans = new StringBuilder();

    public static String crackSafe(int n, int k) {
        //这个常量是获取n位数字的后n-1位，相当于该节点的下一个节点的公共部分
        int high = (int) Math.pow(10, n - 1);
        dfs(0, k, high);
        //本来我们是从0000(n个0)开始递归,现在是从0开始相当于去掉了n-1个零
        //而由于递归行为，答案是从后往前的，所以在最后补上n-1个零即可
        for (int i = 1; i < n; i++) {
            ans.append('0');
        }
        return ans.toString();
    }

    //Hierholzer递归
    public static void dfs(int i, int k, int high) {
        for (int j = 0; j < k; j++) {
            //新节点
            int num = i * 10 + j;
            if (!visit.contains(num)) {
                visit.add(num);
                dfs(num % high, k, high);
                ans.append(j);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(crackSafe(3, 2));
    }
}
