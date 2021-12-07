package LeetCodeContest.Code_270_2021_12_05;

import java.util.LinkedList;

public class Main3 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    //题目链接:https://leetcode-cn.com/problems/step-by-step-directions-from-a-binary-tree-node-to-another/submissions/
    public static String getDirections(TreeNode root, int startValue, int destValue) {
        StringBuilder ans = new StringBuilder();
        StringBuilder builder1 = new StringBuilder();
        for (char c : startToDest(root, startValue)) {
            builder1.append(c);
        }
        String s1 = builder1.toString();
        StringBuilder builder2 = new StringBuilder();
        for (char c : startToDest(root, destValue)) {
            builder2.append(c);
        }
        String s2 = builder2.toString();
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        int index = 0;
        for (int i = 0; i < Math.min(chars1.length, chars2.length) && chars1[i] == chars2[i]; i++) {
            index = i + 1;
        }
        for (int i = index; i < chars1.length; i++) ans.append("U");
        String substring = s2.substring(index);
        ans.append(substring);
        return ans.toString();
    }

    //该递归方法返回从start到dest的路径(使用双端队列不能用String拼接超时)
    public static LinkedList<Character> startToDest(TreeNode start, int dest) {
        LinkedList<Character> ans = new LinkedList<>();
        if (start == null) return null;
        if (start.val == dest) {
            return ans;
        }
        LinkedList<Character> left = startToDest(start.left, dest);
        LinkedList<Character> right = startToDest(start.right, dest);
        if (left != null) {
            ans = left;
            ans.addFirst('L');
        } else if (right != null) {
            ans = right;
            ans.addFirst('R');
        } else {
            ans = null;
        }
        return ans;
    }

    //左神的二叉树递归套路解法
    public static String getDirections2(TreeNode root, int startValue, int destValue) {
        Info all = process(root, startValue, destValue);
        StringBuilder builder = new StringBuilder();
        for (char c : all.path) {
            builder.append(c);
        }
        return builder.toString();
    }

    public static class Info {
        public boolean findStart;
        public boolean findDest;
        public LinkedList<Character> path;

        // 如果start和dest都发现了，path就是全路径
        // 如果start和dest都没有发现，path就是空路径
        // 如果单独只发现了start，path就是从start往上到当前点的路径
        // 如果单独只发现了dest，path就是从当前点往下到dest的路径
        public Info(boolean s, boolean d, LinkedList<Character> p) {
            findStart = s;
            findDest = d;
            path = p;
        }
    }
    public static Info process(TreeNode h, int start, int dest) {
        if (h == null) {
            return new Info(false, false, new LinkedList<>());
        }
        Info infoL = process(h.left, start, dest);
        if (infoL.findStart && infoL.findDest) {
            return infoL;
        }
        Info infoR = process(h.right, start, dest);
        if (infoR.findStart && infoR.findDest) {
            return infoR;
        }
        boolean findStart = h.val == start || infoL.findStart || infoR.findStart;
        boolean findDest = h.val == dest || infoL.findDest || infoR.findDest;
        LinkedList<Character> path = new LinkedList<>();
        if (infoL.findStart || infoR.findStart) {
            path = infoL.findStart ? infoL.path : infoR.path;
            path.addLast('U');
        }
        if (infoL.findDest || infoR.findDest) {
            LinkedList<Character> toDest = infoL.findDest ? infoL.path : infoR.path;
            toDest.addFirst(infoL.findDest ? 'L' : 'R');
            if (!findStart) {
                path = toDest;
            } else {
                path.addAll(toDest);
            }
        }
        return new Info(findStart, findDest, path);
    }
}