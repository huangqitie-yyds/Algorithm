package LeetCodeRandom;

public class Code_76 {
    //题目链接:https://leetcode.cn/problems/minimum-window-substring/
    public String minWindow(String s, String t) {
        if (t.length() > s.length()) return "";
        //统计还债表
        int[] num = new int[128];
        for (int i = 0; i < t.length(); i++) {
            num[t.charAt(i)]++;
        }
        int total = t.length();//还债总数
        int left = 0;//窗口是左闭右开状态
        int right = 0;
        int minlength = Integer.MAX_VALUE;//字串长度
        int begin = 0;//子串的开始索引
        while (right < s.length()) {
            //还债
            if (num[s.charAt(right)]-- > 0) {
                total--;
            }
            //债还清了
            if (total == 0) {
                //检查有没有还多了
                while (num[s.charAt(left)] < 0) {
                    num[s.charAt(left)]++;
                    left++;
                }
                if (minlength > right - left + 1) {
                    minlength = right - left + 1;
                    begin = left;
                }
            }
            right++;
        }
        return minlength == Integer.MAX_VALUE ? "" : s.substring(begin, begin + minlength);
    }
}
