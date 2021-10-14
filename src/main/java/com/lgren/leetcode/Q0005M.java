package com.lgren.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

/**
 * 5. 最长回文子串
 * https://leetcode-cn.com/problems/longest-palindromic-substring/
 * 下方链接较为详细的讲解了动态规划
 * https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zhong-xin-kuo-san-dong-tai-gui-hua-by-liweiwei1419/
 *
 * @author Lgren
 * @since 2020/6/29 4:22 下午
 */
public class Q0005M {
    /**
     * 网友极简解法
     * <br/>
     * 结果:<br/>
     * 执行用时： 3 ms 在所有 Java 提交中击败了 99.69% 的用户<br/>
     * 内存消耗： 38.4 MB 在所有 Java 提交中击败了 82.57% 的用户<br/>
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        // 保存起始位置，测试了用数组似乎能比全局变量稍快一点
        int[] range = new int[2];
        char[] str = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            // 把回文看成中间的部分全是同一字符，左右部分相对称
            // 找到下一个与当前字符不同的字符
            i = findLongest(str, i, range);
        }
        return s.substring(range[0], range[1] + 1);
    }

    public static int findLongest(char[] str, int low, int[] range) {
        // 查找中间部分
        int high = low;
        while (high < str.length - 1 && str[high + 1] == str[low]) {
            high++;
        }
        // 定位中间部分的最后一个字符
        int ans = high;
        // 从中间向左右扩散
        while (low > 0 && high < str.length - 1 && str[low - 1] == str[high + 1]) {
            low--;
            high++;
        }
        // 记录最大长度
        if (high - low > range[1] - range[0]) {
            range[0] = low;
            range[1] = high;
        }
        return ans;
    }

    /**
     * 思路:<br/>
     * 遍历str, 记住符合条件的开始坐标以及长度, 后续依次遍历从 符合条件的缓存记录尾标后一位 开始... 如果长度大于记录的长度则替换 否则忽略
     * 与{@link #longestPalindrome1}区别在于后续遍历从 符合条件的缓存记录尾标后一位 开始.
     * 将后续满足条件的尾标最大的那个也记录下来, 可以省去更多步骤
     * <br/>
     * 结果:<br/>
     * 执行用时： 155 ms 在所有 Java 提交中击败了 52.64% 的用户<br/>
     * 内存消耗： 38.4 MB 在所有 Java 提交中击败了 87.45% 的用户<br/>
     */
    public String longestPalindrome2(String s) {
        int[] resultArr = {0, 0}, nextArr = {0, 0};
        int len = s.length();
        char[] charArr = s.toCharArray();
        for (int i = 0; i < len; i++) {
            for (int j = nextArr[1] + i; j < len; j++) {
                boolean isOK = true;
                for (int k = i; j - k > 0 && isOK; k++) {
                    isOK = charArr[k] == charArr[j - (k - i)];
                }
                if (isOK && (j - i + 1) > nextArr[1]) {
                    nextArr[0] = i;
                    nextArr[1] = j - i + 1;
                }
            }
            if (nextArr[1] > resultArr[1]) {
                resultArr[0] = nextArr[0];
                resultArr[1] = nextArr[1];
            }
        }
        return s.substring(resultArr[0], resultArr[0] + resultArr[1]);
    }

    /**
     * 思路:<br/>
     * 遍历str, 记住符合条件的开始坐标以及长度, 后续依次遍历从 符合条件的记录尾标后一位 开始... 如果长度大于记录的长度则替换 否则忽略
     * 与{@link #longestPalindrome0}区别在于后续遍历从 符合条件的记录尾标后一位 开始.
     * 因为例如 abbaedcba 第一次从0开始, 符合条件的为 abba, 符合条件的记录尾标后一位为 4 所以后续遍历a bbaedcba, 从e开始遍历
     * 因为abba符合条件 那么bba肯定不符合条件(起码长度就已经没之前的长了), 那么就略去此步骤从下一位开始.
     * <br/>
     * 结果:<br/>
     * 执行用时： 416 ms 在所有 Java 提交中击败了 10.33% 的用户<br/>
     * 内存消耗： 38 MB 在所有 Java 提交中击败了 98.59% 的用户<br/>
     */
    public String longestPalindrome1(String s) {
        int resultStart = 0, resultLen = 0;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            for (int j = resultLen + i; j < len; j++) {
                boolean isOK = true;
                for (int k = i; j - k > 0 && isOK; k++) {
                    isOK = s.charAt(k) == s.charAt(j - (k - i));
                }
                if (isOK && (j - i + 1) > resultLen) {
                    resultStart = i;
                    resultLen = j - i + 1;
                }
            }
        }
        return s.substring(resultStart, resultStart + resultLen);
    }


    /**
     * 思路: 遍历str, 记住符合条件的开始坐标以及长度, 后续依次遍历 从 1, 2... 开始 如果长度大于记录的长度则替换 否则忽略
     * 结果: 超时
     */
    public String longestPalindrome0(String s) {
        int resultStart = 0, resultLen = 0;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                boolean isOK = true;
                for (int k = i; j - k > 0 && isOK; k++) {
                    isOK = s.charAt(k) == s.charAt(j - (k - i));
                }
                if (isOK && (j - i + 1) > resultLen) {
                    resultStart = i;
                    resultLen = j - i + 1;
                }
            }
        }
        return s.substring(resultStart, resultStart + resultLen);
    }

    @Test
    public void test() {
        base(this::longestPalindrome0);
        base(this::longestPalindrome1);
        base(this::longestPalindrome2);
    }

    private void base(UnaryOperator<String> func) {
        Assert.assertEquals(func.apply("babad"), "bab");
        Assert.assertEquals(func.apply("cbbd"), "bb");
        Assert.assertEquals(func.apply("a"), "a");
        Assert.assertEquals(func.apply("ac"), "a");
        Assert.assertEquals(func.apply("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggg"), "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff");
    }

}
