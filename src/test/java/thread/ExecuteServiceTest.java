package thread;

import org.junit.Test;

import java.util.concurrent.*;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;

/**
 * TODO
 * @author lgren
 * @since 2020-06-28 3:50 下午
 */
public class ExecuteServiceTest {
    @Test
    public void name1() {
        Executors.newFixedThreadPool(10);
        new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), (r, e)->{});
    }

    @Test
    public void name2() throws InterruptedException {
        ScheduledThreadPoolExecutor scheduled = new ScheduledThreadPoolExecutor(2);
        ScheduledFuture<?> future = scheduled.scheduleAtFixedRate(() -> System.out.println("一次"), 1, 1, TimeUnit.SECONDS);//0表示首次执行任务的延迟时间，40表示每次执行任务的间隔时间，TimeUnit.MILLISECONDS执行的时间间隔数值单位
        Thread.sleep(1000000);
    }
}
