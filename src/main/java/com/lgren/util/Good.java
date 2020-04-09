package com.lgren.util;

import java.io.File;
import java.util.Optional;

/**
 * 一些比较好的写法
 * @author lgren
 * @since 2020-03-11 11:30 上午
 */
public class Good {
    public static void generateFolder(String path) {
        Optional.of(new File(path)).filter(f -> !f.exists()).ifPresent(File::mkdirs);
    }
}
