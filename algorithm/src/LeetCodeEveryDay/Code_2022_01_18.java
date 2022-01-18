package LeetCodeEveryDay;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Code_2022_01_18 {
    //题目链接:https://leetcode-cn.com/problems/minimum-time-difference/
    public int findMinDifference(List<String> timePoints) {
        int[] num = new int[timePoints.size()];
        int index = 0;
        for (String timePoint : timePoints) {
            String hh = timePoint.substring(0, 2);
            int h = 0, m = 0;
            if (hh.charAt(0) == '0') {
                h = hh.charAt(1) - '0';
            } else {
                h = Integer.valueOf(hh);
            }
            String mm = timePoint.substring(3);
            if (mm.charAt(0) == '0') {
                m = mm.charAt(1) - '0';
            } else {
                m = Integer.valueOf(mm);
            }
            int n = h * 60 + m;
            num[index++] = n;
        }
        Arrays.sort(num);
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i < num.length; i++) {
            ans = Math.min(ans, num[i] - num[i - 1] < 720 ? num[i] - num[i - 1] : 1440 + num[i - 1] - num[i]);
        }
        ans = Math.min(ans, num[0] + 1440 - num[num.length - 1]);
        return ans;
    }

    //官方题解不借用数组直接对list进行排序
    //空间复杂度降低到了O(logn)
    public int findMinDifference1(List<String> timePoints) {
        //当集合长度超过1440必然有重复值直接返回0
        if (timePoints.size() > 1440) return 0;
        Collections.sort(timePoints);
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i < timePoints.size(); i++) {
            ans = Math.min(ans, getMinute(timePoints.get(i)) - getMinute(timePoints.get(i - 1)));
        }
        ans = Math.min(ans, getMinute(timePoints.get(0)) + 1440 - getMinute(timePoints.get(timePoints.size() - 1)));
        return ans;
    }

    private int getMinute(String s) {
        return (s.charAt(0) - '0') * 600 + (s.charAt(1) - '0') * 60 + (s.charAt(3) - '0') * 10 + s.charAt(4) - '0';
    }

}
