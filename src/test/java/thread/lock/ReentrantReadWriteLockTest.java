package thread.lock;

import cn.hutool.core.lang.Console;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * TODO
 * @author lgren
 * @since 2020-06-29 9:34 上午
 */
public class ReentrantReadWriteLockTest {
    Map<Integer, Integer> data = new HashMap<>();
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public Integer get(Integer k) {
        lock.readLock().lock();
        try {
            Console.log("读取这一次 k: {}", k);
            Thread.sleep(3000);
            return data.get(k);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.readLock().unlock();
        }
    }

    public Integer put(Integer k, Integer v) {
        lock.writeLock().lock();
        try {
            Console.log("写入这一次 k: {} v: {}", k, v);
            Thread.sleep(3000);
            return data.put(k, v);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Test
    public void read() throws InterruptedException {
        ExecutorService exe = Executors.newFixedThreadPool(4);
        exe.submit(() -> { get(1); });
        exe.submit(() -> { get(2); });
        exe.submit(() -> { get(3); });
        exe.submit(() -> { get(4); });
        exe.shutdown();
        exe.awaitTermination(1, TimeUnit.MINUTES);
        System.out.println("OK");
    }

    @Test
    public void write() throws InterruptedException {
        ExecutorService exe = Executors.newFixedThreadPool(4);
        exe.submit(() -> { put(1, 1); });
        exe.submit(() -> { put(2, 2); });
        exe.submit(() -> { put(3, 3); });
        exe.submit(() -> { put(4, 4); });
        exe.shutdown();
        exe.awaitTermination(1, TimeUnit.MINUTES);
        System.out.println("OK");
    }
}
