package 二叉树.二叉树;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * leetcode 236
 * 二叉树的最近公共祖先
 *
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * 迭代解法
 * 时间复杂度：O(N)
 * 空间复杂度：O(N)
 * 但是常数时间较大，即效率较低
 */
public class LowestCommonAncestor {
    private Map<Integer, TreeNode> parent = new HashMap<Integer, TreeNode>();
    private Set<Integer> visited = new HashSet<Integer>();

    public void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            parent.put(root.left.val, root);
        }
        if (root.right != null) {
            parent.put(root.right.val, root);
        }
        dfs(root.left);
        dfs(root.right);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        dfs(root);
        while (p != null) {
            visited.add(p.val);
            p = parent.get(p.val);
        }
        while (q != null) {
            if (visited.contains(q.val)) {
                return q;
            } else {
                q = parent.get(q.val);
            }
        }
        return null;
    }
}

/**
 * 递归解法 1
 * 时间复杂度：O(N)
 * 空间复杂度：O(N)
 */
class LowestCommonAncestor01 {
    private TreeNode res;

    public LowestCommonAncestor01() {
        this.res = null;
    }


    public boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return false;
        }
        boolean lson = dfs(root.left, p, q);
        boolean rson = dfs(root.right, p, q);
        if ((lson && rson) || (((root.val == p.val) || (root.val == q.val)) && (lson || rson))) {
            res = root;
        }
        return lson || rson || root.val == p.val || root.val == q.val;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return res;
    }
}

/**
 * 递归解法 2
 * 时间复杂度：O(N)
 * 空间复杂度：O(N)
 */
class LowestCommonAncestor02 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == q || root == p || root == null) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        if (left == null) {
            return right;
        }
        return left;
    }
}