package LeetCodeRandom;

public class Code_5 {
    //题目链接:https://leetcode-cn.com/problems/longest-palindromic-substring/submissions/
    public String longestPalindrome1(String s) {
        //方法一:动态规划 时间复杂度O(n^2)空间复杂度O(n^2)
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        dp[n - 1][n - 1] = true;
        int maxLen = 1, begin = 0;
        for (int i = 0; i < n - 1; i++) {
            dp[i][i] = true;
            dp[i][i + 1] = s.charAt(i) == s.charAt(i + 1) ? true : false;
            if (dp[i][i + 1] && maxLen < 2) {
                maxLen = 2;
                begin = i;
            }
        }
        for (int i = n - 3; i >= 0; i--) {
            for (int j = i + 2; j < n; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) ? dp[i + 1][j - 1] : false;
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    public String longestPalindrome2(String s) {
        //方法二:中心扩散法 时间复杂度O(n^2)空间复杂度O(1)
        int n = s.length();
        int begin = 0, maxLen = 1;
        for (int i = 0; i < n; i++) {
            int l1 = isCountCenter(s, i, i);
            int l2 = isCountCenter(s, i, i + 1);
            int max = Math.max(l1, l2);
            if (max > maxLen) {
                maxLen = max;
                begin = i - (maxLen - 1) / 2;
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    private int isCountCenter(String s, int i, int j) {
        int n = s.length();
        while (i >= 0 && j < n && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
        }
        return j - i - 1;
    }

    public String longestPalindrome3(String s) {
        //方法三:manacher 时间复杂度O(n)空间复杂度O(n)
        char[] str = manacherString(s);
        //该数组记录每个位置的最大回文半径
        int[] pArray = new int[str.length];
        //c是以r为边界的回文中心
        int c = -1;
        //r是能扩充到的最右边界的下一个位置
        int r = -1;
        int max = 0;
        int center = -1;
        int begin = -1;
        for (int i = 0; i < str.length; i++) {
            pArray[i] = r > i ? Math.min(pArray[2 * c - i], r - i) : 1;
            while (i + pArray[i] < str.length && i - pArray[i] >= 0) {
                if (str[i + pArray[i]] == str[i - pArray[i]]) {
                    pArray[i]++;
                } else {
                    break;
                }
            }
            if (i + pArray[i] > r) {
                r = i + pArray[i];
                c = i;
            }
            if (max < pArray[i]) {
                max = pArray[i];
                center = i;
            }
        }
        //manacher串的长度和中心位置映射回原串
        max = max - 1;
        center = (center - 1) / 2;
        begin = center - (max - 1) / 2;
        return s.substring(begin, begin + max);
    }
    private char[] manacherString(String s) {
        char[] str = s.toCharArray();
        char[] ans = new char[2 * str.length + 1];
        int index = 0;
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (i & 1) == 0 ? '#' : str[index++];
        }
        return ans;
    }
}
