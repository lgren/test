package work.jyyh.archives.db.sql;

import lombok.Data;
import org.junit.Test;

import java.util.*;
import java.util.function.BiConsumer;

/**
 * TODO
 * @author lgren
 * @since 2020-12-22 5:24 下午
 */
@Data
public class LTable<K, V> {
    private Item<K, V> data;

    public V put(V value, K... keyArr) {
        if (data == null) {
            data = new Item<>();
        }
        Item<K, V> tempItem = getLastItem(keyArr);
        V oldV = tempItem.getValue();
        tempItem.setValue(value);
        return oldV;
    }

    public V get(K... keyArr) {
        if (data == null) {
            return null;
        }
        Item<K, V> tempItem = data;
        for (K key : keyArr) {
            tempItem = Optional.ofNullable(tempItem.getValueMap()).map(m -> m.get(key)).orElse(null);
            if (tempItem == null) {
                return null;
            }
        }
        return tempItem.getValue();
    }

    public boolean containsValue(V value, K... keyArr) {
        if (data == null) {
            return false;
        }
        Item<K, V> item = getLastItem(keyArr);
        Map<K, Item<K, V>> valueMap = item.getValueMap();
        if (valueMap == null || valueMap.isEmpty()) {
            return false;
        }
        for (Map.Entry<K, Item<K, V>> itemEntry : valueMap.entrySet()) {
            if (Objects.equals(itemEntry.getValue().getValue(), value)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsKey(K key, K... keyArr) {
        if (data == null) {
            return false;
        }
        Item<K, V> item = getLastItem(keyArr);
        Map<K, Item<K, V>> valueMap = item.getValueMap();
        if (valueMap == null || valueMap.isEmpty()) {
            return false;
        }
        return valueMap.containsKey(key);
    }

    public void forEach(BiConsumer<K, Item<K, V>> action, K... keyArr) {
        if (data == null) {
            return;
        }
        Item<K, V> item = getLastItem(keyArr);
        item.forEach(action);
    }

    private Item<K, V> getLastItem(K... keyArr) {
        Item<K, V> tempItem = data;
        for (K key : keyArr) {
            tempItem = tempItem.getOrCreateValueMap().computeIfAbsent(key, k -> new Item<>());
        }
        return tempItem;
    }

    @Data
    static class Item<K, V> {
        private V value;
        private Map<K, Item<K, V>> valueMap;

        public Item() {
        }

        public V get(Object key) {
            return Optional.ofNullable(valueMap).map(m -> m.get(key)).map(Item::getValue).orElse(null);
        }

        public boolean isEmpty() {
            return Optional.ofNullable(valueMap).map(Map::isEmpty).orElse(false);
        }

        public Map<K, Item<K, V>> getOrCreateValueMap() {
            return valueMap = Optional.ofNullable(valueMap).orElseGet(LinkedHashMap::new);
        }

        public void forEach(BiConsumer<K, Item<K, V>> action) {
            Optional.ofNullable(valueMap).ifPresent(m -> m.forEach(action));
        }
    }

    // @Test
    // public void lTableTestSimple() {
    //     LTable<String, Integer> table = new LTable<>();
    //     table.put(1, "成都");
    //     table.put(2, "北京");
    //     table.put(3, "上海");
    //
    //     System.out.println(table.get("成都"));
    //     System.out.println(table.get("北京"));
    //     System.out.println(table.get("上海"));
    //     System.out.println(table.get("深圳"));
    //
    //     System.out.println(table.containsValue(1));
    //     System.out.println(table.containsValue(2));
    //     System.out.println(table.containsValue(3));
    //     System.out.println(table.containsValue(4));
    // }
    //
    // @Test
    // public void lTableTestComplex() {
    //     LTable<String, Integer> table = new LTable<>();
    //     table.put(1, "四川省", "成都市", "武侯区");
    //     table.put(2, "四川省", "成都市", "锦江区");
    //     table.put(3, "四川省", "成都市", "高新区");
    //
    //     System.out.println("get->" + table.get("四川省", "成都市", "武侯区"));
    //     System.out.println("get->" + table.get("四川省", "成都市", "锦江区"));
    //     System.out.println("get->" + table.get("四川省", "成都市", "高新区"));
    //     System.out.println("get->" + table.get("四川省", "成都市", "天府新区"));
    //     System.out.println("get->" + table.get("四川省", "成都市", "天府新区", "其他小区"));
    //     System.out.println("get->" + table.get("四川省", "成都市"));
    //     System.out.println("get->" + table.get("四川省"));
    //
    //     System.out.println("containsValue->" + table.containsValue(1, "四川省", "成都市"));
    //     System.out.println("containsValue->" + table.containsValue(2, "四川省", "成都市"));
    //     System.out.println("containsValue->" + table.containsValue(3, "四川省", "成都市"));
    //     System.out.println("containsValue->" + table.containsValue(4, "四川省", "成都市"));
    //
    //     System.out.println("containsKey->" + table.containsKey("武侯区", "四川省", "成都市"));
    //     System.out.println("containsKey->" + table.containsKey("锦江区", "四川省", "成都市"));
    //     System.out.println("containsKey->" + table.containsKey("高新区", "四川省", "成都市"));
    //     System.out.println("containsKey->" + table.containsKey("天府新区", "四川省", "成都市"));
    //     System.out.println("containsKey->" + table.containsKey("其他小区", "四川省", "成都市", "天府新区"));
    //     System.out.println("containsKey->" + table.containsKey("成都市", "四川省"));
    //     System.out.println("containsKey->" + table.containsKey("四川省"));
    //
    // }
}

