package 动态规划.基础;

import java.util.Arrays;

/**
 * @author gzw
 */
public class Fibonacci {
    public static void main(String[] args) {
        int[] nums = new int[10];
        System.out.println(nums[0]);
    }


    /**
     * 基础版
     * 存在大量的重复计算
     * 效率极低
     * 时间复杂度：O(2 ^ N)
     * 空间复杂度：O(N)
     */
    public static int fib(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }

    /**
     * 带有备忘录的递归解法
     * 自顶向下
     * 时间复杂度：O(N)
     * 空间复杂度：O(N)
     */
    public static int fib1(int n) {
        if (n <= 0) {
            return 0;
        }
        int[] memo = new int[n + 1];
        Arrays.fill(memo, 0);
        return help(memo, n);
    }

    private static int help(int[] memo, int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        if (memo[n] != 0) {
            return memo[n];
        }
        memo[n] = help(memo, n - 1) + help(memo, n - 2);
        return memo[n];
    }

    /**
     * 迭代解法，动态规划
     * 自底向上
     *
     * 时间复杂度：O(N)
     * 空间复杂度：O(N)
     */
    public static int fib2(int n) {
        if (n <= 0) {
            return 0;
        }
        int[] dp = new int[n + 1];
        dp[1] = dp[2] = 1;
        for (int i = 3; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 迭代解法，动态规划
     * 自底向上
     *
     * 时间复杂度：O(N)
     * 空间复杂度：O(1)
     */
    public static int fib3(int n) {
        if (n <= 0) {
            return 0;
        }
        int pre = 1, cur = 1;
        for (int i = 2; i < n; i++) {
            int sum = pre + cur;
            pre = cur;
            cur = sum;
        }
        return cur;
    }

}

/**
 * 进阶解法
 * 矩阵快速幂
 * 时间复杂度：O(logN)
 * 空间复杂度：O(1)
 */
class Fibonacci01 {
    static final int MOD = 1000000007;

    public int fib(int n) {
        if (n < 2) {
            return n;
        }
        int[][] q = {{1, 1}, {1, 0}};
        int[][] res = pow(q, n - 1);
        return res[0][0];
    }

    public int[][] pow(int[][] a, int n) {
        int[][] ret = {{1, 0}, {0, 1}};
        while (n > 0) {
            if ((n & 1) == 1) {
                ret = multiply(ret, a);
            }
            n >>= 1;
            a = multiply(a, a);
        }
        return ret;
    }

    public int[][] multiply(int[][] a, int[][] b) {
        int[][] c = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                c[i][j] = (int) (((long) a[i][0] * b[0][j] + (long) a[i][1] * b[1][j]) % MOD);
            }
        }
        return c;
    }
}