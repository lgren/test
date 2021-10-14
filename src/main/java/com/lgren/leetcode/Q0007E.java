package com.lgren.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.function.IntFunction;

/**
 * 7. 整数反转
 * https://leetcode-cn.com/problems/reverse-integer/
 * @author lgren
 * @since 2021-10-08 17:16
 */
public class Q0007E {
    public int reverse(int x) {
        long xl = x;
        int symbol = xl > 0 ? 1 : -1;
        xl *= symbol;
        long result = 0;
        do {
            result = result * 10 + xl % 10;
            if (result > Integer.MAX_VALUE) {
                return 0;
            }
            xl = xl / 10;
        } while (xl > 0);
        return (int) (result * symbol);
    }

    @Test
    public void test() {
        base(this::reverse);
    }

    private void base(IntFunction<Integer> func) {
        Assert.assertEquals((int)func.apply(123), 321);
        Assert.assertEquals((int)func.apply(-123), -321);
        Assert.assertEquals((int)func.apply(120), 21);
        Assert.assertEquals((int)func.apply(0), 0);
    }

    private void print(IntFunction<Integer> func) {
        System.out.println(func.apply(-2147483648));
        System.out.println(func.apply(123));
        System.out.println(func.apply(-123));
        System.out.println(func.apply(120));
        System.out.println(func.apply(0));
    }

    @Test
    public void name1() {
        System.out.println(Integer.MIN_VALUE);
    }
}
