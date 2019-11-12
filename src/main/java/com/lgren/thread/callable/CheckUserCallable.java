package com.lgren.thread.callable;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Callable 测试
 * @author Lgren
 * @create 2018-10-25 13:24
 **/
public class CheckUserCallable<T> implements Callable<Integer> {
    private List<String> userList;
    private Set<String> succList;
    private Map<String, String> failMap;
    public CheckUserCallable(List<String> userList, Set<String> succList, Map<String, String> failMap) {
        this.userList = userList;
        this.succList = succList;
        this.failMap = failMap;
    }

    @Override
    public Integer call() throws InterruptedException {
        int failMapSize = 0;
//        checkUserExecutor.submit();
        for (String userId : userList) {
            failMapSize++;
            TimeUnit.MILLISECONDS.sleep(35);
//            TimeUnit.SECONDS.sleep(50);
        }
        return failMapSize;
    }
}
