package ClassicAlgorithm;

public class Manacher {
    //该算法是求字符串中最长回文子串时间复杂度可以到O(N)
    //传统求法时间复杂度是O(N^2)
    public static String manacher(String s) {
        //时间复杂度O(n)空间复杂度O(n)
        char[] str = manacherString(s);
        //该数组记录每个位置的最大回文半径
        int[] pArray = new int[str.length];
        //c是以r为边界的回文中心
        int c = -1;
        //r是能扩充到的最右边界的下一个位置
        int r = -1;
        //manacher串的回文半径减去1是原串的回文串长度
        int max = 0;
        //记录最大长度时的回文中心位置
        int center = -1;
        //原串取得最大长度的开始位置
        int begin = -1;
        //时间复杂度分析
        //r最大值是str的长度，i最大值也是str的长度
        //每次循环i和r必然有一个值增大不会回退，所以时间复杂度是O(n)
        //1.当pArray[2*c-i]<r-i时，while进入一次直接break，下次i自增
        //2.当pArray[2*c-i]>r-i时，while进入一次直接break，下次i自增
        //3.当pArray[2*c-i]==r-i时，进入while循环，r必然增加
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

    public static char[] manacherString(String str) {
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i != res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }

    // for test
    public static String right(String s) {
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

    public static int isCountCenter(String s, int i, int j) {
        int n = s.length();
        while (i >= 0 && j < n && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
        }
        return j - i - 1;
    }

    // for test
    public static String getRandomString(int possibilities, int size) {
        char[] ans = new char[(int) (Math.random() * size) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) {
        int possibilities = 5;
        int strSize = 20;
        int testTimes = 5000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilities, strSize);
            if (!manacher(str).equals(right(str))) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }
}
