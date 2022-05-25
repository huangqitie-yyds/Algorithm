package LeetCodeRandom;

import java.util.Stack;

public class Code_2281 {
    //题目链接:https://leetcode.cn/problems/sum-of-total-strength-of-wizards/
    //单调栈和前缀和的前缀和是907题的加强版
    public static int totalStrength(int[] strength) {
        int mod = 1000000007;
        int[][] range = getRange(strength);
        //strength的前缀和数组
//        long[] sum = new long[strength.length];
        //sum的前缀和数组
        long[] sumSum = new long[strength.length];
//        sum[0] = strength[0];
        long s = strength[0];
        sumSum[0] = strength[0];
        for (int i = 1; i < strength.length; i++) {
            s = (s + strength[i]) % mod;
            sumSum[i] = (sumSum[i - 1] + s) % mod;
        }
        long ans = 0;
        for (int i = 0; i < strength.length; i++) {
            int l = range[i][0], r = range[i][1];
            long ss = (long) (i - l) * (sumSum[r - 1] - (i - 1 < 0 ? 0 : sumSum[i - 1])) % mod -
                    (long) (r - i) * ((i - 1 < 0 ? 0 : sumSum[i - 1]) - (l - 1 < 0 ? 0 : sumSum[l - 1])) % mod;
            ans += (long) strength[i] * ss;
            ans %= mod;
        }
        return (int) (ans + mod) % mod;
    }

    //找到每个位置左边离他最近而且比他小的位置x,arr[x]<arr[i]
    //找到每个位置右边离他最近而且不大于他的位置y,arr[y]<=arr[i];
    //使用单调栈是最合适的了
    public static int[][] getRange(int[] strength) {
        int[][] ans = new int[strength.length][2];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < strength.length; i++) {
            while (!stack.isEmpty() && strength[i] <= strength[stack.peek()]) {
                int index = stack.pop();
                ans[index][1] = i;
                ans[index][0] = -1;
                if (!stack.isEmpty()) {
                    ans[index][0] = stack.peek();
                }
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int index = stack.pop();
            ans[index][1] = strength.length;
            ans[index][0] = -1;
            if (!stack.isEmpty()) {
                ans[index][0] = stack.peek();
            }
        }
        return ans;
    }
    public static int[][] getRange2(int[] arr) {
        int[][] count = new int[arr.length][2];
        int[] stack = new int[arr.length];
        int size = 0;//模拟系统栈的大小初始值为0
        for (int i = 0; i < arr.length; i++) {
            while (size != 0 && arr[i] <= arr[stack[size - 1]]) {
                int index = stack[--size];
                count[index][1] = i;
                count[index][0] = -1;
                if (size != 0) {
                    count[index][0] = stack[size - 1];
                }
            }
            stack[size++] = i;
        }
        while (size != 0) {
            int index = stack[--size];
            count[index][1] = arr.length;
            count[index][0] = -1;
            if (size != 0) {
                count[index][0] = stack[size - 1];
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 1, 2};
        System.out.println(totalStrength(arr));
    }
}
