package com.lgren.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 3. 无重复字符的最长子串
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 * @author lgren
 * @create 2019-05-16 09:30
 **/
public class LengthOfLongestSubstring {
    // public int lengthOfLongestSubstring(String s) {
    //
    // }
    public static int lengthOfLongestSubstring_1(String s) {
        Map<Character, Integer> map = new HashMap<>(s.length());
        int ans = 0;
        for (int l = 0, r = 0; r < s.length(); r++) {
            if (map.containsKey(s.charAt(r))) {
                l = map.get(s.charAt(r)) + 1;
            }
            ans = Math.max(r - l + 1, ans);
            map.put(s.charAt(r), r);
        }
        return ans;
    }

    public static int lengthOfLongestSubstring_2(String s) {
        int i = 0;
        int flag = 0;
        int length = 0;
        int result = 0;
        while (i < s.length()) {
            int pos = s.indexOf(s.charAt(i), flag);
            if (pos < i) {
                if (length > result) {
                    result = length;
                }
                if (result >= s.length() - pos - 1) {
                    return result;
                }
                length = i - pos - 1;
                flag = pos + 1;
            }
            length++;
            i++;
        }
        return length;
    }

    public static void main(String[] args) {
        String str = "abba";
        System.out.println(lengthOfLongestSubstring_1(str));
    }
}
