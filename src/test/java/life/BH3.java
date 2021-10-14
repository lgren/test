package life;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.SneakyThrows;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class BH3 {
    double[] rates = {2.479, 1.24, 1.24, 1.24};// 百分比

    @Test
    public void run() {
        System.out.println(10000_0000D / statistics(0, 10000_0000, 0).get("s"));
    }

    private Map<String, Integer> statistics(int id, int num, int wait) {
        return drawCard(id, num, wait).stream().collect(Collectors.groupingBy(s -> s, Collectors.reducing(0, e -> 1, Integer::sum)));
    }

    private final Map<Integer, AtomicInteger[]> map = new ConcurrentHashMap<>();
    ThreadLocalRandom random = RandomUtil.getRandom();

    @SneakyThrows
    private List<String> drawCard(int id, int num, int wait) {
        AtomicInteger[] nowNumArr = map.computeIfAbsent(id, i -> new AtomicInteger[]{new AtomicInteger(), new AtomicInteger()});

        List<String> resultList = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            int nowANum = nowNumArr[0].incrementAndGet();
            int nowNum = nowNumArr[1].incrementAndGet();
            if (nowANum % 100 == 0) {
                resultList.add("s");
                nowNumArr[1].set(0);
            } /*else if (nowANum % 50 == 0) {
                resultList.add("s");
            } else if (nowANum % 30 == 0) {
                resultList.add("s");
            }else if (nowNum == 10) {
                resultList.add("s");
                nowNumArr[1].set(0);
            } */ else {
                int result = random.nextInt(0, 1000);
                if (result < 15) {
                    resultList.add("s");
                    nowNumArr[1].set(0);
                } else if (result < 200) {
                    resultList.add("a");
                } else {
                    resultList.add("b");
                }
            }
            if (wait > 0) {
                TimeUnit.SECONDS.sleep(wait);
            }
        }
        return resultList;
    }

    // private String getCard(int cardPackageId) {
    //
    // }

    // private final Map<Integer, Map<Integer, String>> cardPackageMap = MapUtil.<Integer, Map<Integer, String>>builder()
    //         .put(1, MapUtil.<Integer, String>builder()
    //                 .put(1, )
    //                 .build())
    //         .build();

}
