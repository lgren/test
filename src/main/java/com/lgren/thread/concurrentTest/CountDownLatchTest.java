package com.lgren.thread.concurrentTest;

import org.apache.commons.lang3.time.DateUtils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 计数线程
 * 比如赛跑比赛 一个CountDownLatch(1) wait 作为开始信号. 一个CountDownLatch(5) run 作为运动员起跑
 * 1.所有运动员 wait.await();// 等待枪响
 * 2.wait.countDown();// 枪响 比赛开始!
 * 3.run.countDown();// 到达终点.
 * 4.run.await();// 当到达重点数量达到5个(run.countDown()执行5次),比赛结束
 * @author lgren
 * @since 2019-09-10 16:19
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch wait = new CountDownLatch(1);// 类似裁判开始信号 wait.await();是运动员等待枪响 wait.countDown();运动员开始
        CountDownLatch run = new CountDownLatch(5);// 5个运动员
        ExecutorService exe = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            exe.execute(() -> {// 给每个运动员先准备好
                try {
                    wait.await();// 等待枪响
                } catch (InterruptedException ignored) { }
                System.out.println("我开始跑了");
                long runTime = ThreadLocalRandom.current().nextLong(1000, 3000);
                try {
                    Thread.sleep(runTime);
                } catch (InterruptedException ignored) { }
                run.countDown();// 到达终点
                System.out.printf("我跑完了%d毫秒%n", runTime);
            });
        }
        System.out.println("比赛开始!");
        wait.countDown();// 比赛开始!
        run.await();// 等待到比赛结束
        System.out.println("比赛结束!");

    }
}
