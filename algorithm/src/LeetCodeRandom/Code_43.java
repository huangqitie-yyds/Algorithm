package LeetCodeRandom;

public class Code_43 {
    //题目链接:https://leetcode-cn.com/problems/multiply-strings/
    //字符串相乘
    //常识:一个n位数与一个m位数相乘结果的位数必然为n+m和n+m-1之一
    //为了一般性的结论，我们统一按照n+m位来算最后去掉前导0就可以
    public String multiply(String num1, String num2) {
        //零因子单独处理
        if (num1.equals("0") || num2.equals("0")) return "0";
        int n = num1.length(), m = num2.length();
        int[] result = new int[n + m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                result[i + j + 1] += mul;
            }
        }
        for (int i = n + m - 1; i >= 1; i--) {
            result[i - 1] += result[i] / 10;
            result[i] %= 10;
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            //去掉前导0
            if (i == 0 && result[i] == 0) continue;
            ans.append(result[i]);
        }
        return ans.toString();
    }
}
