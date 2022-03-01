package ClassicAlgorithm;

public class SegmentTree {
    //线段树
    public static class SegmentTreeNode {
        // arr[]为原序列的信息从0开始，但在arr里是从1开始的
        // sum[]模拟线段树维护区间和
        // lazy[]为累加和懒惰标记
        // change[]为更新的值
        // update[]为更新慵懒标记
        public int[] arr;
        public int[] sum;
        public int[] lazy;
        public int[] change;
        public boolean[] update;

        public SegmentTreeNode(int[] origin) {
            arr = new int[origin.length + 1];
            for (int i = 0; i < origin.length; i++) {
                arr[i + 1] = origin[i];
            }

            sum = new int[arr.length << 2];// 用来支持脑补概念中，某一个范围的累加和信息
            lazy = new int[arr.length << 2];// 用来支持脑补概念中，某一个范围沒有往下发的累加任务
            change = new int[arr.length << 2];// 用来支持脑补概念中，某一个范围更新任务，更新成了什么
            update = new boolean[arr.length << 2];// 用来支持脑补概念中，某一个范围有没有更新操作的任务
        }

        // 在初始化阶段，先把sum数组，填好
        // 在arr[l~r]范围上，去build，1~N，
        // rt : 这个范围在sum中的下标
        //这里时间复杂度是O(n)
        public void build(int l, int r, int rt) {
            if (l == r) {
                sum[rt] = arr[l];
                return;
            }
            int mid = l + ((r - l) >> 1);
            build(l, mid, rt << 1);
            build(mid + 1, r, rt << 1 | 1);
            pushUp(rt);
        }

        //任务:L-R范围数统一加个C
        //接收者:rt位置代表的l-r范围上的累加和
        public void add(int L, int R, int C, int l, int r, int rt) {
            //任务把该范围全包了 不进行下发
            if (L <= l && r <= R) {
                lazy[rt] += C;
                sum[rt] += C * (r - l + 1);
                return;
            }
            //任务没有包住 先把之前的懒信息下发一层
            int mid = l + ((r - l) >> 1);
            pushDown(rt, mid - l + 1, r - mid);
            //下发完毕 处理该任务
            if (L <= mid) {
                add(L, R, C, l, mid, rt << 1);
            }
            if (R > mid) {
                add(L, R, C, mid + 1, r, rt << 1 | 1);
            }
            pushUp(rt);
        }

        public void update(int L, int R, int C, int l, int r, int rt) {
            //任务把该范围全包了 不进行下发
            if (L <= l && r <= R) {
                change[rt] = C;
                update[rt] = true;
                sum[rt] = C * (r - l + 1);
                lazy[rt] = 0;
                return;
            }
            //任务没有包住 先把之前的懒信息下发一层
            int mid = l + ((r - l) >> 1);
            pushDown(rt, mid - l + 1, r - mid);
            //下发完毕 处理该任务
            if (L <= mid) {
                update(L, R, C, l, mid, rt << 1);
            }
            if (R > mid) {
                update(L, R, C, mid + 1, r, rt << 1 | 1);
            }
            pushUp(rt);
        }

        public long query(int L, int R, int l, int r, int rt) {
            if (L <= l && r <= R) {
                return sum[rt];
            }
            long ans = 0;
            int mid = l + ((r - l) >> 1);
            pushDown(rt, mid - l + 1, r - mid);
            if (L <= mid) {
                ans += query(L, R, l, mid, rt << 1);
            }
            if (R > mid) {
                ans += query(L, R, mid + 1, r, rt << 1 | 1);
            }
            return ans;
        }

        public void pushUp(int rt) {
            sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
        }

