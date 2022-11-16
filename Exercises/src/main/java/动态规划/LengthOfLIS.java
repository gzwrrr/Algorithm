package 动态规划;

import java.util.Arrays;

/**
 * leetcode 300
 * 最长递增子序列
 *
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 *
 * 基础版
 * 动态规划 -- 动态数组
 * 时间复杂度：O(N^2)
 * 空间复杂度：O(N)
 * @author gzw
 */
public class LengthOfLIS {
    public int lengthOfLIS(int[] nums) {
        int length = nums.length;
        if (length == 0) {
            return 0;
        }

        int[] dp = new int[length];
        Arrays.fill(dp, 1);
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int res = 0;
        for (int i = 0; i < length; i++) {
            res = Math.max(dp[i], res);
        }
        return res;
    }
}

/**
 * 动态数组
 * 常数时间优化
 */
class LengthOfLIS01 {
    public int lengthOfLIS(int[] nums) {
        int length = nums.length;
        if (length == 0) {
            return 0;
        }

        // 动态数组
        int[] dp = new int[length];
        dp[0] = 1;

        // 结果
        int res = 1;
        // 开始搜索
        for (int i = 1; i < length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}

/**
 * 二分查找 + 贪心
 * 时间复杂度：O(N * logN)
 * 空间复杂度：O(N)
 *
 *
 * leetcode 解释：https://leetcode.cn/problems/longest-increasing-subsequence/solutions/147667/zui-chang-shang-sheng-zi-xu-lie-by-leetcode-soluti/
 * 更容易理解的解释：https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247496972&idx=1&sn=e51afab73618a9f2069af3270a928ac2&scene=21#wechat_redirect
 */
class LengthOfLIS02 {
    public int lengthOfLIS(int[] nums) {
        int length = nums.length;
        if (length == 0) {
            return 0;
        }

        // 放置最小牌的牌堆
        int[] top = new int[length];
        // 牌堆数
        int piles = 0;
        for (int i = 0; i < length; i++) {
            int poker = nums[i];

            // 二分查找，定义左右边界
            int left = 0, right = piles;
            while (left < right) {
                int mid = left + ((right - left) >> 1);
                if (poker > top[mid]) {
                    left = mid + 1;
                } else if (poker < top[mid]) {
                    right = mid;
                } else {
                    right = mid;
                }
            }

            if (left == piles) {
                piles++;
            }
            top[left] = poker;
        }
        return piles;
    }
}

/**
 * 二分查找 + 贪心
 * 另一种写法（和上面的思路一样）
 */
class LengthOfLIS03 {
    public int lengthOfLIS(int[] nums) {
        int len = 1, n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] d = new int[n + 1];
        d[len] = nums[0];
        for (int i = 1; i < n; ++i) {
            if (nums[i] > d[len]) {
                d[++len] = nums[i];
            } else {
                // 如果找不到说明所有的数都比 nums[i] 大，此时要更新 d[1]，所以这里将 pos 设为 0
                int l = 1, r = len, pos = 0;
                while (l <= r) {
                    int mid = (l + r) >> 1;
                    if (d[mid] < nums[i]) {
                        pos = mid;
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                d[pos + 1] = nums[i];
            }
        }
        return len;
    }
}