package base;

import org.junit.Test;

import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * TODO
 * @author lgren
 * @since 2021-01-06 5:07 下午
 */
public class SortTest {
    private final static Comparator<String> TRANSLATE_COMPARATOR = (o1, o2) -> o2.length() > o1.length() ? 1 : Objects.equals(o2.length(), o1.length()) ? o2.compareTo(o1) : -1;
    // private final static Comparator<String> TRANSLATE_COMPARATOR = (o1, o2) -> o2.length() > o1.length() ? 1 : Objects.equals(o2.length(), o1.length()) ? -1 : -1;

    @Test
    public void name1() {
        Map<String, Integer> map = new TreeMap<>(TRANSLATE_COMPARATOR);
        map.put("111", 1);
        map.put("1111", 2);
        map.put("11111", 3);
        map.put("11211", 4);
        map.put("12111", 5);
        map.forEach((k, v) -> System.out.printf("%s->%s\n", k, map.get(k)));
    }


    @Test
    public void name2() {
        int a = 10;
        int b = 10;
        if (a < 100) {
            System.out.println("a");
        } else if (a < 1000) {
            System.out.println("1000");
        } else if (a > 100) {
            System.out.println("qewr");
        } else if (b < 100) {
            System.out.println("b");
        }
    }
}
