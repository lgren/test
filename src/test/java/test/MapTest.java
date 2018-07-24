package test;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MapTest {
    @Test
    public void UsuallyMap() {
        Long nowTime = System.currentTimeMillis();
        Map<String, String> map;
        for (int i = 0; i < 1_0000_0000; i++) {
            map = new HashMap<>();
            map.put("one","one");
            map.put("two","two");
            map.put("tree","tree");
            map.put("four","four");
        }
        System.out.println("用时:" + (System.currentTimeMillis() - nowTime));
    }

//    @Test
    public void AnotherMap() {
        Long nowTime = System.currentTimeMillis();
        Map map;
        for (int i = 0; i < 1_0000_0000; i++) {
            map = new HashMap(){{
                put("one","one");
                put("two","two");
                put("tree","tree");
                put("four","four");
            }};
        }
        System.out.println("用时:" + (System.currentTimeMillis() - nowTime));
    }

    @Test
    public void test1() {
        for (int i = 0; i < 5; i++) {
            AnotherMap();
        }
    }
    @Test
    public void test2() {
        for (int i = 0; i < 5; i++) {
            UsuallyMap();
        }
    }

}