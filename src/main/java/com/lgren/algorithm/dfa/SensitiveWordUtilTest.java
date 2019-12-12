package com.lgren.algorithm.dfa;

import com.google.common.collect.Sets;
import com.lgren.util.LgrenUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 敏感词工具类
 * @author lgren
 * @since 2019-10-24 15:27
 */
public class SensitiveWordUtilTest {
    @Test
    public void 获取所有开始结束坐标_最小匹配_1() {
        SensitiveWordUtil.SENSITIVE_MAP.clear();
        Sets.newHashSet("test", "testt", "testttt").forEach(SensitiveWordUtil::addWordToMap);
        String text = "testtttttttttttt";
        Map<Integer, Integer> actualMap = SensitiveWordUtil.getAllWordBeginEnd(text, SensitiveWordUtil.MIN_MATCH_TYPE);

        Map<Integer, Integer> expectMap = LgrenUtil.newHashMap(0, 3);
        boolean result = false;
        for (Map.Entry<Integer, Integer> entry : actualMap.entrySet()) {
            result = Objects.equals(expectMap.remove(entry.getKey()), entry.getValue());
            if (!result) {
                break;
            }
        }
        result &= expectMap.isEmpty();
        if (!result) {
            System.out.println(LgrenUtil.newHashMap(0, 3));
            System.out.println(actualMap);
        }
        Assert.assertTrue(result);
        // Assert.assertEquals(matchLength, matchLength, 0);
    }

    @Test
    public void 获取所有开始结束坐标_最小匹配_2() {
        SensitiveWordUtil.SENSITIVE_MAP.clear();
        Sets.newHashSet("test", "testt", "testttt").forEach(SensitiveWordUtil::addWordToMap);
        String text = "testtt";
        Map<Integer, Integer> actualMap = SensitiveWordUtil.getAllWordBeginEnd(text, SensitiveWordUtil.MIN_MATCH_TYPE);

        Map<Integer, Integer> expectMap = LgrenUtil.newHashMap(0, 3);
        boolean result = false;
        for (Map.Entry<Integer, Integer> entry : actualMap.entrySet()) {
            result = Objects.equals(expectMap.remove(entry.getKey()), entry.getValue());
            if (!result) {
                break;
            }
        }
        result &= expectMap.isEmpty();
        if (!result) {
            System.out.println(LgrenUtil.newHashMap(0, 3));
            System.out.println(actualMap);
        }
        Assert.assertTrue(result);
        // Assert.assertEquals(matchLength, matchLength, 0);
    }

    @Test
    public void 获取所有开始结束坐标_最小匹配_3() {
        SensitiveWordUtil.SENSITIVE_MAP.clear();
        Sets.newHashSet("test", "testt", "testttt").forEach(SensitiveWordUtil::addWordToMap);
        String text = "vdvdtestssvtesvdsvdsttt";
        Map<Integer, Integer> actualMap = SensitiveWordUtil.getAllWordBeginEnd(text, SensitiveWordUtil.MIN_MATCH_TYPE);

        Map<Integer, Integer> expectMap = LgrenUtil.newHashMap(4, 7);
        boolean result = false;
        for (Map.Entry<Integer, Integer> entry : actualMap.entrySet()) {
            result = Objects.equals(expectMap.remove(entry.getKey()), entry.getValue());
            if (!result) {
                break;
            }
        }
        result &= expectMap.isEmpty();
        if (!result) {
            System.out.println(LgrenUtil.newHashMap(4, 7));
            System.out.println(actualMap);
        }
        Assert.assertTrue(result);
        // Assert.assertEquals(matchLength, matchLength, 0);
    }

