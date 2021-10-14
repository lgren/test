package com.lgren.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.function.Function;
import java.util.function.IntFunction;

/**
 * 字符串转换整数 (atoi)
 * https://leetcode-cn.com/problems/string-to-integer-atoi/
 * @author lgren
 * @since 2021-10-08 17:41
 */
public class Q0008M {
    public int myAtoi(String s) {
        int l = s.length();
        boolean start = false;
        long result = 0;
        int symbol = 1;
        for (int i = 0; i < l; i++) {
            char c = s.charAt(i);
            if (!start) {
                if (c == ' ') {// 空格
                    continue;
                } else if (c == '+') {// +
                } else if (c == '-') {// -
                    symbol = -1;
                } else if (c >= '0' && c <= '9') {// 0-9
                    result = c - '0';
                } else {
                    return 0;
                }
                start = true;
            } else if (c >= '0' && c <= '9') {// 0-9
                result = result * 10 + c - '0';
                if (result > Integer.MAX_VALUE) {
                    if (symbol == -1) {
                        return Integer.MIN_VALUE;
                    } else {
                        return Integer.MAX_VALUE;
                    }
                }
            } else {
                break;
            }
        }
        return (int)result * symbol;
    }

    @Test
    public void test() {
        print(this::myAtoi);
        base(this::myAtoi);
    }

    private void base(Function<String, Integer> func) {
        Assert.assertEquals((int)func.apply("42"), 42);
        Assert.assertEquals((int)func.apply("   -42"), -42);
        Assert.assertEquals((int)func.apply("words and 987"), 0);
        Assert.assertEquals((int)func.apply("-91283472332"), -2147483648);
    }

    private void print(Function<String, Integer> func) {
        System.out.println(func.apply("42"));
        System.out.println(func.apply("   -42"));
        System.out.println(func.apply("words and 987"));
        System.out.println(func.apply("-91283472332"));
    }

    @Test
    public void test1() {
        System.out.println(0 + '0');
        System.out.println(0 + '9');
        System.out.println(0 + '-');
        System.out.println(0 + '+');
        System.out.println(0 + ' ');
        System.out.println(0 + '.');
    }
}
