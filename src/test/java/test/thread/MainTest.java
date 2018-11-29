package test.thread;

import com.lgren.util.LgrenUtil;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TODO
 *
 * @author Lgren
 * @create 2018-10-27 14:19
 **/
public class MainTest {
    public static List<String> buildIntRange() {
        List<String> numbers = new ArrayList<>(5);
        for (int i = 0; i < 10_0000; i++)
            numbers.add(i + "");
//        return Collections.unmodifiableList(numbers);
        return numbers;
    }

    @Test
    public void test() {
        List<String> source = buildIntRange();
        // 多管道parallelStream
        long start = System.currentTimeMillis();
        source.parallelStream().forEach(r -> {
            try {
                TimeUnit.MILLISECONDS.sleep(35);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        System.out.println("parallelStream : " + (System.currentTimeMillis() - start) + "ms");
        System.out.println("number:" + source.size());

    }
    private Boolean 模拟等待接口(String value) throws InterruptedException {
//        Random random = new Random();
        TimeUnit.MILLISECONDS.sleep(50);
        return value.indexOf("1") > 0;
    }

    @Test
    public void 多线程复杂测试() {
        ExecutorService checkUserExecutor = Executors.newCachedThreadPool();
        List<String> list = buildIntRange();
        for (int i = 0; i < 2000 ; i++) {
            list.add("12000");
        }
        Set<String> testList = ConcurrentHashMap.newKeySet();

        long start = System.currentTimeMillis();
        int codeNum = 10000;// 计划将一个list分割成多少段
        int getPageSize = Math.max(list.size() / codeNum, 10);// 每段最少10条数据
        List<Future<Integer>> checkResultList = new ArrayList<>(codeNum);
        AtomicInteger x = new AtomicInteger();
        LgrenUtil.partition(list, getPageSize).forEach(listVar ->
            checkResultList.add(checkUserExecutor.submit(() -> {
                x.getAndIncrement();
                int failNumVar = 0;
                for (String value : listVar) {
                    if (testList.contains(value)) {
//                        failNumVar++;
                    } else {
                        if (模拟等待接口(value)) {
                            testList.add(value);
                            failNumVar++;
                        }
                    }
                }
                return failNumVar;
            }))
        );

        int failNum = 0;
        for (Future<Integer> integerFuture : checkResultList) {
            try {
                failNum += integerFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("parallelStream : " + (System.currentTimeMillis() - start) + "ms");
        System.out.println("number:" + failNum + "->" + testList.size());

    }

    @Test
    public void test2() {

    }
}
