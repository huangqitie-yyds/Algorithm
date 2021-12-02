package LeetCodeEveryDay;

import java.util.Arrays;

public class Code_2021_12_02 {
   //题目链接:https://leetcode-cn.com/problems/relative-ranks/
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
