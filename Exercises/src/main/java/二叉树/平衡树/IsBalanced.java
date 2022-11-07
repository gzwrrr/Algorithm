package 二叉树.平衡树;

/**
 *
 * leetcode 平衡二叉树
 *
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * 本题中，一棵高度平衡二叉树定义为：
 * 一个二叉树每个节点 的左右两个子树高度差的绝对值不超过 1 。
 *
 * 使用递归套路判断是否平衡
 * 创建对象开销较大但是比较容易实现
 *
 * 时间复杂度：O(N)
 * 空间复杂度：O(N)
 * @author gzw
 */
public class IsBalanced {
    public boolean isBalanced(TreeNode root) {
        return process(root).isBalanced;
    }

    class ReturnType {
        boolean isBalanced;
        int height;

        public ReturnType(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    public ReturnType process(TreeNode root) {
        if (root == null) {
            return new ReturnType(true, 0);
        }
        ReturnType left = process(root.left);
        ReturnType right = process(root.right);
        boolean isBalanced = left.isBalanced && right.isBalanced && Math.abs(left.height - right.height) < 2;
        int height = Math.max(left.height, right.height) + 1;
        return new ReturnType(isBalanced, height);
    }
}


/**
 * 递归求解
 * 通过求出节点深度来判断平衡
 * 自顶向下
 * 效率不高
 * 时间复杂度：O(N^2)
 * 空间复杂度：O(N)
 */
class IsBalanced01 {
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return Math.abs(deep(root.left) - deep(root.right)) < 2 && isBalanced(root.left) && isBalanced(root.right);
    }

    public int deep(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(deep(node.left), deep(node.right)) + 1;
    }
}

/**
 * 递归求解
 * 通过求出节点深度来判断平衡
 * 自定向上
 * 效率比自顶向下高
 * 时间复杂度：O(N)
 * 空间复杂度：O(N)
 */
class IsBalanced02 {
    public boolean isBalanced(TreeNode root) {
        return process(root) >= 0;
    }

    /**
     * 平衡就返回高度，不平衡就返回 -1
     * 只要有一棵子树不平衡那么就一定不平衡，即遇到一个 -1 就不断返回 -1
     * @param root
     * @return
     */
    public int process(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = process(root.left);
        int right = process(root.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }
}