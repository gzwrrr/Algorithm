package 二叉树.二叉树;

import java.util.LinkedList;
import java.util.Queue;

/**
 * leetcode 112
 * 路径总和
 *
 * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。如果存在，返回 true ；否则，返回 false 。
 *
 * 求出路径总和
 * 深度优先遍历
 * @author gzw
 */
public class HasPathSum {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.val == targetSum && root.left == null && root.right == null) {
            return true;
        }
        boolean left = hasPathSum(root.left,  targetSum - root.val);
        boolean right = hasPathSum(root.right, targetSum - root.val);
        return left || right;
    }
}

/**
 * 广度优先遍历解法
 */
class HasPathSum01 {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
        Queue<Integer> sumQueue = new LinkedList<Integer>();
        nodeQueue.add(root);
        sumQueue.add(root.val);
        while (!nodeQueue.isEmpty()) {
            int size = nodeQueue.size();
            for (int i = 0; i < size; i++) {
                root = nodeQueue.poll();
                int preSum = sumQueue.poll();
                if (root.left == null && root.right == null) {
                    if (preSum == targetSum) {
                        return true;
                    }
                    continue;
                }
                if (root.left != null) {
                    nodeQueue.add(root.left);
                    sumQueue.add(root.left.val + preSum);
                }
                if (root.right != null) {
                    nodeQueue.add(root.right);
                    sumQueue.add(root.right.val + preSum);
                }
            }
        }
        return false;
    }
}