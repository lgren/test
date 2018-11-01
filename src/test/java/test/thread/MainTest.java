package test.thread;

import com.github.pagehelper.PageInfo;
import com.lgren.util.LgrenUtil;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.*;

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
        return Collections.unmodifiableList(numbers);
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

    @Test
    public void test1() {
        long start = System.currentTimeMillis();

        List<String> list = buildIntRange();
        start = System.currentTimeMillis();
        Set<String> succList = Collections.synchronizedSet(new HashSet<>(list.size()));
        Map<String, String> failMap = new ConcurrentHashMap<>(list.size() / 3);
        int failMapSize = 0;
        ExecutorService checkUserExecutor = Executors.newCachedThreadPool();
        int codeNum = 10000;
        int getPageNum = Math.max(list.size() / codeNum, 10);
        List<Future<Integer>> checkResultList = new ArrayList<>(codeNum);
        int pageNum = 1;
        PageInfo<String> pageUser;
        do {
            pageUser = LgrenUtil.pageInfoForList(list, pageNum, getPageNum);
            List<String> checkUserList = pageUser.getList();
            pageNum++;
            checkResultList.add(checkUserExecutor.submit(() -> {
                int failMapSizeVar = 0;
                for (String userId : checkUserList) {
                    failMapSizeVar++;
                    TimeUnit.MILLISECONDS.sleep(35);
                }
                return failMapSizeVar;
            }));
        } while (pageUser.isHasNextPage());

        for (Future<Integer> integerFuture : checkResultList) {
            try {
                failMapSize += integerFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("parallelStream : " + (System.currentTimeMillis() - start) + "ms");
        System.out.println("number:" + failMapSize);

    }
}
