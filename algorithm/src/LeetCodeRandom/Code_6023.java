package LeetCodeRandom;

public class Code_6023 {
    //题目链接:https://leetcode-cn.com/problems/minimum-white-tiles-after-covering-with-carpets/
    public int minimumWhiteTiles(String floor, int numCarpets, int carpetLen) {
        int n = floor.length(), m = numCarpets, len = carpetLen;
        //dp[i][j]代表考虑前i个砖块使用j块地毯所剩余的最少白色数量
        int[][] dp = new int[n + 1][m + 1];
        //地毯为0时
        for (int i = 1; i <= n; i++) {
            dp[i][0] = dp[i - 1][0] + floor.charAt(i - 1) - '0';
        }
        for (int j = 1; j <= m; j++) {
            //当i<=j*len时 dp[i][j]=0;
            for (int i = j*len+1; i <= n; i++) {
                dp[i][j]=Math.min(dp[i-1][j]+floor.charAt(i - 1) - '0',dp[i-len][j-1]);
            }
        }
        return dp[n][m];
    }
}
