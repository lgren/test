package com.lgren.java8;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 流测试
 * @author lgren
 * @since 2019-11-22 09:45
 */
public class StreamTest {
    @Test
    public void test1() {
        ArrayList<ImmutableMap<String, String>> list = Lists.newArrayList(
                ImmutableMap.of("type", "01", "content", "content1"),
                ImmutableMap.of("type", "02", "content", "content2"),
                ImmutableMap.of("type", "03", "content", "content3"),
                ImmutableMap.of("type", "04", "content", "content4"),
                ImmutableMap.of("type", "01", "content", "content5"),
                ImmutableMap.of("type", "01", "content", "content6"),
                ImmutableMap.of("type", "03", "content", "content7"),
                ImmutableMap.of("type", "02", "content", "content8"),
                ImmutableMap.of("type", "04", "content", "content9")
        );
        // {title: '政策法规', type: '01', content: tempMap['01'], on: 'on'},
        // {title: '业务规范', type: '02', content: tempMap['02']},
        // {title: '办事指南', type: '03', content: tempMap['03']},
        // {title: '操作手册', type: '04', content: tempMap['04']},
        Map<String, List<ImmutableMap<String, String>>> result = list.stream()
                .collect(Collectors.groupingBy(m -> m.get("type"), () -> {
                    Map<String, List<ImmutableMap<String, String>>> map = new HashMap<>();
                    return map;
                }, Collectors.toList()));

        Map<String, List<ImmutableMap<String, String>>> result1 = list.stream()
                .collect(Collectors.groupingBy(m -> m.get("type"), HashMap::new, Collectors.toList()));
        System.out.println();
    }

    @Test
    public void name1() throws FileNotFoundException {
        int[] arr = new int[]{3, 4, 5, 6};
    }
}