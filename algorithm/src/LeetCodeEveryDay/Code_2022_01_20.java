package LeetCodeEveryDay;

public class Code_2022_01_20 {
    //题目链接:https://leetcode-cn.com/problems/stone-game-ix/
    //挺有意思的一个博弈题
    public boolean stoneGameIX(int[] stones) {
        int[] num = new int[3];
        for (int i : stones) {
            num[i % 3]++;
        }
        if ((num[0] & 1) == 0) {
            return num[1] >= 1 && num[2] >= 1;
        }
        return Math.abs(num[1] - num[2]) > 2;
    }
}
