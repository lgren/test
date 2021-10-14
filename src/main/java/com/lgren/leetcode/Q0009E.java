package com.lgren.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.function.Function;
import java.util.function.IntFunction;

/**
 * 9. 回文数
 * https://leetcode-cn.com/problems/palindrome-number/
 * @author lgren
 * @since 2021-10-09 09:11
 * @since 2021-10-09 09:22
 */
public class Q0009E {
    // myself
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        int newX = x;
        int tempX = 0;
        do {
            tempX = tempX * 10 + (newX % 10);
            newX /= 10;
        } while (newX > 0);
        return tempX == x;
    }

    // another way of thinking
    public boolean isPalindrome1(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) return false;
        int newX = 0;
        while (x > newX) {
            newX = newX * 10 + x % 10;
            x /= 10;
        }
        return x == newX || newX / 10 == x;
    }

    @Test
    public void test() {
        base(this::isPalindrome);
    }

    private void base(Function<Integer, Boolean> func) {
        Assert.assertEquals(func.apply(1), true);
        Assert.assertEquals(func.apply(121), true);
        Assert.assertEquals(func.apply(123), false);
    }

    @Test
    public void test1() {

    }
}