    @Test
    public void 获取所有开始结束坐标_最小匹配_4() {
        SensitiveWordUtil.SENSITIVE_MAP.clear();
        Sets.newHashSet("123", "1234", "1234567").forEach(SensitiveWordUtil::addWordToMap);
        String text = "543512534123454312345673431232";
        Map<Integer, Integer> actualMap = SensitiveWordUtil.getAllWordBeginEnd(text, SensitiveWordUtil.MIN_MATCH_TYPE);

        Map<Integer, Integer> expectMap = LgrenUtil.newHashMap(9, 11, 16, 18, 26, 28);
        boolean result = false;
        for (Map.Entry<Integer, Integer> entry : actualMap.entrySet()) {
            result = Objects.equals(expectMap.remove(entry.getKey()), entry.getValue());
            if (!result) {
                break;
            }
        }
        result &= expectMap.isEmpty();
        if (!result) {
            System.out.println(LgrenUtil.newHashMap(9, 11, 16, 18, 26, 28));
            System.out.println(actualMap);
        }
        Assert.assertTrue(result);
        // Assert.assertEquals(matchLength, matchLength, 0);
    }

    @Test
    public void 获取所有开始结束坐标_最大匹配_1() {
        SensitiveWordUtil.SENSITIVE_MAP.clear();
        Sets.newHashSet("test", "testt", "testttt").forEach(SensitiveWordUtil::addWordToMap);
        String text = "testtttttttttttt";
        Map<Integer, Integer> actualMap = SensitiveWordUtil.getAllWordBeginEnd(text, SensitiveWordUtil.MAX_MATCH_TYPE);

        Map<Integer, Integer> expectMap = LgrenUtil.newHashMap(0, 6);
        boolean result = false;
        for (Map.Entry<Integer, Integer> entry : actualMap.entrySet()) {
            result = Objects.equals(expectMap.remove(entry.getKey()), entry.getValue());
            if (!result) {
                break;
            }
        }
        result &= expectMap.isEmpty();
        if (!result) {
            System.out.println(LgrenUtil.newHashMap(0, 6));
            System.out.println(actualMap);
        }
        Assert.assertTrue(result);
    }

    @Test
    public void 获取所有开始结束坐标_最大匹配_2() {
        SensitiveWordUtil.SENSITIVE_MAP.clear();
        Sets.newHashSet("test", "testt", "testttt").forEach(SensitiveWordUtil::addWordToMap);
        String text = "testtt";
        Map<Integer, Integer> actualMap = SensitiveWordUtil.getAllWordBeginEnd(text, SensitiveWordUtil.MAX_MATCH_TYPE);

        Map<Integer, Integer> expectMap = LgrenUtil.newHashMap(0, 4);
        boolean result = false;
        for (Map.Entry<Integer, Integer> entry : actualMap.entrySet()) {
            result = Objects.equals(expectMap.remove(entry.getKey()), entry.getValue());
            if (!result) {
                break;
            }
        }
        result &= expectMap.isEmpty();
        if (!result) {
            System.out.println(LgrenUtil.newHashMap(0, 4));
            System.out.println(actualMap);
        }
        Assert.assertTrue(result);
    }

    @Test
    public void 获取所有开始结束坐标_最大匹配_3() {
        SensitiveWordUtil.SENSITIVE_MAP.clear();
        Sets.newHashSet("test", "testt", "testttt").forEach(SensitiveWordUtil::addWordToMap);
        String text = "vdvdtestssvtesvdsvdsttt";
        Map<Integer, Integer> actualMap = SensitiveWordUtil.getAllWordBeginEnd(text, SensitiveWordUtil.MAX_MATCH_TYPE);

        Map<Integer, Integer> expectMap = LgrenUtil.newHashMap(4, 7);
        boolean result = false;
        for (Map.Entry<Integer, Integer> entry : actualMap.entrySet()) {
            result = Objects.equals(expectMap.remove(entry.getKey()), entry.getValue());
            if (!result) {
                break;
            }
        }
        result &= expectMap.isEmpty();
        if (!result) {
            System.out.println(LgrenUtil.newHashMap(4, 7));
            System.out.println(actualMap);
        }
        Assert.assertTrue(result);
        // Assert.assertEquals(matchLength, matchLength, 0);
    }

