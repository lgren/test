package base;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * TODO
 * @author lgren
 * @since 2020-10-22 2:19 下午
 */
public class QueueTest {
    // 首先，我们来看看基于内存的队列。在Java的并发包中已经提供了BlockingQueue的实现，比较常用的有 ArrayBlockingQueue 和 LinkedBlockingQueue ，前者是以数组的形式存储，后者是以Node节点的链表形式存储。至于数组和链表的区别这里就不多说了。
    //
    // BlockingQueue 队列常用的操作方法:
    //
    //       1.往队列中添加元素: add(), put(), offer()
    //
    //       2.从队列中取出或者删除元素: remove() element()  peek()   poll()  take()
    //
    // 每个方法的说明如下：
    //
    //       offer()方法往队列添加元素如果队列已满直接返回false,队列未满则直接插入并返回true;
    //
    //       add()方法是对offer()方法的简单封装.如果队列已满,抛出异常new IllegalStateException("Queue full");
    //
    //        put()方法往队列里插入元素,如果队列已经满,则会一直等待直到队列为空插入新元素,或者线程被中断抛出异常.
    //
    //        remove()方法直接删除队头的元素:
    //
    //        peek()方法直接取出队头的元素,并不删除.
    //
    //        element()方法对peek方法进行简单封装,如果队头元素存在则取出并不删除,如果不存在抛出异常NoSuchElementException()
    //
    //        poll()方法取出并删除队头的元素,当队列为空,返回null;
    //
    //        take()方法取出并删除队头的元素,当队列为空,则会一直等待直到队列有新元素可以取出,或者线程被中断抛出异常
    //
    // 　　offer()方法一般跟pool()方法相对应, put()方法一般跟take()方法相对应.日常开发过程中offer()与pool()方法用的相对比较频繁.aa

    // offer():boolean  赋头 队列不满true, 满false
    // add():boolean    赋头 队列不满true, 满抛 IllegalStateException("Queue full")

    // element():T  取留头 队列空则抛 NoSuchElementException()
    // peek():T     取留头 队列空则返回null
    // remove():T   取删头 队列空则抛 NoSuchElementException()
    // poll():T     取删头 队列空则返回null
    @Test
    public void LinkedList() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        System.out.println(queue);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue);
    }
}
