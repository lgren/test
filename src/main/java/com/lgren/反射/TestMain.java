package com.lgren.反射;

import org.junit.Test;

import java.lang.reflect.Field;

/**
 * TODO
 * @create 2019-06-24 17:35
 * @since lgren
 */
public class TestMain {
    @Test
    public void test1() throws NoSuchFieldException {
        Field id = TestClass.class.getField("id");
        System.out.println();
    }
}
