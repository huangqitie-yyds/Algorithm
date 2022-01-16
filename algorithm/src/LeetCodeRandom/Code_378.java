package LeetCodeRandom;

import java.util.PriorityQueue;

public class Code_378 {
    //题目链接:https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix/
    //使用堆
    public static int kthSmallest(int[][] matrix, int k) {
        //时间复杂度klogn
        PriorityQueue<int[]> p = new PriorityQueue<>((o1, o2) -> matrix[o1[0]][o1[1]] - matrix[o2[0]][o2[1]]);
        for (int i = 0; i < matrix.length; i++) {
            p.add(new int[]{i, 0});
        }
        while (k-- > 1 && !p.isEmpty()) {
            int[] poll = p.poll();
            if (poll[1] + 1 < matrix[0].length) {
                p.add(new int[]{poll[0], poll[1] + 1});
            }
        }
        return matrix[p.peek()[0]][p.peek()[1]];

    }

    //二分时间复杂度nlog(r-l)
    public static int kthSmallest2(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0];
        int right = matrix[n - 1][n - 1];
        int ans = 0;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (check(matrix, mid, k, n)) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    public static boolean check(int[][] matrix, int mid, int k, int n) {
        int i = n - 1;
        int j = 0;
        int num = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] <= mid) {
                num += i + 1;
                j++;
            } else {
                i--;
            }
        }
        return num >= k;
    }

    public static int kthSmallest3(int[][] matrix, int k) {
        //Top(K)问题
        //时间复杂度O(n^2*logk)
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> (b - a));
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[i].length; ++j) {
                if (q.size() < k) {
                    q.add(matrix[i][j]);
                } else if (matrix[i][j] < q.peek()) {
                    q.poll();
                    q.add(matrix[i][j]);
                }
            }
        }
        return q.peek();
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2}, {153, 1000}};
        System.out.println(kthSmallest2(matrix, 3));
    }
}
