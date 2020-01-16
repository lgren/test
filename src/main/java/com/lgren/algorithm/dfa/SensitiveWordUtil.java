package com.lgren.algorithm.dfa;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * 敏感词工具类
 * @author lgren
 * @since 2019-10-24 15:27
 */
public class SensitiveWordUtil {
    public static final int MIN_MATCH_TYPE = 1; // 最小匹配规则
    public static final int MAX_MATCH_TYPE = 2; // 最大匹配规则

    private static final char MASK_CHAR = '*'; // 掩码

    private static final boolean IS_END = true; // 是最后
    private static final boolean NOT_END = false; // 不是最后
    private static final String IS_END_STR = "isEnd"; // 是否是最后的字段名

    private static final Map<Object, Object> SENSITIVE_MAP = new ConcurrentHashMap<>(4096); // 敏感词DFA处理后的Map

    /** 清除map */
    public static void clear() {
        SENSITIVE_MAP.clear();
    }

    /** 在map中增加词 */
    public static Map<Object, Object> addWordToMap(String keyword) {
        Map<Object, Object> currCharInfo = SENSITIVE_MAP;// 循环中的当前char的信息
        for (char currChar : keyword.toCharArray()) {
            @SuppressWarnings("unchecked")
            Map<Object, Object> findCharInfo = (Map<Object, Object>) currCharInfo.get(currChar);// 寻找的char的信息
            if (findCharInfo == null) {// 寻找的char的信息如果为空
                findCharInfo = new HashMap<>(4);
                currCharInfo.put(currChar, findCharInfo);// 将寻找的char信息加入当前层
            }
            currCharInfo = findCharInfo;
        }
        // 循环完毕后此map为最后一个char的属性map 将IS_END_STR设置为IS_END
        currCharInfo.put(IS_END_STR, IS_END);
        return SENSITIVE_MAP;
    }

    /** 将列表添加进敏感词 */
    public static void addListToMap(Iterable<String> strIte) {
        for (String word : strIte) {
            addWordToMap(word);
        }
    }

    /** 将列表添加进敏感词 */
    public static <T>void addListToMap(Iterable<T> iterable, Function<T, String> getWordFunc) {
        for (T obj : iterable) {
            addWordToMap(getWordFunc.apply(obj));
        }
    }

    /** 在map中移除词 */
    public static String removeWordInMap(String keyword) {
        Map<Object, Object> currCharInfo = SENSITIVE_MAP;// 循环中的当前char的信息
        char[] charArr = keyword.toCharArray();
        for (int i = 0, len = charArr.length; i < len; i++) {
            char currChar = charArr[i];
            @SuppressWarnings("unchecked")
            Map<Object, Object> findCharInfo = (Map<Object, Object>) currCharInfo.get(currChar);// 寻找的char的信息
            if (findCharInfo == null) {// 寻找的char的信息如果为空
                break;
            }
            if (i == len - 1) {
                Boolean isEnd = (Boolean)findCharInfo.get(IS_END_STR);
                if (isEnd != null && isEnd) {
                    currCharInfo.remove(currChar);
                }
            }
            currCharInfo = findCharInfo;
        }
        return keyword;
    }

    /**
     * 检查此文本中是否存在词
     * @param text 检查文本
     * @param matchType 匹配类型 MIN_MATCH_TYPE-最小匹配规则 MAX_MATCH_TYPE-最大匹配规则
     * @return 首次匹配到的词的长度 未匹配则返回0 例如: 匹配到了"我去" 则返回2
     */
    public static int checkWord(String text, int matchType) {
        int result = 0;
        int tempMatchLen = 0;// 最大匹配的时候此数依然会增加
        char[] charArr = text.toCharArray();
        Map<Object, Object> mapTemp = SENSITIVE_MAP; // 为了循环临时的map
        for (int i = 0, len = charArr.length; i < len; i++) {
            char currChar = charArr[i];
            @SuppressWarnings("unchecked")
            Map<Object, Object> currCharInfoMap = (Map<Object, Object>)mapTemp.get(currChar);
            if (currCharInfoMap != null) { // 当前字符的信息不为空(匹配到敏感词中的字符)
                tempMatchLen++; // 匹配到的字符长度+1
                Boolean isEnd = (Boolean)currCharInfoMap.get(IS_END_STR);
                if (isEnd != null ? isEnd : NOT_END) { // 如果已经是结束字符了 如果该字段不存在则默认为不是最后
                    result = tempMatchLen;
                    if (Objects.equals(matchType, MIN_MATCH_TYPE)) {// 最小匹配 结果
                        break;
                    }
                    // 没有匹配到最后字符 && 是最大匹配规则 && 已经匹配到了 && 是检查字符串的最后一个字符
                } else if (Objects.equals(matchType, MAX_MATCH_TYPE) && result != 0 && i == len - 1) {// 最大匹配结果
                    break;
                }
                mapTemp = currCharInfoMap;
                // 没有匹配到字符 && 是最大匹配 && 已经匹配到了
            } else {
                if (Objects.equals(matchType, MAX_MATCH_TYPE) && result != 0) {// 最大匹配结果
                    break;
                }
                tempMatchLen = 0;
                mapTemp = SENSITIVE_MAP;
            }
        }
        return result;
    }

