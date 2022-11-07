package 二叉树.搜索树;

import java.util.*;

/**
 * leetcode 173
 *
 * 二叉搜索树迭代器：
 * 实现一个二叉搜索树迭代器类BSTIterator ，表示一个按中序遍历二叉搜索树（BST）的迭代器：
 * BSTIterator(TreeNode root) 初始化 BSTIterator 类的一个对象。BST 的根节点 root 会作为构造函数的一部分给出。指针应初始化为一个不存在于 BST 中的数字，且该数字小于 BST 中的任何元素。
 * boolean hasNext() 如果向指针右侧遍历存在数字，则返回 true ；否则返回 false 。
 * int next()将指针向右移动，然后返回指针处的数字。
 * 注意，指针初始化为一个不存在于 BST 中的数字，所以对 next() 的首次调用将返回 BST 中的最小元素。
 * 你可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 的中序遍历中至少存在一个下一个数字。
 *
 * @author gzw
 */

/**
 * 先遍历所有节点，将节点中的值存储到数组中
 * 效率较低
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 */
class BSTIterator {

    private int index;

    private List<Integer> arr;

    private void inorderTraversal(TreeNode root, List<Integer> arr) {
        if (root == null) {
            return;
        }
        inorderTraversal(root.left, arr);
        arr.add(root.val);
        inorderTraversal(root.right, arr);
    }


    public BSTIterator(TreeNode root) {
        this.index = 0;
        this.arr = new ArrayList<Integer>();
        inorderTraversal(root, arr);
    }

    public int next() {
        return arr.get(index++);
    }

    public boolean hasNext() {
        return index < arr.size();
    }
}


/**
 * 使用栈，每一次获取再存储
 * 效率较低
 * 时间复杂度：O(n)，平均下来 O(1)
 * 空间复杂度：O(n)
 */
class BSTIterator01 {

    private TreeNode cur;

    private Deque<TreeNode> stack;

    public BSTIterator01(TreeNode root) {
        cur = root;
        this.stack = new LinkedList<TreeNode>();
    }

    public int next() {
        while (cur != null) {
            stack.push(cur);
            this.cur = cur.left;
        }
        cur = stack.pop();
        int res = cur.val;
        cur = cur.right;
        return res;
    }

    public boolean hasNext() {
        return cur != null || !stack.isEmpty();
    }
}