        public void pushDown(int rt, int ln, int rn) {
            //这里必须update信息首先处理
            //因为当update和add共存时
            //只可能update在前，add在后(update会清空add的懒信息)
            if (update[rt]) {
                update[rt << 1] = true;
                update[rt << 1 | 1] = true;
                //懒信息下发到左孩子
                change[rt << 1] = change[rt];
                //懒信息下发到右孩子
                change[rt << 1 | 1] = change[rt];
                //左孩子累加懒信息作废清空
                lazy[rt << 1] = 0;
                //右孩子累加懒信息作废清空
                lazy[rt << 1 | 1] = 0;
                //左孩子累加和信息更新
                sum[rt << 1] = change[rt] * ln;
                //右孩子累加和信息更新
                sum[rt << 1 | 1] = change[rt] * rn;
                update[rt] = false;
            }

            //将懒信息下发
            if (lazy[rt] != 0) {
                //下发到左孩子
                lazy[rt << 1] += lazy[rt];
                //左孩子的累加和相应改变
                sum[rt << 1] += lazy[rt] * ln;
                //下发到右孩子
                lazy[rt << 1 | 1] += lazy[rt];
                //左孩子的累加和相应改变
                sum[rt << 1 | 1] += lazy[rt] * rn;
                lazy[rt] = 0;
            }
        }
    }

    //暴力遍历
    public static class Node {
        public int[] arr;

        public Node(int[] origin) {
            arr = new int[origin.length + 1];
            for (int i = 0; i < origin.length; i++) {
                arr[i + 1] = origin[i];
            }
        }

        public void update(int L, int R, int C) {
            for (int i = L; i <= R; i++) {
                arr[i] = C;
            }
        }

        public void add(int L, int R, int C) {
            for (int i = L; i <= R; i++) {
                arr[i] += C;
            }
        }

        public long query(int L, int R) {
            long ans = 0;
            for (int i = L; i <= R; i++) {
                ans += arr[i];
            }
            return ans;
        }

    }

    public static int[] genarateRandomArray(int len, int max) {
        int size = (int) (Math.random() * len) + 1;
        int[] origin = new int[size];
        for (int i = 0; i < size; i++) {
            origin[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        return origin;
    }

    public static boolean test() {
        int len = 100;
        int max = 1000;
        int testTimes = 5000;
        int addOrUpdateTimes = 1000;
        int queryTimes = 500;
        for (int i = 0; i < testTimes; i++) {
            int[] origin = genarateRandomArray(len, max);
            SegmentTreeNode seg = new SegmentTreeNode(origin);
            int S = 1;
            int N = origin.length;
            int root = 1;
            seg.build(S, N, root);
            Node rig = new Node(origin);
            for (int j = 0; j < addOrUpdateTimes; j++) {
                int num1 = (int) (Math.random() * N) + 1;
                int num2 = (int) (Math.random() * N) + 1;
                int L = Math.min(num1, num2);
                int R = Math.max(num1, num2);
                int C = (int) (Math.random() * max) - (int) (Math.random() * max);
                if (Math.random() < 0.5) {
                    seg.add(L, R, C, S, N, root);
                    rig.add(L, R, C);
                } else {
                    seg.update(L, R, C, S, N, root);
                    rig.update(L, R, C);
                }
            }
            for (int k = 0; k < queryTimes; k++) {
                int num1 = (int) (Math.random() * N) + 1;
                int num2 = (int) (Math.random() * N) + 1;
                int L = Math.min(num1, num2);
                int R = Math.max(num1, num2);
                long ans1 = seg.query(L, R, S, N, root);
                long ans2 = rig.query(L, R);
                if (ans1 != ans2) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] origin = {2, 1, 1, 2, 3, 4, 5};
        SegmentTreeNode seg = new SegmentTreeNode(origin);
        int S = 1; // 整个区间的开始位置，规定从1开始，不从0开始 -> 固定
        int N = origin.length; // 整个区间的结束位置，规定能到N，不是N-1 -> 固定
        int root = 1; // 整棵树的头节点位置，规定是1，不是0 -> 固定
        int L = 2; // 操作区间的开始位置 -> 可变
        int R = 5; // 操作区间的结束位置 -> 可变
        int C = 4; // 要加的数字或者要更新的数字 -> 可变
        // 区间生成，必须在[S,N]整个范围上build
        seg.build(S, N, root);
        // 区间修改，可以改变L、R和C的值，其他值不可改变
        seg.add(L, R, C, S, N, root);
        // 区间更新，可以改变L、R和C的值，其他值不可改变
        seg.update(L, R, C, S, N, root);
        // 区间查询，可以改变L和R的值，其他值不可改变
        long sum = seg.query(L, R, S, N, root);
        System.out.println(sum);
        System.out.println("对数器测试开始...");
        System.out.println("测试结果 : " + (test() ? "通过" : "未通过"));

    }


}
