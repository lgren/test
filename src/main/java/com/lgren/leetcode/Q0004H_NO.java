package com.lgren.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.function.ToDoubleBiFunction;

/**
 * 4. 寻找两个正序数组的中位数
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/
 * @since 2020/6/29 4:08 下午
 * @author Lgren
 */
public class Q0004H_NO {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        return 2.0;
    }

    @Test
    public void test() {
        base(this::findMedianSortedArrays);
    }

    private void base(ToDoubleBiFunction<int[], int[]> func) {
        Assert.assertEquals(func.applyAsDouble(new int[]{1, 3}, new int[]{2}), 2.0, 0.0);
        Assert.assertEquals(func.applyAsDouble(new int[]{1, 2}, new int[]{3, 4}), 2.5, 0.0);
    }

}
