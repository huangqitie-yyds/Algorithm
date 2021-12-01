package LeetCodeEveryDay;

import java.util.Arrays;

public class Code_2021_12_02 {
    //    506. 相对名次
//    给你一个长度为 n 的整数数组 score ，其中 score[i] 是第 i 位运动员在比赛中的得分。所有得分都 互不相同 。
//    运动员将根据得分 决定名次 ，其中名次第 1 的运动员得分最高，名次第 2 的运动员得分第 2 高，依此类推。运动员的名次决定了他们的获奖情况：
//    名次第 1 的运动员获金牌 "Gold Medal" 。
//    名次第 2 的运动员获银牌 "Silver Medal" 。
//    名次第 3 的运动员获铜牌 "Bronze Medal" 。
//    从名次第 4 到第 n 的运动员，只能获得他们的名次编号（即，名次第 x 的运动员获得编号 "x"）。
//    使用长度为 n 的数组 answer 返回获奖，其中 answer[i] 是第 i 位运动员的获奖情况。
//    示例 1：
//    输入：score = [5,4,3,2,1]
//    输出：["Gold Medal","Silver Medal","Bronze Medal","4","5"]
//    解释：名次为 [1st, 2nd, 3rd, 4th, 5th] 。
//    示例 2：
//    输入：score = [10,3,8,9,4]
//    输出：["Gold Medal","5","Bronze Medal","Silver Medal","4"]
//    解释：名次为 [1st, 5th, 3rd, 2nd, 4th] 。
//    提示：
//    n == score.length
//    1 <= n <= 104
//    0 <= score[i] <= 106
//    score 中的所有值互不相同
    public String[] findRelativeRanks1(int[] score) {
        String[] ans = new String[score.length];
        int[] num = Arrays.copyOf(score, score.length);
        Arrays.sort(num);
        for (int i = 0; i < score.length; i++) {
            //rank代表名次
            int rank = findRank(score[i], num);
            if (rank == 1) {
                ans[i] = "Gold Medal";
            } else if (rank == 2) {
                ans[i] = "Silver Medal";
            } else if (rank == 3) {
                ans[i] = "Bronze Medal";
            } else {
                ans[i] = String.valueOf(rank);
            }
        }
        return ans;
    }

    public int findRank(int n, int[] score) {
        int l = 0, r = score.length - 1;
        int pos = 0;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (score[mid] == n) {
                pos = mid;
                break;
            } else if (score[mid] < n) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return score.length - pos;
    }

    //题解更加简便做法时间复杂度和空间复杂度并没有减少
    public String[] findRelativeRanks2(int[] score) {
        int n = score.length;
        int[][] arr = new int[n][2];
        String[] ans = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i][0] = score[i];
            arr[i][1] = i;
        }
        Arrays.sort(arr, (a, b) -> (b[0] - a[0]));
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                ans[arr[i][1]] = "Gold Medal";
            } else if (i == 1) {
                ans[arr[i][1]] = "Silver Medal";
            } else if (i == 2) {
                ans[arr[i][1]] = "Bronze Medal";
            } else {
                ans[arr[i][1]]=String.valueOf(i+1);
            }
        }
        return ans;
    }
}
