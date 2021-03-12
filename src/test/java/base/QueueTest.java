package base;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * TODO
 * @author lgren
 * @since 2020-10-22 2:19 下午
 */
public class QueueTest {
    @Test
    public void LinkedList() {
        Queue<Integer> queue = new ArrayDeque<>(2);
        queue.add(1);
        queue.add(1);
        queue.add(1);
        queue.add(1);
        queue.add(1);
        System.out.println(queue);
        System.out.println(queue);
    }
}
