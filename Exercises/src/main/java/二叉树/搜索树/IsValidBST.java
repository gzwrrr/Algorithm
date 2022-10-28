package 二叉树.搜索树;

/**
 * leetcode 98
 * @author gzw
 */
public class IsValidBST {
//    /**
//     * 递归解法
//     * @param root
//     * @return
//     */
//    public boolean isValidBST(TreeNode root) {
//        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
//    }
//    public boolean isValidBST(TreeNode root, long lower, long higher) {
//        if (root == null) {
//            return true;
//        }
//        if (root.val <= lower || root.val >= higher) {
//            return false;
//        }
//        return isValidBST(root.left, lower, root.val) && isValidBST(root.right, root.val, higher);
//    }


//    /**
//     * 非递归中序遍历验证
//     * 性能较低
//     */
//    public boolean isValidBST(TreeNode root) {
//        Deque<TreeNode> stack = new LinkedList<TreeNode>();
//        double inorder = -Double.MAX_VALUE;
//        while (!stack.isEmpty() || root != null) {
//            while (root != null) {
//                stack.push(root);
//                root = root.left;
//            }
//            root = stack.pop();
//            if (root.val <= inorder) {
//                return false;
//            }
//            inorder = root.val;
//            root = root.right;
//        }
//        return true;
//    }



    /**
     * 递归版中序遍历验证
     * 性能较高
     */
    private long lower = Long.MIN_VALUE;
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean left = isValidBST(root.left);
        if (root.val <= lower) {
            return false;
        }
        lower = root.val;
        boolean right = isValidBST(root.right);
        return left && right;
    }


}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}