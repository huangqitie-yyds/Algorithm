package LeetCodeEveryDay;

public class Code_2021_12_04 {
    //题目链接:https://leetcode-cn.com/problems/ransom-note/
    public boolean canConstruct1(String ransomNote, String magazine) {
        int[] count = new int[26];
        //统计字符串词频
        for (int i = 0; i < ransomNote.length(); i++) {
            count[ransomNote.charAt(i) - 'a']++;
        }
        for (int i = 0; i < magazine.length(); i++) {
            count[magazine.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) return false;
        }
        return true;
    }

    //官方题解做法
    //统计magazine的词频更加优雅一点
    public boolean canConstruct2(String ransomNote, String magazine) {
        if (magazine.length() < ransomNote.length()) return false;
        int[] count = new int[26];
        //统计magazine词频
        for (char c : magazine.toCharArray()) {
            count[c - 'a']++;
        }
        for (char c : ransomNote.toCharArray()) {
            if (--count[c - 'a'] < 0) return false;
        }
        return true;
    }
}
