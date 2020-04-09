package thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * TODO
 * @author lgren
 * @since 2020-04-08 2:38 下午
 */
public class VolatileTest {
    private volatile int inc = 0;
    private volatile AtomicInteger anInt = new AtomicInteger();

    public void increase() {
        this.inc++;
        anInt.getAndIncrement();
    }

    public static void main(String[] args) {
        final VolatileTest test = new VolatileTest();
        for(int i=0;i<10;i++){
            new Thread(() -> {
                for(int j=0;j<1000;j++)
                    test.increase();
            }).start();
        }

        while(Thread.activeCount()>1)  //保证前面的线程都执行完
            Thread.yield();
        System.out.println(test.inc);
    }
}
