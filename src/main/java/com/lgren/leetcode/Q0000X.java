package com.lgren.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.function.Function;
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
        print(this::method);
        base(this::method);
    }

    private void base(Function<Integer, Integer> func) {
        Assert.assertEquals((int)func.apply(1), 2);
    }

    private void print(Function<Integer, Integer> func) {
        System.out.println(func.apply(1));
    }

    @Test
    public void test1() {

    }
}
