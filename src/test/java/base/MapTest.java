package base;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * map 测试
 * @author lgren
 * @since 2020-06-18 10:25 上午
 */
public class MapTest {
    @Test
    public void 删除指定项() {
        Map<String, String> map = new HashMap<>(6);
        map.put("a", "a");
        map.put("aa", "aa");
        map.put("aaa", "aaa");
        map.put("aaaa", "aaaa");

    }
}
