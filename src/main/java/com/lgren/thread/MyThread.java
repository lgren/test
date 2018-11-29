package com.lgren.thread;

/**
 * 继承 Thread创建线程
 * @author Lgren
 * @create 2018-11-01 14:16
 **/
public class MyThread extends Thread {
    public MyThread() {
    }

    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println("this is" + this.getName());
    }
}
