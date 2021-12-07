package LeetCodeContest.Code_270_2021_12_05;

import java.util.List;

public class Main2 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {

    }

    public ListNode deleteMiddle(ListNode head) {
        ListNode node=head;
        int len=1;
        while(node!=null){
            node=node.next;
            len++;
        }
        len/=2;
        if(len==0) return null;
        ListNode cur=head;
        while(len>1){
            cur=cur.next;
            len--;
        }
        cur.next=cur.next.next;
        return head;
    }
}
