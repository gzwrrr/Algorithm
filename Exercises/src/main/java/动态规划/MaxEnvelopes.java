package 动态规划;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 354. 俄罗斯套娃信封问题
 * 通俗讲解：https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247496972&idx=1&sn=e51afab73618a9f2069af3270a928ac2&scene=21#wechat_redirect
 * @author gzw
 */
public class MaxEnvelopes {
    public int maxEnvelopes(int[][] envelopes) {
        int length = envelopes.length;
        if(length == 0) {
            return 0;
        }
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0];
            }
        });

        int[] height = new int[length];
        for (int i = 0; i < length; i++) {
            height[i] = envelopes[i][1];
        }
        return lengthOfLIS(height);
    }

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

    public int lengthOfLIS01(int[] nums) {
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