    /**
     * 替换检查文本中所有匹配到的词
     * @param text 检查文本
     * @param matchType 匹配类型 MIN_MATCH_TYPE-最小匹配规则 MAX_MATCH_TYPE-最大匹配规则
     * @return 替换检查文本中所有匹配到的词后的结果
     */
    public static String replaceAllWord(String text, int matchType) {
        Map<Integer, Integer> allWordBeginEnd = getAllWordBeginEnd(text, matchType);

        char[] textCharArr = text.toCharArray();
        for (Map.Entry<Integer, Integer> entry : allWordBeginEnd.entrySet()) {
            for (int i = entry.getKey(); i <= entry.getValue(); i++) {
                textCharArr[i] = MASK_CHAR;
            }
        }
        return new String(textCharArr);
    }

    /**
     * 检查文本中所有的匹配到的词的开始, 结束坐标
     * @param text 检查文本
     * @param matchType 匹配类型 MIN_MATCH_TYPE-最小匹配规则 MAX_MATCH_TYPE-最大匹配规则
     * @return 文本中所有的匹配到的词的开始, 结束坐标
     */
    static Map<Integer, Integer> getAllWordBeginEnd(String text, int matchType) {
        Map<Integer, Integer> matchBeginEnd = new HashMap<>(16);

        int tempEndI = 0;
        int tempResult = 0;
        int tempMatchLen = 0;// 最大匹配的时候此数依然会增加
        char[] charArr = text.toCharArray();
        Map<Object, Object> mapTemp = SENSITIVE_MAP; // 为了循环临时的map
        for (int i = 0, len = charArr.length; i < len; i++) {
            char currChar = charArr[i];
            @SuppressWarnings("unchecked")
            Map<Object, Object> currCharInfoMap = (Map<Object, Object>)mapTemp.get(currChar);
            if (currCharInfoMap != null) { // 当前字符的信息不为空(匹配到敏感词中的字符)
                tempMatchLen++; // 匹配到的字符长度+1
                Boolean isEnd = (Boolean)currCharInfoMap.get(IS_END_STR);
                if (isEnd != null ? isEnd : NOT_END) { // 如果已经是结束字符了 如果该字段不存在则默认为不是最后
                    tempEndI = i;
                    tempResult = tempMatchLen;
                    if (Objects.equals(matchType, MIN_MATCH_TYPE)) {// 最小匹配结果
                        matchBeginEnd.put(i - tempResult + 1, i);
                        tempEndI = 0;
                        tempResult = 0;
                        tempMatchLen = 0;
                        mapTemp = SENSITIVE_MAP;
                        continue;
                    }
                    // 没有匹配到最后字符 && 是最大匹配规则 && 已经匹配到了 && 是检查字符串的最后一个字符
                } else if (Objects.equals(matchType, MAX_MATCH_TYPE) && tempResult != 0 && i == len - 1) {// 最大匹配结果
                    matchBeginEnd.put(tempEndI - tempResult + 1, tempEndI);
                }
                mapTemp = currCharInfoMap;
                // 没有匹配到字符 && 是最大匹配 && 已经匹配到了
            } else {
                if (Objects.equals(matchType, MAX_MATCH_TYPE) && tempResult != 0) {// 最大匹配结果
                    matchBeginEnd.put(tempEndI - tempResult + 1, tempEndI);
                }
                tempEndI = 0;
                tempResult = 0;
                tempMatchLen = 0;
                mapTemp = SENSITIVE_MAP;
            }
        }
        return matchBeginEnd;
    }
}
