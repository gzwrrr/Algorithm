package 二叉树.二叉树;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * leetcode 104
 * 二叉树的最大深度
 *
 * 二叉树的最大深度
 * 深度优先遍历
 * 自定向下
 *
 * 时间复杂度：O(N)
 * 空间复杂度：O(height)
 *
 * @author gzw
 */
public class MaxDepth {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return maxDepth(root, 0);
    }

    public int maxDepth(TreeNode root, int deep) {
        if (root == null) {
            return deep;
        }
        int left = maxDepth(root.left, deep + 1);
        int right = maxDepth(root.right, deep + 1);
        return Math.max(left, right);
    }
}

/**
 * 深度优先遍历
 * 自底向上
 *
 * 时间复杂度：O(N)
 * 空间复杂度：O(height)
 */
class MaxDepth01 {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }
}


/**
 * 广度优先遍历
 * 时间复杂度：O(N)
 * 空间复杂度：O(N)
 */
class MaxDepth02 {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        int res = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                root = queue.poll();
                if (root.left != null) {
                    queue.add(root.left);
                }
                if (root.right != null) {
                    queue.add(root.right);
                }
            }
            res++;
        }
        return res;
    }
}
