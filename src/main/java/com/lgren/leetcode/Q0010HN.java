package com.lgren.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * 10. 正则表达式匹配
 * https://leetcode-cn.com/problems/regular-expression-matching/
 * @author lgren
 * @since 2021-10-09 09:29
 */
public class Q0010HN {
    public boolean isMatch(String s, String p) {
        int sLen = s.length();

        int pI = 0;
        boolean a = false;// any
        int pl;
        boolean e = false;// extend
        int pr;
        for (int i = 0; i < sLen; i++) {
            int pn = p.charAt(pI);
            if (pn == '*') {

            }
            // if (e) {
            //
            // } else {
            //     if (a = (pn == '.')) {
            //         continue;
            //     } else if (pn == '*') {
            //
            //     } else {
            //         pn = p.charAt(pI);
            //     }
            //     pI++;
            // }

        }
        return a;
    }


    @Test
    public void test() {
        print(this::isMatch);
        base(this::isMatch);
    }

    private void base(BiFunction<String, String, Boolean> func) {
        Assert.assertEquals(func.apply("aa", "a"), false);
        Assert.assertEquals(func.apply("aa", "a*"), true);
        Assert.assertEquals(func.apply("ab", ".*"), true);
        Assert.assertEquals(func.apply("aab", "c*a*b"), true);
        Assert.assertEquals(func.apply("mississippi", "mis*is*p*."), false);
    }

    private void print(BiFunction<String, String, Boolean> func) {
        // System.out.println(func.apply(1));
    }

    @Test
    public void test1() {
        System.out.println(method(3));
    }

    // int method(int n) {
    //     if (n <= 1) return n;
    //     int[] dp = new int[n + 1];// 1.定义dp
    //     dp[1] = 1;dp[2] = 2;// 3.找到初始值
    //     for(int i = 3; i <= n; i++) {
    //         dp[i] = dp[i - 1] + dp[i - 2];
    //     }
    //     return dp[n];
    // }

    int method(int n) {
        if (n <= 1) return n;
        int[] dp = new int[n];// 1.定义dp
        dp[1] = 1;dp[2] = 2;// 3.找到初始值
        for(int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
//     http://10.160.12.143:8001/console/login/LoginForm.jsp：8003 8005 8007 8009 8011 8013
//     http://10.160.12.144:8001/console/login/LoginForm.jsp：8003 8005 8007 8009 8011 8013
//     http://10.160.12.223:8001/console/login/LoginForm.jsp：8003 8005 8007 8009 8011 8013
//     http://10.160.12.225:8001/console/login/LoginForm.jsp：8003 8005 8007 8009 8011 8013
//     http://10.160.12.226:8001/console/login/LoginForm.jsp：8003 8005 8007 8009 8011 8013
