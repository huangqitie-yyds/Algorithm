package LeetCodeEveryDay;

public class Code_2022_01_17 {
    //题目链接:https://leetcode-cn.com/problems/count-vowels-permutation/submissions/
    public int countVowelPermutation(int n) {
        int mod = (int) 1e9 + 7;
        //a表示以a结尾的字符串的个数以此类推
        long a = 1, e = 1, i = 1, o = 1, u = 1;
        for (int k = 2; k <= n; k++) {
            long aa = (e + i + u) % mod;
            long ee = (a + i) % mod;
            long ii = (e + o) % mod;
            long oo = i % mod;
            long uu = (i + o) % mod;
            a = aa;
            e = ee;
            i = ii;
            o = oo;
            u = uu;
        }
        return (int) ((a + e + i + o + u) % mod);
    }

    //矩阵快速幂
    public int countVowelPermutation1(int n) {
        int mod = (int) 1e9 + 7;
        //五阶的矩阵
        long[][] m = {
                {0, 1, 1, 0, 1},
                {1, 0, 1, 0, 0},
                {0, 1, 0, 1, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 1, 0}
        };
        long[][] res = fastPow(m, n - 1, mod);
        //根据题意我们算出m的n-1次方，再将所有元素相加除以模就是答案
        long ans = 0;
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[0].length; j++) {
                ans = (ans + res[i][j]) % mod;
            }
        }
        return (int) ans;
    }

    private long[][] fastPow(long[][] m, int n, int mod) {
        long[][] res = new long[m.length][m[0].length];
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }
        long[][] t = m;
        for (; n > 0; n >>= 1) {
            if ((n & 1) != 0) {
                res = muliMatrix(res, t, mod);
            }
            t = muliMatrix(t, t, mod);
        }
        return res;
    }

    private long[][] muliMatrix(long[][] m, long[][] n, int mod) {
        long[][] ans = new long[m.length][n[0].length];
        for (int i = 0; i < ans.length; i++) {
            for (int j = 0; j < ans[0].length; j++) {
                for (int k = 0; k < n.length; k++) {
                    ans[i][j] += (ans[i][j] + m[i][k] * n[k][j]) % mod;
                }
            }
        }
        return ans;
    }
}
