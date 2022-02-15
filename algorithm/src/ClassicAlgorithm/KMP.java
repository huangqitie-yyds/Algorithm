package ClassicAlgorithm;

public class KMP {
    //时间复杂度O(n) n为s1的长度
    public static int getIndexOf(String s1, String s2) {
        if (s1 == null || s2 == null || s2.length() < 1 || s1.length() < s2.length()) {
            return -1;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int x = 0;//str1上的配对位置
        int y = 0;//str2上的配对位置
        //O(m) m<=n
        int[] next = getNextArray(str2);
        //O(n)
        while (x < str1.length && y < str2.length) {
            if (str1[x] == str2[y]) {
                x++;
                y++;
            } else if (next[y] == -1) {//说明开始位置就对不上x应该往下走
                x++;
            } else {
                y = next[y];
            }
        }
        return y == str2.length ? x - y : -1;
    }

    public static int[] getNextArray(char[] str2) {
        if (str2.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[str2.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2;//表示哪个位置在求next数组值
        int cn = 0;//表示当前是哪个位置的值在和i-1位置的字符比较
        while (i < str2.length) {
            if (str2[i - 1] == str2[cn]) {
                next[i++] = ++cn;//这里意思是i位置的值为cn+1同时cn也会加一成为新的cn
            } else if (cn > 0) {
                cn = next[cn];//没配对上继续找前缀位置来配
            } else {
                next[i++] = 0;//cn为0且配对不上 所以为0
            }
        }
        return next;
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
        int matchSize = 5;
        int testTimes = 5000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilities, strSize);
            String match = getRandomString(possibilities, matchSize);
            if (getIndexOf(str, match) != str.indexOf(match)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }
}
