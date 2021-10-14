package com.lgren.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * 62. 不同路径(动态规划)
 * https://leetcode-cn.com/problems/unique-paths/
 *
 * @author lgren
 * @since 2021-10-09 11:23
 */
public class Q0062M {
    public int uniquePaths(int m, int n) {
        if (m < 1 || n < 1) return 0;
        int[][] dp = new int[m][n];// 1.定义dp
        for(int i = 0; i < m; i++) dp[i][0] = 1;// 3.找到初始值
        for(int i = 0; i < n; i++) dp[0][i] = 1;// 3.找到初始值
        for(int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                dp[i][j] = dp[i][j-1] + dp[i-1][j];// 2.找到表达式
        return dp[m-1][n-1];
    }

    @Test
    public void test() {
        print(this::uniquePaths);
        base(this::uniquePaths);
    }

    private void base(BiFunction<Integer, Integer, Integer> func) {
        Assert.assertEquals((int)func.apply(3, 7), 28);
        Assert.assertEquals((int)func.apply(3, 2), 3);
        Assert.assertEquals((int)func.apply(7, 3), 28);
        Assert.assertEquals((int)func.apply(3, 3), 6);
    }

    private void print(BiFunction<Integer, Integer, Integer> func) {
        System.out.println(func.apply(3, 7));
        System.out.println(func.apply(3, 2));
        System.out.println(func.apply(7, 3));
        System.out.println(func.apply(3, 3));
    }

    @Test
    public void test1() {

    }
}
