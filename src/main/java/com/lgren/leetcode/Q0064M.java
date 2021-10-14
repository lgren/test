package com.lgren.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.function.Function;

/**
 * 64. 最小路径和
 * https://leetcode-cn.com/problems/minimum-path-sum/
 *
 * @author lgren
 * @since 2021-10-09 11:28
 * @since 2021-10-09 12:08
 */
public class Q0064M {
    public int minPathSum(int[][] grid) {
        int row;
        int col;
        if ((row = grid.length) == 0 || (col = grid[0].length) == 0) {
            return 0;
        }
        int[][] dp = new int[row][col];// 1.定义
        // 3.初始值
        // 2.关系式
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++){
                if (i == 0 || j == 0) {
                    if (j != 0) {
                        dp[i][j] = dp[i][j - 1] + grid[i][j];
                    } else if (i != 0) {
                        dp[i][j] = dp[i - 1][j] + grid[i][j];
                    } else {
                        dp[i][j] = grid[i][j];
                    }
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                }
            }
        return dp[row - 1][col - 1];
    }

    // another way of thinking
    public int minPathSum1(int[][] grid) {
        int row;
        int col;
        if ((row = grid.length) == 0 || (col = grid[0].length) == 0) {
            return 0;
        }
        int[][] dp = new int[row][col];// 1.定义
        // 3.初始值
        dp[0][0] = grid[0][0];
        for (int i = 1; i < row; i++) dp[i][0] = grid[i][0] + dp[i - 1][0];
        for (int i = 1; i < col; i++) dp[0][i] = grid[0][i] + dp[0][i - 1];
        // 2.关系式
        for (int i = 1; i < row; i++)
            for (int j = 1; j < col; j++)
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
        return dp[row - 1][col - 1];
    }

    @Test
    public void test() {
        print(this::minPathSum);
        base(this::minPathSum);
    }

    private void base(Function<int[][], Integer> func) {
        Assert.assertEquals((int) func.apply(new int[][]{new int[]{1, 3, 1}, new int[]{1, 5, 1}, new int[]{4, 2, 1}}), 7);
        Assert.assertEquals((int) func.apply(new int[][]{new int[]{1, 2, 3}, new int[]{4, 5, 6}}), 12);

    }

    private void print(Function<int[][], Integer> func) {
        System.out.println(func.apply(new int[][]{new int[]{1, 3, 1}, new int[]{1, 5, 1}, new int[]{4, 2, 1}}));
        System.out.println(func.apply(new int[][]{new int[]{1, 2, 3}, new int[]{4, 5, 6}}));
    }

    @Test
    public void test1() {

    }
}
