package 二叉树.二叉树;

/**
 * Morris 二叉树遍历
 * Morris的整体思路就是将 以某个根结点开始，找到它左子树的最右侧节点之后与这个根结点进行连接
 *
 * 整体较为复杂
 * MorrisTraversal 是模板，在不同位置操作可以得到不同顺序的遍历
 * @author gzw
 */
public class MorrisTraversal {
    public void morris(TreeNode root) {
        if (root == null) {
            return;
        }
        // 一开始指向根节点
        TreeNode cur1 = root;
        TreeNode cur2 = null;
        while(cur1 != null) {
            // 找到节点的左子树，cur2 指向该子树
            cur2 = cur1.left;
            // 如果左子树不为空，就找到其最右节点，将最右节点与 cur 指向的根节点相连
            if (cur2 != null) {
                // 找到最右节点，如果没有进循环说明直接就是叶子节点
                while (cur2.right != null && cur2.right != cur1) {
                    cur2 = cur2.right;
                }
                // 将找到的最右节点（叶子节点），与 cur1 指向的节点相连，并且将 cur1 下移一次以便遍历所有节点
                if (cur2.right == null) {
                    cur2.right = cur1;
                    cur1 = cur1.left;
                    continue;
                } else {
                    // 进入这里说明已经做过了连接操作，即已经 visit 过一次，此时就可以断开连接
                    cur2.right = null;
                }
            }
            // 走到这里说明左子树为空或者已经 visit 过一次，可以往右走
            cur1 = cur1.right;
        }
    }
}

/**
 * 先序遍历
 */
class PreOrder {
    public static void preOrderMorris(TreeNode head) {
        if (head == null) {
            return;
        }
        TreeNode cur1 = head;
        TreeNode cur2 = null;
        while (cur1 != null) {
            cur2 = cur1.left;
            if (cur2 != null) {
                while (cur2.right != null && cur2.right != cur1) {
                    cur2 = cur2.right;
                }
                if (cur2.right == null) {
                    cur2.right = cur1;
                    System.out.print(cur1.val + " ");
                    cur1 = cur1.left;
                    continue;
                } else {
                    cur2.right = null;
                }
            } else {
                System.out.print(cur1.val + " ");
            }
            cur1 = cur1.right;
        }
    }
}

/**
 * 中序遍历
 */
class InOrder {
    public static void inOrderMorris(TreeNode head) {
        if (head == null) {
            return;
        }
        TreeNode cur1 = head;
        TreeNode cur2 = null;
        while (cur1 != null) {
            cur2 = cur1.left;
            //构建连接线
            if (cur2 != null) {
                while (cur2.right != null && cur2.right != cur1) {
                    cur2 = cur2.right;
                }
                if (cur2.right == null) {
                    cur2.right = cur1;
                    cur1 = cur1.left;
                    continue;
                } else {
                    cur2.right = null;
                }
            }
            System.out.print(cur1.val + " ");
            cur1 = cur1.right;
        }
    }
}

/**
 * 后序遍历
 */
class PostOrder {
    //后序Morris
    public static void postOrderMorris(TreeNode head) {
        if (head == null) {
            return;
        }
        TreeNode cur1 = head;//遍历树的指针变量
        TreeNode cur2 = null;//当前子树的最右节点
        while (cur1 != null) {
            cur2 = cur1.left;
            if (cur2 != null) {
                while (cur2.right != null && cur2.right != cur1) {
                    cur2 = cur2.right;
                }
                if (cur2.right == null) {
                    cur2.right = cur1;
                    cur1 = cur1.left;
                    continue;
                } else {
                    cur2.right = null;
                    postMorrisPrint(cur1.left);
                }
            }
            cur1 = cur1.right;
        }
        postMorrisPrint(head);
    }
    //打印函数
    public static void postMorrisPrint(TreeNode head) {
        TreeNode reverseList = postMorrisReverseList(head);
        TreeNode cur = reverseList;
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.right;
        }
        postMorrisReverseList(reverseList);
    }
    //翻转单链表
    public static TreeNode postMorrisReverseList(TreeNode head) {
        TreeNode cur = head;
        TreeNode pre = null;
        while (cur != null) {
            TreeNode next = cur.right;
            cur.right = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}