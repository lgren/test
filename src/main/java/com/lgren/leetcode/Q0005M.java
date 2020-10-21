package com.lgren.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.UnaryOperator;

/**
 * 5. 最长回文子串
 * https://leetcode-cn.com/problems/longest-palindromic-substring/
 * 下方链接较为详细的讲解了动态规划
 * https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zhong-xin-kuo-san-dong-tai-gui-hua-by-liweiwei1419/
 * @since 2020/6/29 4:22 下午
 * @author Lgren
 */
public class Q0005M {
    public String longestPalindrome(String s) {
        return "";
    }
    @Test
    public void test() {
        base(this::longestPalindrome);
    }

    private void base(UnaryOperator<String> func) {
        Assert.assertEquals(func.apply("babad"), "bab");
        Assert.assertEquals(func.apply("cbbd"), "bb");
    }

}
