package 字符串.滑动窗口;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * leetcode 567
 * 字符串的排列
 *
 * 给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。如果是，返回 true ；否则，返回 false 。
 * 换句话说，s1 的排列之一是 s2 的 子串 。
 *
 * 滑动窗口解法
 *
 * @author gzw
 */
public class CheckInclusion {
    /**
     * s1 是需要判断的字串，s2 是需要搜索的字符串
     * 基础版，效率较低
     *
     * 时间复杂度：O(N)，常数时间较大
     * 空间复杂度：O(N)
     */
    public boolean checkInclusion(String s1, String s2) {

        // 存储 s1 中字符的个数，因为其中可能有重复的，所以 size 要额外注意
        Map<Character, Integer> need = new HashMap<>(), window = new HashMap<>();

        // 初始化 need 中的个数
        for (int i = 0; i < s1.length(); i++) {
            need.put(s1.charAt(i), need.getOrDefault(s1.charAt(i), 0) + 1);
        }

        // 初始化左右指针，均为 0
        int left = 0, right = 0;
        // 初始化是否合法的标识（是否有合法的字串）
        int valid = 0;

        // 开始遍历
        while (right < s2.length()) {
            // 取出一个字符，取出后右指针后移
            char ch = s2.charAt(right++);
            // 如果取出的 ch 是 need 中的一个字符，那么 window 中对应的字符个数 + 1，当 window 中字符个数等于 need 中对应的字符的个数时，valid + 1
            if (need.containsKey(ch)) {
                window.put(ch, window.getOrDefault(ch, 0) + 1);
                if (need.get(ch).equals(window.get(ch))) {
                    valid++;
                }
            }

            // 判断窗口是否需要缩小
            while (right - left >= s1.length()) {
                // 这里先判断是否合法，即是否已经找到了字串
                if (valid == need.size()) {
                    return true;
                }

                // 取出一个字符，并让左指针后移一位
                char c = s2.charAt(left++);

                // 更新状态
                if (need.containsKey(c)) {
                    if (need.get(c).equals(window.get(c))) {
                        valid--;
                    }
                    window.put(c, window.getOrDefault(c, 0) - 1);
                }
            }
        }
        return false;
    }
}

/**
 * 滑动窗口 进阶版 1
 * 改变存储的数据结构
 */
class CheckInclusion01 {
    /**
     * 使用数组直接存储字符个数，优化时空复杂度
     */
    public boolean checkInclusion(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        if (n > m) {
            return false;
        }

        // 存储字符个数的数组
        int[] cnt1 = new int[26], cnt2 = new int[26];
        for (int i = 0; i < n; i++) {
            ++cnt1[s1.charAt(i) - 'a'];
            ++cnt2[s2.charAt(i)- 'a'];
        }
        // 开始移动窗口，判断是否找到子串
        for (int i = n; i <= m; i++) {
            if (Arrays.equals(cnt1, cnt2)) {
                return true;
            }
            ++cnt2[s2.charAt(i) - 'a'];
            --cnt2[s2.charAt(i - n) - 'a'];
        }
        return false;
    }
}


/**
 * 滑动窗口 进阶版 2
 * 优化不同字符的比较（上面的每次都要判断一次数组是否相等）
 */
class CheckInclusion02 {
    /**
     * 使用一个数组存储并使用一个指针 diff 判断是否找到合法的子串
     */
    public boolean checkInclusion(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        if (n > m) {
            return false;
        }
        int[] cnt = new int[26];
        for (int i = 0; i < n; ++i) {
            --cnt[s1.charAt(i) - 'a'];
            ++cnt[s2.charAt(i) - 'a'];
        }
        int diff = 0;
        for (int c : cnt) {
            if (c != 0) {
                ++diff;
            }
        }
        if (diff == 0) {
            return true;
        }
        for (int i = n; i < m; ++i) {
            int x = s2.charAt(i) - 'a', y = s2.charAt(i - n) - 'a';
            if (x == y) {
                continue;
            }
            if (cnt[x] == 0) {
                ++diff;
            }
            ++cnt[x];
            if (cnt[x] == 0) {
                --diff;
            }
            if (cnt[y] == 0) {
                ++diff;
            }
            --cnt[y];
            if (cnt[y] == 0) {
                --diff;
            }
            if (diff == 0) {
                return true;
            }
        }
        return false;
    }
}

/**
 * TODO
 */