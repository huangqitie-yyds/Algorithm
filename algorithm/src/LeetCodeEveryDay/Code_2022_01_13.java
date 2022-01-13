package LeetCodeEveryDay;

import java.util.logging.Level;

public class Code_2022_01_13 {
    //题目链接:https://leetcode-cn.com/problems/largest-number-at-least-twice-of-others/
    public int dominantIndex(int[] nums) {
        //最大值的下标
        int index = -1;
        int m1 = -1, m2 = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > m1) {
                m2 = m1;
                m1 = nums[i];
                index = i;
            } else if (nums[i] > m2) {
                m2 = nums[i];
            }
        }
        return m1 >= 2 * m2 ? index : -1;
    }
}
