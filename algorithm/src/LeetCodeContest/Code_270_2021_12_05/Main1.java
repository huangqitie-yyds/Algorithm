package LeetCodeContest.Code_270_2021_12_05;

import java.util.TreeSet;

public class Main1 {
    public static void main(String[] args) {

    }

    public int[] findEvenNumbers(int[] digits) {
        TreeSet<Integer> set = new TreeSet<>();
        int n = digits.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (i == j || j == k || i == k) continue;
                    if (digits[i] == 0) continue;
                    if ((digits[k] & 1) == 1) continue;
                    int num = digits[i] * 100 + digits[j] * 10 + digits[k];
                    set.add(num);
                }
            }
        }
        int[] ans = new int[set.size()];
        int index = 0;
        for (int i : set) {
            ans[index++] = i;
        }
        return ans;
    }
}
