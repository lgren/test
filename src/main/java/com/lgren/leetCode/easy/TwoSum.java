package com.lgren.leetCode.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 * https://leetcode-cn.com/problems/two-sum/
 * @author lgren
 * @create 2019-05-16 09:02
 **/
public class TwoSum {
    /**
     * 两数之和
     * https://leetcode-cn.com/problems/two-sum/
     * @create 2019/4/9 2:43 PM
     * @author Lgren
     */
    public static int[] twoSum(int[] nums, int target) {
        int len = nums.length;
        Map<Integer, Integer> map;
        map = new HashMap<>(len);
        for (int i = 0; i < len; i++) {
            int match = target - nums[i];
            if (map.containsKey(match)) {
                return new int[]{map.get(match), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public static void main(String[] args) {
        int[] arr = {-1, -2, -3, -4, -5};
        System.out.println(Arrays.toString(TwoSum.twoSum(arr, -8)));
    }
}
