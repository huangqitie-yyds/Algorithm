package LeetCodeEveryDay;

import java.util.Arrays;

public class Code_2022_01_12 {
    //题目链接:https://leetcode-cn.com/problems/increasing-triplet-subsequence/
    public static boolean increasingTriplet(int[] nums) {
        //维护两个数组left,right
        //left[i]表示第i位置数左边的最小值
        //right[i]表示第i位置数右边的最大值
        //时间复杂度和空间复杂度都是O(n)
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        //第一个数左边没有比他小的数设置为系统最大值
        left[0] = min;
        //最后一个数右边没有比他大的数设置为系统最小值
        right[n - 1] = max;
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] < min) min = nums[i];
            left[i + 1] = min;
        }
        for (int i = n - 1; i > 0; i--) {
            if (nums[i] > max) max = nums[i];
            right[i - 1] = max;
        }
        for (int i = 1; i < n - 1; i++) {
            if (left[i] < nums[i] && right[i] > nums[i]) return true;
        }
        return false;
    }

    //进阶：你能实现时间复杂度为 O(n) ，空间复杂度为 O(1) 的解决方案
    //利用贪心维护遍历过程中最小和第二小的数，发现比他们大的就返回true
    //注意这种维护方式的代码技巧
    public static boolean increasingTriplet1(int[] nums) {
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num <= first) {
                first = num;
            } else if (num <= second) {
                second = num;
            } else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] num = {2, 4, -2, -3};
        System.out.println(increasingTriplet(num));
    }
}
