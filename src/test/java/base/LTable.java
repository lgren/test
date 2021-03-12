package base;

import lombok.experimental.Delegate;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * TODO
 * @author lgren
 * @since 2020-12-25 11:19 上午
 */
public class LTable<K, V> {
    @Delegate
    private final Map<K, V> map;

    public LTable() {
        this.map = new HashMap<>();
    }

    @Test
    public void test1() {
        LTable<String, String> lTable = new LTable<>();
        lTable.put("test", "test");
        System.out.println(lTable.get("test"));

    }

    static class Item<K, V> {
        private K key;
        private V value;
        @Delegate
        private final Map<K, Item<K, V>> childrenMap;

        public Item(Supplier<Map<K, Item<K, V>>> childrenMapFunc) {
            this.childrenMap = childrenMapFunc.get();
        }
    }
}
