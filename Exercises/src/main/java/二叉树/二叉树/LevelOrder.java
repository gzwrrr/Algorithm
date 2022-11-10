package 二叉树.二叉树;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * leetcode 102
 * 二叉树的层序遍历
 *
 * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）
 *
 * BFS 广度优先遍历
 *
 * @author gzw
 */
public class LevelOrder {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<List<Integer>>();
        }
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);
        while (!queue.isEmpty()) {
            int levelNum = queue.size();
            List<Integer> subList = new ArrayList<Integer>();
            for (int i = 0; i < levelNum; i++) {
                TreeNode cur = queue.poll();
                subList.add(cur.val);
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
            res.add(subList);
        }
        return res;
    }
}


/**
 * 虽然层序遍历正常是用广度优先遍历，但是这里也可以使用 DFS 深度优先遍历
 */
class LevelOrder01 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        leverHelper(res, root, 0);
        return res;
    }

    private void leverHelper(List<List<Integer>> res, TreeNode root, int level) {
        if (root == null) {
            return;
        }
        if (level >= res.size()) {
            res.add(new ArrayList<Integer>());
        }
        res.get(level).add(root.val);
        leverHelper(res, root.left, level + 1);
        leverHelper(res, root.right, level + 1);
    }
}