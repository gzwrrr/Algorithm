package 二叉树.二叉树;

/**
 * 二叉树节点
 *
 * 每个节点中的值必须大于（或等于）存储在其左侧子树中的任何值。
 * 每个节点中的值必须小于（或等于）存储在其右子树中的任何值。
 *
 * @author gzw
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
