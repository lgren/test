package com.lgren.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.function.IntFunction;

/**
 * asd
 */
public class Q0000X {
    public int method(int num) {
        return num;
    }

    @Test
    public void test() {
        base(this::method);
    }

    private void base(IntFunction<Integer> func) {
        Assert.assertEquals((int)func.apply(1), 2);
    }

}
