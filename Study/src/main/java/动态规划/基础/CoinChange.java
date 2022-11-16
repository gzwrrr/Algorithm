package 动态规划.基础;

import java.util.Arrays;
import java.util.List;

/**
 * 凑零钱问题
 * 给你 k 种⾯值的硬币，⾯值分别为 c1, c2 ... ck ，每种硬
 * 币的数量⽆限，再给⼀个总⾦额 amount ，问你最少需要⼏枚硬币凑出这个
 * ⾦额，如果不可能凑出，算法返回 -1 。
 *
 *
 * @author gzw
 */
public class CoinChange {
    public static void main(String[] args) {
        List<Integer> coins = Arrays.asList(1, 2, 5);
        CoinChange coinChange = new CoinChange();
        System.out.println(coinChange.coinChange2(coins, 11));
    }

    /**
     * dp 数组的定义：
     * 当前的目标金额是 n，至少需要 dp[n] 个硬币才能凑出该金额
     *
     * 确定了 dp 函数的含义后，需要做的就是确定「选择」并择优
     * 即对于每个状态，可以做出什么改变当前「状态」
     * 具体到当前这个问题，就是无论当前目标金额是什么，从面额列表中选择一个硬币，这样目标金额的「状态」就会发生改变
     *
     * 最后需要确定 base case
     * 当目标金额为 0 时，所需硬币数量为 0
     * 当目标金额小于 0 时，无解，返回 -1
     *
     * 基础版，递归解法，自顶向下
     * 时间复杂度：O(K * N ^ K)，指数级别
     */
    public int coinChange(List<Integer> coins, int amount) {
        // base case
        if (amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        // 因为最后要获取最小值，所以一开始先初始化为最大以方便比较
        int res = Integer.MAX_VALUE;
        for (Integer coin : coins) {
            // 改变总金额的状态，让子问题自己解决
            int sub = coinChange(coins, amount - coin);
            // 找不到就继续
            if (sub == -1) {
                continue;
            }
            // 因为当前已经使用了一个硬币，所以比较的是子问题使用的硬币 + 1
            res = Math.min(res, sub + 1);
        }
        return res != Integer.MAX_VALUE ? res : -1;
    }

    /**
     * 带备忘录 memo 的递归解法，自顶向下
     * 时间复杂度：O(K * N)
     */
    public int coinChange1(List<Integer> coins, int amount) {
        return dp1(coins, amount, new int[amount + 1]);
    }
    public int dp1(List<Integer> coins, int amount, int[] memo) {
        // base case
        if (amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        // 查询备忘录，避免重复计算
        if (memo[amount] != 0) {
            return memo[amount];
        }
        int res = Integer.MAX_VALUE;
        for (Integer coin : coins) {
            int sub = dp1(coins, amount - coin, memo);
            if (sub == -1) {
                continue;
            }
            res = Math.min(res, sub + 1);
        }
        memo[amount] = res != Integer.MAX_VALUE ? res : -1;
        return memo[amount];
    }

    /**
     * 迭代解法基础版，自底向上
     * 使用 dp 数组
     */
    public int coinChange2(List<Integer> coins, int amount) {
        int[] dp = new int[amount + 1];
        // 初始化为最大数，即 amount + 1
        Arrays.fill(dp, amount + 1);
        // base case
        dp[0] = 0;
        for (int i = 0; i < dp.length; i++) {
            for (Integer coin : coins) {
                // 子问题无解
                if (i - coin < 0) {
                    continue;
                }
                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }
        return (dp[amount] == amount + 1) ? -1 : dp[amount];
    }
}
