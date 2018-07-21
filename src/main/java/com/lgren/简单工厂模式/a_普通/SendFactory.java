package com.lgren.简单工厂模式.a_普通;

public class SendFactory {

    public Sender product(String type) {
        if (type.toLowerCase().equals("mail")) {
            return new MailSender();
        } else if (type.toLowerCase().equals("sms")) {
            return new SmsSender();
        }
        return null;
    }
}
