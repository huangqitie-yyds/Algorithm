package LeetCodeRandom;

import java.util.Arrays;

public class Code_753 {
    //题目链接:https://leetcode.com/problems/cracking-the-safe/
    //左神解答
    public static String crackSafe(int n, int k) {
        int size = (int) Math.pow(k, n);
        int offset = size / k;
        int[] ans = new int[size - 1 + n];
        int[] choose = new int[offset];
        Arrays.fill(choose, k - 1);
        for (int i = n - 1, first = 0, pre = 0; i < ans.length; i++, first++) {
            System.out.println(pre);
            ans[i] = choose[pre]--;
            //这是K进制下取pre的后k-2位加上当前选择数->新的pre
            pre = pre * k - ans[first] * offset + ans[i];
        }
        StringBuilder builder = new StringBuilder();
        for (int num : ans) {
            builder.append(num);
        }
        return builder.toString();
    }
}
