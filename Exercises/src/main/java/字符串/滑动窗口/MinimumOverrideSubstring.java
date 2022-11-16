package 字符串.滑动窗口;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class MinimumOverrideSubstring {
    public static void main(String[] args) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        map.put('c', map.getOrDefault('c', 0) + 1);
        System.out.println(map);
    }

    public String minWindow(String s, String t) {
        // 记录字符的个数与滑动窗口
        Map<Character, Integer> need = new HashMap<Character, Integer>(), window = new HashMap<Character, Integer>();

        // 初始化 need Map，所有需要的字符个数初始化为 0
        for (int i = 0; i < t.length(); i++) {
            need.put(t.charAt(i), need.getOrDefault(t.charAt(i), 0) + 1);
        }

        // 初始化左右指针，这个是需要滑动的窗口
        int left = 0, right = 0;
        // 初始化记录字串的起始指针与长度，这个是需要根据条件更新的数据
        int start = 0, length = Integer.MAX_VALUE;
        // 记录是否合法，当数量与 t 的长度一致时合法，此时找到了字串，但是可能不是最短的，需要继续更新
        int valid = 0;

        // 开始遍历
        while (right < s.length()) {
            // 取出一个字符后，right 指针后移一位
            char ch = s.charAt(right++);
            // 判断 ch 是否在 need 中，是的话 need 中对应的字符个数 + 1
            if (need.containsKey(ch)) {
                // 更新窗口的值
                window.put(ch, window.getOrDefault(ch, 0) + 1);
                // 需要的字符达到个数就让 valid + 1
                if (window.get(ch).equals(need.get(ch))) {
                    valid++;
                }
            }

            // 当出现一个符合的字串时，左指针开始往后移
            while (valid == need.size()) {
                // 更新最小覆盖长度
                if (right - left < length) {
                    start = left;
                    length = right - left;
                }
                // 取出一个字符看是否让子串变得不符合要求
                char c = s.charAt(left++);
                if (window.containsKey(c)) {
                    if (window.get(c).equals(need.get(c))) {
                        valid--;
                    }
                    window.put(c, window.getOrDefault(c, 0) - 1);
                }
            }
        }
        return length == Integer.MAX_VALUE ? "" : s.substring(start, length + start);
    }
}
