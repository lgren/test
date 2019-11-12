package com.lgren.thread.test;

import com.lgren.util.LgrenUtil;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.*;

/**
 * TODO
 * @author lgren
 * @since 2019-09-24 14:32
 */
public class GetUserInfoFromService {
    @Test
    public void test1() {
        GetUserInfoFromService getUserInfoFromService = new GetUserInfoFromService();
        Map<String, Map<String, Object>> userMap = new ConcurrentHashMap<>();
        Set<String> failUserIdOfSet = new CopyOnWriteArraySet<>();


        ExecutorService exe = new ThreadPoolExecutor(0, 1000, 10, TimeUnit.SECONDS, new SynchronousQueue<>());
        List<Map<String, Object>> data = getUserInfoFromService.getData(100);
        for (Map<String, Object> dataMap : data) {
            exe.execute(() -> {
                Map<String, Object> userInfo = getUserInfoFromService.getUserInfo(String.valueOf(dataMap.get("userId")));
                dataMap.put("name", userInfo.get("name"));
                userMap.put(String.valueOf(userInfo.get("id")), userInfo);
                failUserIdOfSet.add(String.valueOf(userInfo.get("id")));
            });
        }

        exe.shutdown();
        try {
            exe.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            System.out.println();
        }
        System.out.println(data);
    }

    public List<Map<String, Object>> getData(int dataNum) {
        List<Map<String, Object>> result = new LinkedList<>();
        for (int i = 0; i < dataNum; i++) {
            result.add(LgrenUtil.newHashMap("id", UUID.randomUUID().toString(),
                    "userId", UUID.randomUUID().toString()));
        }
        return result;
    }

    public Map<String, Object> getUserInfo(String userId) {
        try {
            Thread.sleep(500L);
        } catch (InterruptedException ignored) {
        }
        return LgrenUtil.newHashMap("id", UUID.randomUUID().toString(),
                "name", "名字叫做" + ThreadLocalRandom.current().nextInt(0, 1000));
    }
}
