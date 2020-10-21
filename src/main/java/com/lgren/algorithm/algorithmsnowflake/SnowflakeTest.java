package com.lgren.algorithm.algorithmsnowflake;

/**
 * TODO
 * @author lgren
 * @since 2020-07-07 3:28 下午
 */
public class SnowflakeTest {
    public static void main(String[] args) {
        // 1_0000_0000_0000
        // 0_0000_0000_0001
        System.out.println(~(-1L << 2L));
        System.out.println(1L << 2L);
        System.out.println(~(1L << 2L));
        System.out.println(1L ^ (1L << 2L));
        System.out.println(-1L ^ (-1L << 12L));
        System.out.println((1L << 12L));
    }


}
