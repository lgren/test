package test;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.io.IOException;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

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

    @Test
    public void oneResult() {
//        String result = "AA";
        // 第一组 :0 -> 一:1 二:2 三:4 四:8 五:16
        // 第二组 :1 -> 东:1 南:2 西:4 北:8 中:16

        Integer one = 1 + 2 + 16;
        Integer two = 4 + 8 + 16;
        Map<Integer, Character> charMap = toCharArr(one, two);

        Map<Character, List<Integer>> intArrMap = toIntArr(charMap.values());
        toCharArr(intArrMap.values().stream().map(intList -> intList.stream().mapToInt(o -> o).sum()).collect(Collectors.toList()));
        System.out.println();
    }
    private char toChar(int numVar) {
        return numVar < 10 ? ((char) (numVar + 48)) : numVar < 36 ? ((char) (numVar + 55)) : numVar < 62 ? ((char) (numVar + 61)) : ((char) (numVar + 19906));
    }
    private Map<Integer, Character> toCharArr(int... numArr) {
        Map<Integer, Character> resultMap = new HashMap<>(numArr.length);
        for (int aNumArr : numArr) {
            resultMap.put(aNumArr, toChar(aNumArr));
        }
        return resultMap;
    }
    private Map<Integer, Character> toCharArr(Collection<Integer> numArr) {
        Map<Integer, Character> resultMap = new HashMap<>(numArr.size());
        for (int aNumArr : numArr) {
            resultMap.put(aNumArr, toChar(aNumArr));
        }
        return resultMap;
    }

    private int toInt(char charVar) {
        return charVar < 58 ? charVar - 48 : charVar < 91 ? charVar - 55 : charVar < 123 ? charVar - 61 : charVar - 19906;
    }
    private List<Integer> parseChar(int intVar) {
        int miNum = 4;
        List<Integer> resultList = new ArrayList<>(5);
        int intVarVar = intVar;
        do {
            int num = 1 << miNum;
            if (intVarVar >= num) {
                intVarVar -= num;
                resultList.add(num);
                if (intVarVar == num) break;
            }
            miNum--;
        } while (intVarVar > 0);
        return resultList;
    }
    private Map<Character, List<Integer>> toIntArr(Collection<Character> charArr) {
        Map<Character, List<Integer>> resultMap = new HashMap<>(charArr.size());
        for (char charVar : charArr) {
            resultMap.put(charVar, parseChar(toInt(charVar)));
        }
        return resultMap;
    }
}
