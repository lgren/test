package com.lgren.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

/**
 * 1. 两数之和
 * https://leetcode-cn.com/problems/two-sum/
 * @author lgren
 * @since 2020-05-22 1:57 下午
 */
public class Q0001E {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (map.containsKey(target - num)) {
                return new int[]{map.get(target - num), i};
            } else {
                map.put(num, i);
            }
        }
        throw new IllegalArgumentException("未找到");
    }

    @Test
    public void test() {
        Assert.assertArrayEquals(twoSum(new int[]{-3,4,3,90}, 0), new int[]{0, 2});
    }

}
