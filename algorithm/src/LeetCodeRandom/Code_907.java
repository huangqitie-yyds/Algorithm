package LeetCodeRandom;

import java.util.Stack;

public class Code_907 {
    //题目链接:https://leetcode-cn.com/problems/sum-of-subarray-minimums/
    //启发性的思想:
    //最直白的思维:两重循环遍历所有的子数组，再遍历每个子数组找到最小值
    //整个时间复杂度为O(n^3),正如题意所说
    //"先确定了子数组再去找子数组的最小值"
    //那把顺序反过来就是
    //"先确定一个数做最小值再去找符合他做最小值的子数组"
    //问题就转化为找到该数(i)左边离他最近的且比他小的位置x，以及右边同样的位置y
    //那么以该数字做最小值的子数组的答案就是(i-x)*(y-i)
    public static int sumSubarrayMins(int[] arr) {
        int[][] count = getLeftAndRight1(arr);
        long ans = 0;
        for (int i = 0; i < arr.length; i++) {
            long left = i - count[i][0];
            long right = count[i][1] - i;
            ans += left * right * (long) arr[i];
            ans %= 1000000007;
        }
        return (int) ans;
    }

    //找到每个位置左边离他最近而且比他小的位置x,arr[x]<arr[i]
    //找到每个位置右边离他最近而且不大于他的位置y,arr[y]<=arr[i];
    //使用单调栈是最合适的了
    //这里使用系统实现的栈常数时间不是很好
    public static int[][] getLeftAndRight1(int[] arr) {
        int[][] count = new int[arr.length][2];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[i] <= arr[stack.peek()]) {
                int index = stack.pop();
                count[index][1] = i;
                count[index][0] = -1;
                if (!stack.isEmpty()) {
                    count[index][0] = stack.peek();
                }
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int index = stack.pop();
            count[index][1] = arr.length;
            count[index][0] = -1;
            if (!stack.isEmpty()) {
                count[index][0] = stack.peek();
            }
        }
        return count;
    }

    //使用数组来模拟系统栈
    public static int[][] getLeftAndRight2(int[] arr) {
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
        int[] arr = {3, 1, 2, 4};
        System.out.println(sumSubarrayMins(arr));
    }
}
