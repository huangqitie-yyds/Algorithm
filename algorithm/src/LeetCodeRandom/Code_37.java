package LeetCodeRandom;

public class Code_37 {
    //题目链接：https://leetcode.cn/problems/sudoku-solver/
    //解数独
    public static void solveSudoku(int[][] board) {
        dfs(board, 0, 0);
    }

    //深度优先搜索每一种可能性
    public static boolean dfs(int[][] board, int x, int y) {
        //递归终止条件
        if (y == 9) {
            return dfs(board, x + 1, 0);
        }
        if (x == 9) {
            return true;
        }
        if (board[x][y] != 0) {
            return dfs(board, x, y + 1);
        }
        for (int i = 1; i <= 9; i++) {
            if (!isValid(board, x, y, i)) {
                continue;
            }
            board[x][y] = i;
            if (dfs(board, x, y + 1)) {
                return true;
            }
            board[x][y] = 0;
        }
        return false;
    }

    public static boolean isValid(int[][] board, int x, int y, int ch) {
        for (int i = 0; i < 9; i++) {
            if (board[x][i] == ch) {
                return false;
            }
            if (board[i][y] == ch) {
                return false;
            }
            if (board[(x / 3) * 3 + i / 3][(y / 3) * 3 + i % 3] == ch) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] board = {
                {0, 8, 0, 0, 0, 0, 0, 0, 3},
                {0, 4, 0, 0, 7, 0, 5, 9, 1},
                {0, 0, 0, 8, 0, 0, 6, 8, 0},
                {0, 3, 0, 7, 0, 0, 0, 0, 9},
                {0, 4, 5, 0, 8, 9, 6, 0, 0},
                {0, 0, 9, 0, 5, 0, 0, 0, 0},
                {0, 2, 6, 0, 0, 0, 0, 1, 0},
                {4, 7, 3, 0, 9, 0, 8, 6, 0},
                {9, 0, 8, 0, 2, 6, 7, 0, 0}
        };
        solveSudoku(board);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
