package LeetCodeEveryDay;

public class Code_2021_12_01 {

    //题目链接:https://leetcode-cn.com/problems/consecutive-characters/
    public int maxPower1(String s) {
        int count=1,ans=1;
        for(int i=1;i<s.length();i++){
            if(s.charAt(i-1)==s.charAt(i)){
                count++;
            }else {
                ans=Math.max(ans,count);
                count=1;
            }
        }
        //最后还是要记下答案比如这种case ："CCC"
        ans=Math.max(ans,count);
        return ans;
    }
    //在第一个if里更新会好一点
    public int maxPower2(String s) {
        int count=1,ans=1;
        for(int i=1;i<s.length();i++){
            if(s.charAt(i-1)==s.charAt(i)){
                count++;
                ans=Math.max(ans,count);
            }else {
                count=1;
            }
        }
        return ans;
    }
}
