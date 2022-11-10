package 二叉树.二叉树;

import java.util.HashMap;

/**
 *
 * 从中序与后序遍历序列构造二叉树
 *
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
