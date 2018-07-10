package com.lgren.简单工厂模式.a_普通;

public class SmsSender implements Sender {

    @Override
    public void sender() {
        System.out.println("This is smsSender class");
    }
}
