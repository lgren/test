package com.lgren.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * 3. 无重复字符的最长子串
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 * @author lgren
 * @since 2020-05-22 4:57 下午
 */
public class Q0003M {
    public int lengthOfLongestSubstring(String s) {
        // best
        int[] last = new int[128];
        int n = s.length();
        int res = 0;
        int start = 0; // 窗口开始位置
        for (int i = 0; i < n; i++) {
            int tc = s.charAt(i);
            int oc = last[tc];
            last[tc] = i + 1;
            if (oc != 0 && oc > start) {
                res = Math.max(res, i - start);
                start = oc;
            }
        }
        res = Math.max(res, n - start);
        return res;
    }

    @Test
    public void test() {
        base(this::lengthOfLongestSubstring);
    }

    public int lengthOfLongestSubstring_2020_6_29_best(String s) {
        // best
        int[] last = new int[128];
        int n = s.length();

        int res = 0;
        int start = 0; // 窗口开始位置
        for (int i = 0; i < n; i++) {
            int index = s.charAt(i);
            int oldI = last[index];
            last[index] = i + 1;
            if (oldI != 0 && oldI >= start) {
                res = Math.max(res, i - start);
                start = oldI;
            }
        }
        res = Math.max(res, n - start);
        return res;
    }

    public int lengthOfLongestSubstring_2020_6_29_3(String s) {
        // 记录字符上一次出现的位置
        int[] last = new int[128];
        for (int i = 0; i < 128; i++) {
            last[i] = -1;
        }
        int n = s.length();

        int res = 0;
        int start = 0; // 窗口开始位置
        for (int i = 0; i < n; i++) {
            int index = s.charAt(i);
            start = Math.max(start, last[index] + 1);
            res = Math.max(res, i - start + 1);
            last[index] = i;
        }

        return res;
    }

    public int lengthOfLongestSubstring_2020_6_29_2(String s) {
        int[] temp = new int[128];// a-z:65-90 A-Z:97-122
        int res = 0;
        int start = 0;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            int thisCI = s.charAt(i);
            int oldI = temp[thisCI];
            temp[thisCI] = i;
            if ((oldI != 0 || (i != 0 && s.charAt(0) == thisCI)) && oldI >= start) {
                res = Math.max(res, i - start);
                start = oldI + 1;
            }
        }
        res = Math.max(res, len - start);
        return res;
    }

    public int lengthOfLongestSubstring_2020_6_29_1(String s) {
        int num = 0;
        int begin = 0;
        int len = s.length();
        Map<Character, Integer> selectMap = new HashMap<>(len);
        for (int i = 0; i < len; i++) {
            char thisChar = s.charAt(i);
            Integer oldI = selectMap.get(thisChar);
            selectMap.put(thisChar, i);
            if (oldI != null && oldI >= begin) {
                num = Math.max(num, i - begin);
                begin = oldI + 1;
            }
        }
        num = Math.max(num, len - begin);
        return num;
    }

    private void base(Function<String, Integer> func) {
        Assert.assertEquals((int) func.apply("a"), 1);
        Assert.assertEquals((int) func.apply("aa"), 1);
        Assert.assertEquals((int) func.apply("ab"), 2);
        Assert.assertEquals((int) func.apply("abc"), 3);
        Assert.assertEquals((int) func.apply("aba"), 2);
        Assert.assertEquals((int) func.apply("abcabcabcabc"), 3);
        Assert.assertEquals((int) func.apply("aabbcc"), 2);
        Assert.assertEquals((int) func.apply("abba"), 2);
    }

    @Test
    public void name() {
        System.out.println((int) 'a');
        System.out.println((int) 'z');
        System.out.println((int) 'A');
        System.out.println((int) 'Z');
        System.out.println((char) 32);
    }

}
