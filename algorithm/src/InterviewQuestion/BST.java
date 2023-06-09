package InterviewQuestion;


import java.util.ArrayList;
import java.util.Scanner;


//1、新建Java文件Tree.java，定义如下数组，例如：
//data = [5,8,1,9,6,3,2,4,8,7]
//2、定义一个二叉树，将数组中每个数字及其在数组中的位置存入二叉树的的叶子结点中，左结点值比父结点小，右结点值比父结点大，生成的二叉树如下：
//3、编译运行Tree.java文件，提示输入一个数字(please input data:)，在二叉树中查找其位置：
//1）输入1，则提示：data:1 position:3
//2）输入8，则提示：data:8 position:2,9
//3）输入10，则提示：data:10 not exist
public class BST {
    TreeNode node;
    public BST(int[] num) {
        for (int i = 0; i < num.length; i++) {
            node = insertNode(num[i], i + 1, node);
        }
    }

    /**
     * @param data     数组元素
     * @param position 数组元素的位置
     * @param root
     * @return
     */
    public static TreeNode insertNode(int data, int position, TreeNode root) {
        if (root == null) {
            ArrayList<Integer> positionList = new ArrayList<>();
            positionList.add(position);
            root = new TreeNode(data, positionList);
            return root;
        }
        if (data == root.data) {
            root.positionList.add(position);
        } else if (data < root.data) {
            root.leftNode = insertNode(data, position, root.leftNode);
        } else {
            root.rightNode = insertNode(data, position, root.rightNode);
        }
        return root;
    }

    public static ArrayList<Integer> searchData(int data, TreeNode root) {
        if (root == null) {
            return null;
        }
        if (data == root.data) {
            return root.positionList;
        } else if (data < root.data) {
            return searchData(data, root.leftNode);
        } else {
            return searchData(data, root.rightNode);
        }
    }

    public static class TreeNode {
        TreeNode leftNode;
        TreeNode rightNode;
        int data;
        ArrayList<Integer> positionList;

        public TreeNode(int data, ArrayList<Integer> positionList) {
            this.data = data;
            this.positionList = positionList;
        }
    }


    public static void main(String[] args) {
        int[] data = {5, 8, 1, 9, 6, 3, 2, 4, 8, 7};
        BST tree = new BST(data);
        Scanner sc = new Scanner(System.in);
        System.out.println("please input data:");
        int num = sc.nextInt();
        ArrayList<Integer> positionList = tree.searchData(num, tree.node);
        if (positionList != null) {
            String s = positionList.toString();
            s = s.substring(1, s.length() - 1);
            System.out.println("data:" + num + " position:" + s);
        } else {
            System.out.println("data:" + num + " not exist");
        }
    }

}
