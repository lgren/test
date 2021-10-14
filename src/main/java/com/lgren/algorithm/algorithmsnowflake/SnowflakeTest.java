package com.lgren.algorithm.algorithmsnowflake;

import com.lgren.algorithm.algorithmsnowflake.Snowflake;
import org.junit.Test;

import java.time.*;

public class SnowflakeTest {
    @Test
    public void name() {
        Snowflake idWorker = new Snowflake(0, 0);
        long id = idWorker.nextId();
        System.out.println(id);
        System.out.println(Long.toBinaryString(id));
    }

    @Test
    public void name1() {
        Snowflake idWorker = new Snowflake(0, 0);
        Snowflake idWorker01 = new Snowflake(0, 1);
        Snowflake idWorker02 = new Snowflake(0, 2);
        Snowflake idWorker03 = new Snowflake(0, 3);
        Snowflake idWorker13 = new Snowflake(1, 3);
        Snowflake idWorker23 = new Snowflake(2, 3);
        Snowflake idWorker33 = new Snowflake(3, 3);
        System.out.println(Long.toBinaryString(idWorker.nextId()));
        System.out.println(Long.toBinaryString(idWorker01.nextId()));
        System.out.println(Long.toBinaryString(idWorker02.nextId()));
        System.out.println(Long.toBinaryString(idWorker03.nextId()));
        System.out.println(Long.toBinaryString(idWorker13.nextId()));
        System.out.println(Long.toBinaryString(idWorker23.nextId()));
        System.out.println(Long.toBinaryString(idWorker33.nextId()));
    }

    @Test
    public void name2() {
        // DateUtil.format(new Date(1420041600000L), "")
        Instant instant = Instant.ofEpochMilli(1420041600000L);
        System.out.println(Long.toBinaryString(1420041600000L));
        long now = System.currentTimeMillis();
        System.out.println(now);
        System.out.println(Long.toBinaryString(now));
        // System.out.println(LocalDateTime.ofInstant(Instant.ofEpochMilli(1420041600000L)));
    }

    @Test
    public void name3() {
        System.out.println(Long.MAX_VALUE);
        System.out.println(Long.toBinaryString(Long.MAX_VALUE));
        System.out.println(1L << 12L);
        System.out.println(-1L << 12L);
        System.out.println(~(-1L << 12L));
        System.out.println(Long.toBinaryString(1L << 12L));
        System.out.println(Long.toBinaryString(-1L << 12L));
        System.out.println(Long.toBinaryString(~(-1L << 12L)));
    }

}
