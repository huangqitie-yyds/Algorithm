package ClassicAlgorithm;


public class MorrisTraversal {
    public static class Node {
        Node left;
        Node right;
        public int value;

        public Node(int value) {
            this.value = value;
        }
    }

    //morris遍历
    //时间复杂度O(n)
    //空间复杂度O(1)
    //假设来到当前节点cur，开始时cur来到头节点位置
    //1）如果cur没有左孩子，cur向右移动(cur =cur.right)
    //2）如果cur有左孩子，找到左子树上最右的节点mostRight：
    //a.如果mostRight的右指针指向空，让其指向cur，然后cur向左移动(cur =cur.left)
    //b.如果mostRight的右指针指向cur，让其指向null，然后cur向右移动(cur =cur.right)
    //3）cur为空时遍历停止
    public static void morris(Node head) {
        if (head == null) return;
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            System.out.print(cur.value + " ");
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            cur = cur.right;
        }
        System.out.println();
    }

    //morris改造成前序打印
    //遍历两次的节点第一次遍历就打印
    public static void morrisPre(Node head) {
        if (head == null) return;
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    System.out.print(cur.value + " ");
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            } else {
                System.out.print(cur.value + " ");
            }
            cur = cur.right;
        }
        System.out.println();
    }

    //morris改造成中序打印
    //遍历两次的节点第二次遍历才打印
    public static void morrisIn(Node head) {
        if (head == null) return;
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            System.out.print(cur.value + " ");
            cur = cur.right;
        }
        System.out.println();
    }

    //morris改造成后序打印
    //这个比较难搞一点
    //思路是在遍历第二边的节点的时候，把他的左子树往右的一路到最右的节点逆序打印
    //最后不要忘记打印一遍整棵树最右边界到根节点
    //由于不能使用空间 所以这里采用链表反转的方式来实现
    public static void morrisPos(Node head) {
        if (head == null) return;
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                    printEdge(cur.left);
                }
            }
            cur = cur.right;
        }
        printEdge(head);
        System.out.println();
    }

    private static void printEdge(Node head) {
        Node tail = reverseEdge(head);
        Node cur = tail;
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur=cur.right;
        }
        reverseEdge(tail);
    }

    private static Node reverseEdge(Node now) {
        Node pre=null;
        Node next=null;
        while (now!=null){
            next=now.right;
            now.right=pre;
            pre=now;
            now=next;
        }
        return pre;
    }

    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(2);
        head.right = new Node(6);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right.left = new Node(5);
        head.right.right = new Node(7);
        morris(head);//morris序 有左孩子的节点会遍历两边，没有左孩子的节点遍历一边
        morrisPre(head);
        morrisIn(head);
        morrisPos(head);
    }

}
