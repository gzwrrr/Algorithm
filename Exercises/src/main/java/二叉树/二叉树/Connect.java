package 二叉树.二叉树;

import javax.print.attribute.standard.NumberUp;
import java.util.LinkedList;
import java.util.Queue;


/**
 *
 * leetcode 116
 * 填充每个节点的下一个右侧节点指针
 *
 * 给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * 初始状态下，所有 next 指针都被设置为 NULL。
 *
 * 迭代解法，是否是满二叉树都能解
 * 时间复杂度：O(N)
 * 空间复杂度：O(N)
 * @author gzw
 */
public class Connect {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        Node cur = root;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                cur = queue.poll();
                if (i < size - 1) {
                    cur.next = queue.peek();
                }
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
        }
        return root;
    }
}

/**
 *
 * 递归解法进阶 1，只能解决满二叉树连接
 * 时间复杂度：O(N)
 * 空间复杂度：O(1)
 */
class Connect01 {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Node leftMost = root;
        while (leftMost.left != null) {
            Node head = leftMost;
            while (head != null) {
                head.left.next = head.right;

                if (head.next != null) {
                    head.right.next = head.next.left;
                }
                head = head.next;
            }
            leftMost = leftMost.left;
        }
        return root;
    }
}

/**
 * leetcode 117
 * 填充每个节点的下一个右侧节点指针 II
 *
 * 给定一个二叉树，填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * 初始状态下，所有 next 指针都被设置为 NULL。
 *
 * 递归解法进阶 2，是否是满二叉树都能解决
 * 主要思想就是用两个指针代替队列，并将每一层直接连接成链表
 * 时间复杂度：O(N)
 * 空间复杂度：O(1)
 */
class Connect02 {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Node cur = root;
        Node head = new Node();
        while (cur != null) {
            Node pre = head;
            while (cur != null) {
                if (cur.left != null) {
                    pre.next = cur.left;
                    pre = pre.next;
                }
                if (cur.right != null) {
                    pre.next = cur.right;
                    pre = pre.next;
                }
                cur = cur.next;
            }
            cur = head.next;
            head.next = null;
        }
        return root;
    }
}

/**
 *
 * 递归解法，只能解决满二叉树连接
 * 时间复杂度：O(N)
 * 空间复杂度：O(1)
 */
class Connect03 {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        if (root.left != null) {
            root.left.next = root.right;
            if (root.next != null) {
                root.right.next = root.next.left;
            }
        }
        connect(root.left);
        connect(root.right);
        return root;
    }
}

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}