package 二叉树.搜索树;

import javax.print.attribute.standard.NumberUp;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * leetcode：703
 *
 * 设计一个找到数据流中第 k 大元素的类（class）。注意是排序后的第 k 大元素，不是第 k 个不同的元素。
 * 请实现 KthLargest 类：
 * KthLargest(int k, int[] nums) 使用整数 k 和整数流 nums 初始化对象。
 * int add(int val) 将 val 插入数据流 nums 后，返回当前数据流中第 k 大的元素。
 *
 * 使用二叉搜索树解
 * 尚未解出
 * @author gzw
 */
public class KthLargest {

    class TreeNode {
        int val;
        int cnt;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) {
            this.cnt = 1;
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.cnt = 1;
            this.val = val;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < cnt; i++) {
                builder.append("\t\t\t");
            }
            builder.append("TreeNode{" + "val=").append(val).append(", cnt=").append(cnt).append('}');
            builder.append("--|");
            return builder.toString();
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 1, 3, 9, 7, 0};
        new KthLargest(1, nums);
    }

    private TreeNode root;
    private int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        for (int i = 0; i < nums.length; i++) {
            root = insert(root, nums[i]);
        }
    }

    public int add(int val) {
        return 0;
    }

    public TreeNode insert(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (val < root.val) {
            root.cnt += 1;
            root.left = insert(root.left, val);
        } else {
            root.cnt += 1;
            root.right = insert(root.right, val);
        }
        return root;
    }

    public void inorderPrint(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        while (!stack.isEmpty() || root != null){
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < stack.size(); i++) {
                    builder.append("\t\t");
                }
                builder.append('[').append(root.val).append(']');
                System.out.println(builder);
                root = root.right;
            }
        }
    }
}

/**
 * 使用优先级队列实现
 * 时间复杂度：
 * - 插入的时间复杂度：O(logK)
 * - 初始化的时间复杂度：O(N * logK)
 * 空间复杂度：O(K)
 */
class KthLargest01 {
    private PriorityQueue<Integer> pq;
    private int k;

    public KthLargest01(int k, int[] nums) {
        this.k = k;
        this.pq = new PriorityQueue<Integer>();
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        pq.offer(val);
        if (pq.size() > k) {
            pq.poll();
        }
        return pq.peek();
    }
}

/**
 * 二叉树实现，性能较差
 * （还没想出来，是别人的解。。。）
 *
 * 每次查询为O(h)，延迟更新，每个结点记录值和自身为第几大结点
 * 当向一棵树种插入值时，如果向右走，那么自身及左子树的count应该全部+1，用mark标记左、右子树延迟增加的值
 */
class KthLargest02 {
    static class TreeNode {
        // 当前结点存储的值
        int val;
        // 第几大结点
        int count;
        // 左、右子树延迟增加值
        int left_mark, right_mark;
        TreeNode left, right;
        public TreeNode(int val, int count) {
            this.val = val;
            this.count = count;
        }
    }
    private TreeNode root;
    private int k;
    // yours表示当node为空树时count应该赋值为多少
    private TreeNode insert(TreeNode node, int val, int yours) {
        if(node == null) return new TreeNode(val, yours);
        else if(val <= node.val) {
            pushDown(node);
            node.left = insert(node.left, val, node.count + 1);
        }
        else {
            // 表示左子树需要全体加1
            node.count++;
            node.left_mark += 1;
            pushDown(node);
            node.right = insert(node.right, val, node.count - 1);
        }
        return node;
    }
    private int search(TreeNode node, int k) {
        if(node == null)
            return Integer.MIN_VALUE;
        else if(k == node.count)
            return node.val;
        else if(k > node.count) {
            pushDown(node);
            return search(node.left, k);
        }
        else {
            pushDown(node);
            return search(node.right, k);
        }
    }
    private void pushDown(TreeNode node) {
        if(node.left != null) {
            node.left.left_mark += node.left_mark;
            node.left.right_mark += node.left_mark;
            node.left.count += node.left_mark;
        }
        if(node.right != null) {
            node.right.left_mark += node.right_mark;
            node.right.right_mark += node.right_mark;
            node.right.count += node.right_mark;
        }
        node.left_mark = node.right_mark = 0;
    }
    public KthLargest02(int k, int[] nums) {
        root = null;
        for(int num : nums) {
            root = insert(root, num, 1);
        }
        this.k = k;
    }
    public int add(int val) {
        root = insert(root, val, 1);
        return search(root, k);
    }
}

