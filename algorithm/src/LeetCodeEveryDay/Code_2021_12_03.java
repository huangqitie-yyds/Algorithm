package LeetCodeEveryDay;

import java.util.Arrays;

public class Code_2021_12_03 {
    //题目链接:https://leetcode-cn.com/problems/maximize-sum-of-array-after-k-negations/
    public int largestSumAfterKNegations(int[] nums, int k) {
        Arrays.sort(nums);
        int index = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 0) break;
            //统计小于0的最右位置
            if (nums[i] < 0 && k > 0) {
                nums[i] = -nums[i];
                k--;
                index = i;
            }
        }
        //如果k剩余奇数才考虑
        //偶数和零一样不用考虑
        if ((k & 1) == 1) {
            if (index >= 0 && index + 1 < nums.length) {
                //比较当负数全部变为正数后新的最小值是哪个
                if (nums[index] < nums[index + 1]) {
                    nums[index] = -nums[index];
                } else {
                    nums[index + 1] = -nums[index + 1];
                }
            } else if (index + 1 == nums.length) {
                //数组全负数，k大于数组长度，变为全正数后逻辑和下面相同 只不过最小的数在右边
                nums[index] = -nums[index];
            } else {
                //数组全正数，只需要考虑第一个数
                nums[index + 1] = -nums[index + 1];
            }
        }
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            ans += nums[i];
        }
        return ans;
    }
}