    @Test
    public void 获取所有开始结束坐标_最大匹配_4() {
        SensitiveWordUtil.SENSITIVE_MAP.clear();
        Sets.newHashSet("123", "1234", "1234567").forEach(SensitiveWordUtil::addWordToMap);
        String text = "543512534123454312345673431232";
        Map<Integer, Integer> actualMap = SensitiveWordUtil.getAllWordBeginEnd(text, SensitiveWordUtil.MAX_MATCH_TYPE);

        Map<Integer, Integer> expectMap = LgrenUtil.newHashMap(9, 12, 16, 22, 26, 28);
        boolean result = false;
        for (Map.Entry<Integer, Integer> entry : actualMap.entrySet()) {
            result = Objects.equals(expectMap.remove(entry.getKey()), entry.getValue());
            if (!result) {
                break;
            }
        }
        result &= expectMap.isEmpty();
        if (!result) {
            System.out.println(LgrenUtil.newHashMap(9, 12, 16, 22, 26, 28));
            System.out.println(actualMap);
        }
        Assert.assertTrue(result);
        // Assert.assertEquals(matchLength, matchLength, 0);
    }

    @Test
    public void 检查文本_最小匹配_0() {
        SensitiveWordUtil.SENSITIVE_MAP.clear();
        Sets.newHashSet("test", "testt", "testttt").forEach(SensitiveWordUtil::addWordToMap);
        String text = "tes";
        int matchLength = SensitiveWordUtil.checkWord(text, SensitiveWordUtil.MIN_MATCH_TYPE);

        Assert.assertEquals(0, matchLength, 0);
    }

    @Test
    public void 检查文本_最小匹配_1() {
        SensitiveWordUtil.SENSITIVE_MAP.clear();
        Sets.newHashSet("test", "testt", "testttt").forEach(SensitiveWordUtil::addWordToMap);
        String text = "testtttttttttttt";
        int matchLength = SensitiveWordUtil.checkWord(text, SensitiveWordUtil.MIN_MATCH_TYPE);

        Assert.assertEquals(4, matchLength, 0);
    }

    @Test
    public void 检查文本_最小匹配_2() {
        SensitiveWordUtil.SENSITIVE_MAP.clear();
        Sets.newHashSet("test", "testt", "testttt").forEach(SensitiveWordUtil::addWordToMap);
        String text = "testtt";
        int matchLength = SensitiveWordUtil.checkWord(text, SensitiveWordUtil.MIN_MATCH_TYPE);

        Assert.assertEquals(4, matchLength, 0);
    }

    @Test
    public void 检查文本_最小匹配_3() {
        SensitiveWordUtil.SENSITIVE_MAP.clear();
        Sets.newHashSet("test", "testt", "testttt").forEach(SensitiveWordUtil::addWordToMap);
        String text = "vdvdtestssvtesvdsvdsttt";
        int matchLength = SensitiveWordUtil.checkWord(text, SensitiveWordUtil.MIN_MATCH_TYPE);

        Assert.assertEquals(4, matchLength, 0);
    }

    @Test
    public void 检查文本_最小匹配_4() {
        SensitiveWordUtil.SENSITIVE_MAP.clear();
        Sets.newHashSet("test", "testt", "testttt").forEach(SensitiveWordUtil::addWordToMap);
        String text = "vdvdtesssvtesvdsvdtestttttsttt";
        int matchLength = SensitiveWordUtil.checkWord(text, SensitiveWordUtil.MIN_MATCH_TYPE);

        Assert.assertEquals(4, matchLength, 0);
    }

    @Test
    public void 检查文本_最小匹配_5() {
        SensitiveWordUtil.SENSITIVE_MAP.clear();
        Sets.newHashSet("test", "sasr", "testttt").forEach(SensitiveWordUtil::addWordToMap);
        String text = "sdfhsdjfhdskfhdsk";
        int matchLength = SensitiveWordUtil.checkWord(text, SensitiveWordUtil.MIN_MATCH_TYPE);

        Assert.assertEquals(0, matchLength, 0);
    }

    @Test
    public void 检查文本_最小匹配_6() {
        SensitiveWordUtil.SENSITIVE_MAP.clear();
        Sets.newHashSet("t", "sasr", "testttt").forEach(SensitiveWordUtil::addWordToMap);
        String text = "t";
        int matchLength = SensitiveWordUtil.checkWord(text, SensitiveWordUtil.MIN_MATCH_TYPE);

        Assert.assertEquals(1, matchLength, 0);
    }

