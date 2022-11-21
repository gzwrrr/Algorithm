package 动态规划;

import java.util.Arrays;

/**
 *
 * leetcode 64
 * 最小路径和
 *
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 *
 * 递归解法
 * 自顶向下
 * @author gzw
 */
public class MinPathSum {
    int[][] memo;

    public int minPathSum(int[][] grid) {
        int width = grid.length;
        int height = grid[0].length;
        memo = new int[width][height];
        for (int i = 0; i < width; i++) {
            Arrays.fill(memo[i], -1);
        }
        return dp(grid, width - 1, height - 1);
    }

    private int dp(int[][] grid, int width, int height) {
        if (width == 0 && height == 0) {
            return grid[0][0];
        }
        if (width < 0 || height < 0) {
            return Integer.MAX_VALUE;
        }
        if (memo[width][height] != -1) {
            return memo[width][height];
        }
        memo[width][height] = Math.min(dp(grid, width, height - 1), dp(grid, width - 1, height)) + grid[width][height];
        return memo[width][height];
    }
}

/**
 * 迭代解法
 * 自底向上
 */
class MinPathSum01 {
    public int minPathSum(int[][] grid) {
        int width = grid.length;
        int height = grid[0].length;
        int[][] memo = new int[width][height];
        memo[0][0] = grid[0][0];

        for (int i = 1; i < width; i++) {
            memo[i][0] = memo[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < height; j++) {
            memo[0][j] = memo[0][j - 1] + grid[0][j];
        }

        for (int i = 1; i < width; i++) {
            for (int j = 1; j < height; j++) {
                memo[i][j] = Math.min(memo[i - 1][j], memo[i][j - 1]) + grid[i][j];
            }
        }

        return memo[width - 1][height - 1];
    }
}