package 二叉树.搜索树;

/**
 * leetcode 700
 * 从二叉搜索树中返回值对应的节点
 *
 * @author gzw
 */

/**
 * 时间复杂度：O(N)
 * 空间复杂度：O(N)
 */
public class SearchBST {
    public TreeNode searchBST(TreeNode root, int val) {
        if (root.val == val || root == null) {
            return root;
        }
        if (root.left != null && val < root.val) {
            return searchBST(root.left, val);
        }
        if (root.right != null && val > root.val) {
            return searchBST(root.right, val);
        }
        return null;
    }
}

class SearchBST01 {
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (val == root.val) {
            return root;
        }
        return searchBST(val < root.val ? root.left : root.right, val);
    }
}

/**
 * 迭代解法
 * 时间复杂度：O(N)
 * 空间复杂度：O(1)
 */
class SearchBST02 {
    public TreeNode searchBST(TreeNode root, int val) {
        while (root != null) {
            if (val == root.val) {
                return root;
            }
            root = val < root.val ? root.left : root.right;
        }
        return null;
    }
}