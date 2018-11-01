package test;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;

/**
 * TODO
 *
 * @author Lgren
 * @create 2018-10-12 10:40
 **/
public class 项目测试 {
    @Test
    public void 读取txt测试() throws IOException {
//        String name = "D:/userId.txt";
//        InputStream in = new FileInputStream(name);
//        Set<String> stringList = new HashSet<>(32);
//        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//        String lineStr;
//        while ((lineStr = reader.readLine()) != null) {
//            stringList.add(lineStr.trim());
//        }
//        stringList.remove(null);
//        System.out.println(stringList);

//        System.out.println(4/3);an

//        List<String> stringList = Files.readAllLines(new File(in).toPath());
//        System.out.println(stringList);
//        list2.add(null);
        "sd".substring(0, 0);

    }
    @Test
    public void 发生大事() {
        List<String> list = Lists.newArrayList("one", "two", "three", "four", "five", "six");
//        forEach(list, (i, str) -> {
//            System.out.println(str);
//        });
        Map<String, Integer> map = ImmutableMap.of("key1", 1, "key2", 2, "key3", 3);
        forEach(map, (k,v,i) -> System.out.println("index:" + i + "  key:" + k + "  value:" + v));
    }

//    private <E> void forEach(Iterable<? extends E> elements, BiConsumer<Integer, ? super E> action) {
//        Objects.requireNonNull(elements);
//        Objects.requireNonNull(action);
//
//        int index = 0;
//        for (E element : elements) {
//            action.accept(index++, element);
//        }
//    }

    private <K,V> void forEach(Map<K, V> map, ThiConsumer<? super K, ? super V, Integer> action) {
        Objects.requireNonNull(map);
        Objects.requireNonNull(action);
        int index = 0;
        for (Map.Entry<K, V> entry : map.entrySet()) {
            action.accept(entry.getKey(), entry.getValue(), index++);
        }
    }
    @FunctionalInterface
    interface ThiConsumer<T,U,W>{
        void accept(T t, U u, W w);

        default ThiConsumer<T,U,W> andThen(ThiConsumer<? super T,? super U,? super W> consumer){
            return (t, u, w)->{
                accept(t, u, w);
                consumer.accept(t, u, w);
            };
        }
    }

    private <K,V> void forEach(Map<K, V> map, BiConsumer<? super K, ? super V> action) {
        Objects.requireNonNull(map);
        Objects.requireNonNull(action);
        for (Map.Entry<K, V> entry : map.entrySet()) {
            action.accept(entry.getKey(), entry.getValue());
        }
    }
}
