package com.lgren.leetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems
 * 简单题
 * @author lgren
 * @create 2019-04-09 2:42 PM
 **/
public class Easy {
    public static void main(String[] args) {
        Easy easy = new Easy();
        int[] arr = {-1, -2, -3, -4, -5};
        int[] result = easy.twoSum(arr, -8);
        System.out.println();
    }

    /**
     * 两数之和
     * https://leetcode-cn.com/problems/two-sum/
     * @create 2019/4/9 2:43 PM
     * @author Lgren
     */
    public int[] twoSum(int[] nums, int target) {
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

    public int[] twoSum1(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;
    }

}
