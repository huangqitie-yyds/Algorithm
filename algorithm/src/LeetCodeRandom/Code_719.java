package LeetCodeRandom;

import java.util.Arrays;

public class Code_719 {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int left = 0, right = nums[n - 1] - nums[0];
        int ans = 0;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (getCount1(mid, nums) < k) {
                left = mid + 1;
            } else {
                right = mid - 1;
                ans = mid;
            }
        }
        return ans;
    }

    //这个方法尤其注意，统计小于dis的数对数目
    //利用双指针，时间复杂度O(n)
    private int getCount(int dis, int[] nums) {
        int l = 0, cnt = 0;
        for (int r = 0; r < nums.length; r++) {
            while (nums[r] - nums[l] > dis) {
                l++;
            }
            cnt += r - l;
        }
        return cnt;
    }

    //一般的双重循环遍历统计
    //时间复杂度O(n^2)
    private int getCount1(int dis, int[] nums) {
        int cnt = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] - nums[i] <= dis) cnt++;
            }
        }
        return cnt;
    }
}
