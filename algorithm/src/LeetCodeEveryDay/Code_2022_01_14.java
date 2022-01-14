package LeetCodeEveryDay;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Code_2022_01_14 {
    //题目链接:https://leetcode-cn.com/problems/find-k-pairs-with-smallest-sums/
    //两个数组都是升序排列
    //醍醐灌顶的四字:"多路合并"--联想合并K个升序链表
    //时间复杂度O(klogk)
    //空间复杂度O(k)
    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        PriorityQueue<int[]> p = new PriorityQueue<>((o1, o2) -> nums1[o1[0]] + nums2[o1[1]] - nums1[o2[0]] - nums2[o2[1]]);
        int n1 = nums1.length, n2 = nums2.length;
        //这里Math.min(n1,k)的原因
        //正常来说我们会形成n1路
        //但是如果说k<n1的话，只需要形成k路即可
        //因为K+1路的最小值都比前面的K路的开头数大，也就不会在前K小的范围中
        for (int i = 0; i < Math.min(n1, k); i++) {
            p.add(new int[]{i, 0});
        }
        while (k-- > 0 && !p.isEmpty()) {
            int[] poll = p.poll();
            List<Integer> list = new ArrayList<>();
            list.add(nums1[poll[0]]);
            list.add(nums2[poll[1]]);
            ans.add(list);
            if (poll[1] + 1 < n2) {
                p.add(new int[]{poll[0], poll[1] + 1});
            }
        }
        return ans;
    }
}
