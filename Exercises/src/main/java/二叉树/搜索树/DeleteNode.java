package 二叉树.搜索树;

/**
 * leetcode 450
 * 删除二叉搜索树中的节点
 *
 * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
 * 一般来说，删除节点可分为两个步骤：
 * 首先找到需要删除的节点；如果找到了，删除它。
 *
 * 递归解法的思路：
 *
 * 二叉搜索树有以下性质：
 * 左子树的所有节点（如果有）的值均小于当前节点的值；
 * 右子树的所有节点（如果有）的值均大于当前节点的值；
 * 左子树和右子树均为二叉搜索树。
 * 二叉搜索树的题目往往可以用递归来解决。此题要求删除二叉树的节点，函数 deleteNode\textit{deleteNode}deleteNode 的输入是二叉树的根节点 root\textit{root}root 和一个整数 key\textit{key}key，输出是删除值为 key\textit{key}key 的节点后的二叉树，并保持二叉树的有序性。可以按照以下情况分类讨论：
 *
 * root\textit{root}root 为空，代表未搜索到值为 key\textit{key}key 的节点，返回空。
 * root.val>key\textit{root.val} > \textit{key}root.val>key，表示值为 key\textit{key}key 的节点可能存在于 root\textit{root}root 的左子树中，需要递归地在 root.left\textit{root.left}root.left 调用 deleteNode\textit{deleteNode}deleteNode，并返回 root\textit{root}root。
 * root.val<key\textit{root.val} < \textit{key}root.val<key，表示值为 key\textit{key}key 的节点可能存在于 root\textit{root}root 的右子树中，需要递归地在 root.right\textit{root.right}root.right 调用 deleteNode\textit{deleteNode}deleteNode，并返回 root\textit{root}root。
 * root.val=key\textit{root.val} = \textit{key}root.val=key，root\textit{root}root 即为要删除的节点。此时要做的是删除 root\textit{root}root，并将它的子树合并成一棵子树，保持有序性，并返回根节点。根据 root\textit{root}root 的子树情况分成以下情况讨论：
 * root\textit{root}root 为叶子节点，没有子树。此时可以直接将它删除，即返回空。
 * root\textit{root}root 只有左子树，没有右子树。此时可以将它的左子树作为新的子树，返回它的左子节点。
 * root\textit{root}root 只有右子树，没有左子树。此时可以将它的右子树作为新的子树，返回它的右子节点。
 * root\textit{root}root 有左右子树，这时可以将 root\textit{root}root 的后继节点（比 root\textit{root}root 大的最小节点，即它的右子树中的最小节点，记为 successor\textit{successor}successor）作为新的根节点替代 root\textit{root}root，并将 successor\textit{successor}successor 从 root\textit{root}root 的右子树中删除，使得在保持有序性的情况下合并左右子树。 简单证明，successor\textit{successor}successor 位于 root\textit{root}root 的右子树中，因此大于 root\textit{root}root 的所有左子节点；successor\textit{successor}successor 是 root\textit{root}root 的右子树中的最小节点，因此小于 root\textit{root}root 的右子树中的其他节点。以上两点保持了新子树的有序性。 在代码实现上，我们可以先寻找 successor\textit{successor}successor，再删除它。successor\textit{successor}successor 是 root\textit{root}root 的右子树中的最小节点，可以先找到 root\textit{root}root 的右子节点，再不停地往左子节点寻找，直到找到一个不存在左子节点的节点，这个节点即为 successor\textit{successor}successor。然后递归地在 root.right\textit{root.right}root.right 调用 deleteNode\textit{deleteNode}deleteNode 来删除 successor\textit{successor}successor。因为 successor\textit{successor}successor 没有左子节点，因此这一步递归调用不会再次步入这一种情况。然后将 successor\textit{successor}successor 更新为新的 root\textit{root}root 并返回。
 *
 * 时间复杂度：O(N)
 * 空间复杂度：O(N)
 *
 * @author gzw
 */
public class DeleteNode {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (key < root.val) {
            // 说明需要删除的节点在左树上，接收返回值是为了删除或覆盖
            // 返回 null 就说明左树节点就是要删除的节点，赋值为 null 相当于直接删除，覆盖也是相似的原理
            root.left = deleteNode(root.left, key);
            return root;
        }
        if (key > root.val) {
            // 同上
            root.right = deleteNode(root.right, key);
            return root;
        }
        if (key == root.val) {
            // 说明删除的是叶子节点，返回空就是直接删除了该节点
            if (root.left == null && root.right == null) {
                return null;
            }
            // 说明只有右节点，直接返回右节点，就会把要删除的节点覆盖，相当于删除
            if (root.left == null) {
                return root.right;
            }
            // 同上
            if (root.right == null) {
                return root.left;
            }
            // 能到这里说明要删除的节点左右节点，这也是最麻烦的情况
            // 一种做法是将右子树的最小节点覆盖到要删除的节点上，这样就能保证调整后还是搜索二叉树
            // 创建一个指针，因为之后需要移动位置找到最左的节点
            TreeNode help = root.right;
            // 找到最小的节点，即最左的节点
            while (help.left != null) {
                // 最后找到的一定是叶子节点
                help = help.left;
            }
            // 这里需要删除原本右树上的最小节点，就是当前 help 指针指向的节点，这步加上下面的两步等价于移动最小节点到要删除的节点上
            root.right = deleteNode(root.right, help.val);
            help.left = root.left;
            help.right = root.right;
            return help;
        }
        // 说明都不做说明不做任何调整，没有找到 key 对应的节点，直接返回就会保持原状
        return root;
    }

}

/**
 * 迭代解法
 */
class DeleteNode01 {
    public TreeNode deleteNode(TreeNode root, int key) {
        // 需要一个指针帮助找到需要删除的节点
        TreeNode cur = root;
        TreeNode curParent = null;
        while (cur != null && key != cur.val) {
            curParent = cur;
            if (key < cur.val) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        // 现在 cur 指向的就是需要删除的节点
        // 如果这个节点就是 null，说明找不到 key 对应的节点，直接返回 root 保持原状
        if (cur == null) {
            return root;
        }
        // 如果 cur 指向的是叶子节点，就直接赋值为 null，即直接删除
        if (cur.left == null && cur.right == null) {
            cur = null;
        } else if (cur.right == null) {
            // 如果只有左节点，直接将左节点覆盖到要删除的节点上
            cur = cur.left;
        } else if (cur.left == null) {
            // 同理
            cur = cur.right;
        } else {
            // 到这里说明左右节点都有
            // 创建两个指针，一个用于寻找右子树的最左节点，一个用于记录最左节点的父节点
            TreeNode help = cur.right, helpParent = cur;
            while (help.left != null) {
                helpParent = help;
                help = help.left;
            }
            // 下面的 if 成立说明没有进上面的 while，即需要删除的节点的右树没有左节点
            // 不是很懂...
            if (helpParent.val == cur.val) {
                helpParent.right = help.right;
            } else {
                // 说明找到了最左节点
                helpParent.left = help.right;
            }
            // 移动最左节点到需要删除的节点上
            help.left = cur.left;
            help.right = cur.right;
            cur = help;
        }
        // 下面的 if 成立说明需要删除的是一开始传入的根节点，此时直接把调整过后的 cur 返回就可以了
        if (curParent == null) {
            return cur;
        } else {
            // 不是很懂...
            if (curParent.left != null && curParent.left.val == key) {
                curParent.left = cur;
            } else {
                curParent.right = cur;
            }
            return root;
        }
    }

}