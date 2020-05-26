package com.lgren.leetCode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 3. 无重复字符的最长子串
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 *
 * @author lgren
 * @since 2020-05-22 4:57 下午
 */
public class Q0003M {
    // public int lengthOfLongestSubstring(String s) {
    //     int result = 0;
    //     int beginI = 0;
    //     int len = s.length();
    //     Map<Character, Integer> selectMap = new HashMap<>(len);
    //     for (int i = 0; i < len; i++) {
    //         char c = s.charAt(i);
    //         if (selectMap.containsKey(c)) {
    //             result = Math.max(result, i - beginI);
    //             beginI = selectMap.get(c) + 1;
    //         }
    //         selectMap.put(c, i);
    //     }
    //     return result;
    // }
    public int lengthOfLongestSubstring(String s) {
        int result = 0;
        int beginI = 0;
        int len = s.length();
        Map<Character, Integer> selectMap = new HashMap<>(len);
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (selectMap.containsKey(c)) {
                beginI = selectMap.get(c) + 1;
            }
            result = Math.max(result, i - beginI + 1);
            selectMap.put(c, i);
        }
        return result;
    }

    @Test
    public void test() {
        System.out.println(lengthOfLongestSubstring("abba"));
    }
}
