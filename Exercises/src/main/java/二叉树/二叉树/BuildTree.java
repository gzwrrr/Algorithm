package 二叉树.二叉树;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * leetcode 106
 * 从中序与后序遍历序列构造二叉树
 *
 * 给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历， postorder 是同一棵树的后序遍历，请你构造并返回这颗 二叉树 。
 *
 * 从中序与后序遍历序列构造二叉树
 * 递归解法
 * @author gzw
 */
public class BuildTree {
    private int postIndex;
    private int[] inorder;
    private int[] postorder;
    private HashMap<Integer, Integer> indexMap = new HashMap<Integer, Integer>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length == 0 || postorder.length == 0) {
            return null;
        }
        this.inorder = inorder;
        this.postorder = postorder;

        this.postIndex = postorder.length - 1;
        for (int i = 0; i < inorder.length; i++) {
            this.indexMap.put(inorder[i], i);
        }

        return build(0, inorder.length - 1);
    }

    public TreeNode build(int leftIndex, int rightIndex) {
        // 说明已经没有需要构造的节点了
        if (leftIndex > rightIndex) {
            return null;
        }

        int rootNum = postorder[postIndex];
        TreeNode root = new TreeNode(rootNum);
        postIndex--;

        int gapNum = indexMap.get(rootNum);

        root.right = build(gapNum + 1, rightIndex);
        root.left = build(leftIndex, gapNum - 1);

        return root;
    }
}

/**
 * 从中序与后序遍历序列构造二叉树
 * 迭代解法
 * @author gzw
 */
class BuildTree01 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }
        Stack<TreeNode> roots = new Stack<TreeNode>();
        int pre = 0;
        int in = 0;
        //先序遍历第一个值作为根节点
        TreeNode curRoot = new TreeNode(preorder[pre]);
        TreeNode root = curRoot;
        roots.push(curRoot);
        pre++;
        //遍历前序遍历的数组
        while (pre < preorder.length) {
            //出现了当前节点的值和中序遍历数组的值相等，寻找是谁的右子树
            if (curRoot.val == inorder[in]) {
                //每次进行出栈，实现倒着遍历
                while (!roots.isEmpty() && roots.peek().val == inorder[in]) {
                    curRoot = roots.peek();
                    roots.pop();
                    in++;
                }
                //设为当前的右孩子
                curRoot.right = new TreeNode(preorder[pre]);
                //更新 curRoot
                curRoot = curRoot.right;

            } else {
                //否则的话就一直作为左子树
                curRoot.left = new TreeNode(preorder[pre]);
                curRoot = curRoot.left;
            }
            roots.push(curRoot);
            pre++;
        }
        return root;
    }
}

/**
 *
 * leetcode 105
 * 从前序与中序遍历序列构造二叉树
 *
 * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 *
 * 从前序与中序遍历序列构造二叉树
 * 时间复杂度：O(N)
 * 空间复杂度：O(N)
 *
 * @author gzw
 */
class PreAndInBuild {
    private int preIndex;
    private int[] preorder;
    private int[] inorder;
    private Map<Integer, Integer> indexMap = new HashMap<Integer, Integer>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0) {
            return null;
        }
        this.preorder = preorder;
        this.inorder = inorder;
        this.preIndex = 0;

        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }

        return build(0, inorder.length - 1);
    }

    private TreeNode build(int leftIndex, int rightIndex) {
        if (leftIndex > rightIndex) {
            return null;
        }

        int rootVal = preorder[preIndex];
        TreeNode root = new TreeNode(rootVal);

        preIndex++;
        int gapNum = indexMap.get(rootVal);

        root.left = build(leftIndex, gapNum - 1);
        root.right = build(gapNum + 1, rightIndex);
        return root;
    }
}
