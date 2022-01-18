package LeetCodeRandom;

import java.util.*;

public class Code_22 {
    //题目链接:https://leetcode-cn.com/problems/generate-parentheses/
    List<String> ans = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        dfs("", n, n);
        return ans;
    }

    //left和right剩余可用括号数
    private void dfs(String s, int left, int right) {
        if (left == 0 && right == 0) {
            ans.add(s);
            return;
        }
        if (left > right) return;
        if (left > 0) {
            dfs(s + "(", left - 1, right);
        }
        if (right > 0) {
            dfs(s + ")", left, right - 1);
        }
    }

    //使用非递归
    public List<String> generateParenthesis1(int n) {
        List<String> ans = new ArrayList<>();
        //这里的队列可以换成栈
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node("", n, n));
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.left == 0 && node.right == 0) {
                ans.add(node.res);
            }
            if (node.left > node.right) {
                continue;
            }
            if (node.left > 0) {
                queue.add(new Node(node.res + "(", node.left - 1, node.right));
            }
            if (node.right > 0) {
                queue.add(new Node(node.res + ")", node.left, node.right - 1));
            }
        }
        return ans;
    }

    class Node {
        String res;
        int left;
        int right;

        public Node(String res, int left, int right) {
            this.res = res;
            this.left = left;
            this.right = right;
        }
    }

}