    @Test
    public void 检查文本_最小匹配_7() {
        SensitiveWordUtil.SENSITIVE_MAP.clear();
        Sets.newHashSet("123", "1234", "1234567").forEach(SensitiveWordUtil::addWordToMap);
        String text = "123456";
        int matchLength = SensitiveWordUtil.checkWord(text, SensitiveWordUtil.MIN_MATCH_TYPE);

        Assert.assertEquals(3, matchLength, 0);
    }

    @Test
    public void 检查文本_最大匹配_0() {
        SensitiveWordUtil.SENSITIVE_MAP.clear();
        Sets.newHashSet("test", "testt", "testttt").forEach(SensitiveWordUtil::addWordToMap);
        String text = "tes";
        int matchLength = SensitiveWordUtil.checkWord(text, SensitiveWordUtil.MAX_MATCH_TYPE);

        Assert.assertEquals(0, matchLength, 0);
    }

    @Test
    public void 检查文本_最大匹配_1() {
        SensitiveWordUtil.SENSITIVE_MAP.clear();
        Sets.newHashSet("test", "testt", "testttt").forEach(SensitiveWordUtil::addWordToMap);
        String text = "testtttttttttttt";
        int matchLength = SensitiveWordUtil.checkWord(text, SensitiveWordUtil.MAX_MATCH_TYPE);

        Assert.assertEquals(7, matchLength, 0);
    }

    @Test
    public void 检查文本_最大匹配_2() {
        SensitiveWordUtil.SENSITIVE_MAP.clear();
        Sets.newHashSet("test", "testt", "testttt").forEach(SensitiveWordUtil::addWordToMap);
        String text = "testtt";
        int matchLength = SensitiveWordUtil.checkWord(text, SensitiveWordUtil.MAX_MATCH_TYPE);

        Assert.assertEquals(5, matchLength, 0);
    }

    @Test
    public void 检查文本_最大匹配_3() {
        SensitiveWordUtil.SENSITIVE_MAP.clear();
        Sets.newHashSet("test", "testt", "testttt").forEach(SensitiveWordUtil::addWordToMap);
        String text = "vdvdtestssvtesvdsvdsttt";
        int matchLength = SensitiveWordUtil.checkWord(text, SensitiveWordUtil.MAX_MATCH_TYPE);

        Assert.assertEquals(4, matchLength, 0);
    }

    @Test
    public void 检查文本_最大匹配_4() {
        SensitiveWordUtil.SENSITIVE_MAP.clear();
        Sets.newHashSet("test", "testt", "testttt").forEach(SensitiveWordUtil::addWordToMap);
        String text = "vdvdtesssvtesvdsvdtestttttsttt";
        int matchLength = SensitiveWordUtil.checkWord(text, SensitiveWordUtil.MAX_MATCH_TYPE);

        Assert.assertEquals(7, matchLength, 0);
    }

    @Test
    public void 检查文本_最大匹配_5() {
        SensitiveWordUtil.SENSITIVE_MAP.clear();
        Sets.newHashSet("123", "1234", "1234567").forEach(SensitiveWordUtil::addWordToMap);
        String text = "123456";
        int matchLength = SensitiveWordUtil.checkWord(text, SensitiveWordUtil.MAX_MATCH_TYPE);

        Assert.assertEquals(4, matchLength, 0);
    }

    @Test
    public void 检查文本_最大匹配_6() {
        SensitiveWordUtil.SENSITIVE_MAP.clear();
        Sets.newHashSet("123", "1234", "1234567").forEach(SensitiveWordUtil::addWordToMap);
        String text = "12345678";
        int matchLength = SensitiveWordUtil.checkWord(text, SensitiveWordUtil.MAX_MATCH_TYPE);

        Assert.assertEquals(7, matchLength, 0);
    }

    @Test
    public void 检查文本_最大匹配_7() {
        SensitiveWordUtil.SENSITIVE_MAP.clear();
        Sets.newHashSet("123", "1234", "1234567").forEach(SensitiveWordUtil::addWordToMap);
        String text = "54351253412354312345673432`1234";
        int matchLength = SensitiveWordUtil.checkWord(text, SensitiveWordUtil.MAX_MATCH_TYPE);

        Assert.assertEquals(3, matchLength, 0);
    }
}
