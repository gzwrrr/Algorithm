package 动态规划;

import java.util.Arrays;

/**
 * leetcode 931
 * 下降路径最小和
 *
 * 给你一个 n x n 的 方形 整数数组 matrix ，请你找出并返回通过 matrix 的下降路径 的 最小和 。
 * 下降路径 可以从第一行中的任何元素开始，并从每一行中选择一个元素。在下一行选择的元素和当前行所选元素最多相隔一列（即位于正下方或者沿对角线向左或者向右的第一个元素）。具体来说，位置 (row, col) 的下一个元素应当是 (row + 1, col - 1)、(row + 1, col) 或者 (row + 1, col + 1) 。
 *
 * 递归解法
 * @author gzw
 */
public class MinFallingPathSum {

    int MAX = 10001;
    int[][] memo;

    public int minFallingPathSum(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        memo = new int[m][n];

        for (int i = 0; i < m; i++) {
            Arrays.fill(memo[i], MAX);
        }

        int res = MAX;
        for (int i = 0; i < n; i++) {
            res = Math.min(res, dp(matrix, m - 1, i));
        }

        return res;
    }

    private int dp(int[][] matrix, int i, int j) {
        if (i < 0 || j < 0 || i > matrix.length - 1 || j > matrix[0].length - 1) {
            return MAX;
        }
        if (i == 0) {
            return matrix[0][j];
        }
        if (memo[i][j] != MAX) {
            return memo[i][j];
        }
        memo[i][j] = Math.min(dp(matrix, i - 1, j), Math.min(dp(matrix, i - 1, j - 1), dp(matrix, i - 1, j + 1))) + matrix[i][j];
        return memo[i][j];
    }
}


/**
 * 迭代解法
 */
class MinFallingPathSum01 {
    public int minFallingPathSum(int[][] matrix) {
        int MAX = 10001;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];

        for (int i = 0; i < n; i++) {
            dp[0][i] = matrix[0][i];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int left = MAX, right = MAX;
                if (j > 0) {
                    left = dp[i - 1][j - 1];
                }
                if (j < n - 1) {
                    right = dp[i - 1][j + 1];
                }
                dp[i][j] = matrix[i][j] + Math.min(dp[i - 1][j], Math.min(left, right));
            }
        }

        int res = MAX;
        for (int i = 0; i < n; i++) {
            res = Math.min(res, dp[m - 1][i]);
        }
        return res;
    }
}