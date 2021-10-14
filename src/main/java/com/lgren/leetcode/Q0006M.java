package com.lgren.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.IntFunction;

/**
 * 6. Z 字形变换
 * https://leetcode-cn.com/problems/zigzag-conversion/
 * @author lgren
 * @since 2021-10-08 14:26
 */
public class Q0006M {
    @Test
    public void test() {
        base(this::convert);
    }

    private void base(BiFunction<String, Integer, String> func) {
        System.out.println(func.apply("PAYPALISHIRING", 3));
        System.out.println(func.apply("PAYPALISHIRING", 4));
        Assert.assertEquals(func.apply("PAYPALISHIRING", 3), "PAHNAPLSIIGYIR");
        Assert.assertEquals(func.apply("PAYPALISHIRING", 4), "PINALSIGYAHRPI");
    }

    public String convert(String s, int numRows) {
        if (numRows < 2) return s;
        int length = s.length();
        int groupN = numRows * 2 - 2;
        StringBuilder[] abArr = new StringBuilder[numRows];
        for (int i = 0; i < abArr.length; i++) {
            abArr[i] = new StringBuilder();
        }
        for (int i = 0; i < length; i++) {
            int groupPos = i % groupN;// 分组序号

            int row;// 第几行
            if (groupPos / numRows == 0) {
                row = groupPos;
            } else {
                int r = groupPos % numRows;// 分组序列在行中属于第几个 4 / 4 = 1~0, 5 / 4 = 1~1
                row = numRows - 2 - r;// numRows-1为最后一排序列, 跨一行再-1, 最后-r为当前位置
            }
            abArr[row].append(s.charAt(i));
        }
        for (int i = 1; i < numRows; i++) {
            abArr[0].append(abArr[i]);
        }
        return abArr[0].toString();
    }

    // 可展示解析 结果不正确
    public String convert2(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        int length = s.length();
        int groupN = numRows * 2 - 2;
        // char[][] result = new char[15][numRows];
        char[][] result = new char[numRows][1000];
        for (int i = 0; i < length; i++) {
            int group = i / groupN;// 分组
            int groupPos = i % groupN;// 分组序号

            int row;// 第几行
            int col= group * groupN;// 第几列
            if (groupPos / numRows == 0) {
                row = groupPos;
            } else {
                int r = groupPos % numRows;// 4 / 4 = 1~0, 5 / 4 = 1~1
                row = numRows - 2 - r;
                col += (r + 1) * 2;
            }
            // result[col][row] = s.charAt(i);
            result[row][col] = s.charAt(i);
        }
        StringBuilder sb = new StringBuilder();
        for (char[] cArr : result) {
            sb.append(new String(cArr));
            sb.append("\n");
        }
        return sb.toString();
    }
}
