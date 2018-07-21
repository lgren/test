package com.lgren.简单工厂模式.a_普通;

public class MailSender implements Sender{

    @Override
    public void send() {
        System.out.println("This is mailSender class");
    }
}
