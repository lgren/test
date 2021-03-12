package thread;

import org.junit.Test;

import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.stream.IntStream;

/**
 * TODO
 * @author lgren
 * @since 2020-06-28 3:50 下午
 */
public class ExecuteServiceTest {
    @Test
    public void all() {
        // FixedThreadPool 和 SingleThreadPool: 允许的请求队列长度为 Integer.MAX_VALUE，可能会堆积大量的请求，从而导致 OOM。
        Executors.newFixedThreadPool(10);
        new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());

        Executors.newSingleThreadExecutor();

        // CachedThreadPool和 ScheduledThreadPool: 允许的创建线程数量为 Integer.MAX_VALUE，可能会创建大量的线程，从而导致 OOM。
        Executors.newCachedThreadPool();
        new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<>());

        Executors.newScheduledThreadPool(3);


    }

    /**
     * - 池中线程数量固定，不会发生变化
     * - 使用无界的LinkedBlockingQueue，要综合考虑生成与消费能力，生成过剩，可能导致堆内存溢出。
     * - 适用一些很稳定很固定的正规并发线程，多用于服务器
     */
    @Test
    public void newFixedThreadPool() {
        Executors.newFixedThreadPool(10);
        // 类比如下
        new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
    }

    /**
     * - 池中线程时随着处理数据增加而增加
     * - 线程数并不是一直增加，如果有新任务需要执行时，首先查询池中是否有空闲线程并且还为到空闲截止时间，如果有，则使用空闲线程，如果没有，则创建新线程并放入池中。
     * - 用于执行一些生存期很短的异步型任务。不适用于IO等长延时操作，因为这可能会创建大量线程，导致系统崩溃。
     * - 使用SynchronousQueue作为阻塞队列，如果有新任务进入队列，必须队列中数据被其他线程处理，否则会等待。
     */
    @Test
    public void newCachedThreadPool() {
        Executors.newCachedThreadPool();
        // 类比如下
        new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<>());
    }

    @Test
    public void queueTest() {
        Queue<Integer> queue = new LinkedBlockingQueue<>(10);
        IntStream.range(0, 10).forEach(queue::add);
        System.out.println(queue);
    }

    @Test
    public void test1() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(4), new ThreadPoolExecutor.AbortPolicy());
        IntStream.range(0, 10_0000).forEach(i -> executor.submit(() -> {
            System.out.println(i);
        }));


    }

    @Test
    public void name2() throws InterruptedException {
        ScheduledThreadPoolExecutor scheduled = new ScheduledThreadPoolExecutor(2);
        ScheduledFuture<?> future = scheduled.scheduleAtFixedRate(() -> System.out.println("一次"), 1, 1, TimeUnit.SECONDS);//0表示首次执行任务的延迟时间，40表示每次执行任务的间隔时间，TimeUnit.MILLISECONDS执行的时间间隔数值单位
        Thread.sleep(1000000);
    }

}
