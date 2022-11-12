package 二叉树.二叉树;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * leetcode 297
 * 二叉树的序列化与反序列化
 *
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 *
 * 二叉树的序列化与反序列化
 * 递归解法
 *
 * 时间复杂度：O(N)
 * 空间复杂度：O(N)
 * @author gzw
 */
public class Codec {
    private StringBuilder builder;

    public Codec() {
        builder = new StringBuilder();
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            builder.append("null,");
            return builder.toString();
        }
        builder.append(root.val).append(",");
        serialize(root.left);
        serialize(root.right);
        return builder.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] dataArray = data.split(",");
        List<String> dataList = new LinkedList<String>(Arrays.asList(dataArray));
        return inorder(dataList);
    }

    public TreeNode inorder(List<String> dataList) {
        if ("null".equals(dataList.get(0))) {
            dataList.remove(0);
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(dataList.get(0)));
        dataList.remove(0);
        root.left = inorder(dataList);
        root.right = inorder(dataList);
        return root;
    }
}
