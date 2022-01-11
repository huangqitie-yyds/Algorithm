package LeetCodeEveryDay;

public class Code_2022_01_10 {
    //题目链接:https://leetcode-cn.com/problems/additive-number/
    //参考官方题解的思路唯一不同在于后续判断非递归改为递归
    public static boolean isAdditiveNumber(String num) {
        int N = num.length();
        if (N < 3) return false;
        //暴力枚举所有前两位数字
        //secondStart表示第二位数的开始位置,secondEnd表示第二位数的结束位置
        for (int secondStart = 1; secondStart < N - 1; secondStart++) {
            //0-secondStart-1有前导0而且位数大于1则直接break
            if (num.charAt(0) == '0' && secondStart != 1) break;
            for (int secondEnd = secondStart; secondEnd < N; secondEnd++) {
                //第二位数前导0判断
                if (num.charAt(secondStart) == '0' && secondEnd != secondStart) break;
                if (valid(0, secondStart - 1, secondStart, secondEnd, num)) return true;
            }
        }
        return false;
    }

    //判断是否是合法序列
    public static boolean valid(int firstStart, int firstEnd, int secondStart, int secondEnd, String num) {
        int N = num.length();
        String third = strAdd(firstStart, firstEnd, secondStart, secondEnd, num);
        int thirdStart = secondEnd + 1;
        int thirdEnd = secondEnd + third.length();
        if (thirdEnd >= N || !num.substring(thirdStart, thirdEnd + 1).equals(third)) return false;
        if (thirdEnd == N - 1) return true;
        return valid(secondStart, secondEnd, thirdStart, thirdEnd, num);
    }

    public static String strAdd(int firstStart, int firstEnd, int secondStart, int secondEnd, String num) {
        StringBuilder ans = new StringBuilder();
        //carry表示进位
        int cur = 0, carry = 0;
        while (firstEnd >= firstStart || secondEnd >= secondStart || carry != 0) {
            cur = carry;
            if (firstEnd >= firstStart) {
                cur += num.charAt(firstEnd) - '0';
            }
            firstEnd--;
            if (secondEnd >= secondStart) {
                cur += num.charAt(secondEnd) - '0';
            }
            secondEnd--;
            carry = cur / 10;
            cur %= 10;
            ans.append((char) (cur + '0'));
        }
        return ans.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(isAdditiveNumber("112358"));
    }
}
