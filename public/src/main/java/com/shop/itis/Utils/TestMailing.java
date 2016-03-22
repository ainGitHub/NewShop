package com.shop.itis.Utils;

import com.shop.itis.MailService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class TestMailing {
    public static void main(String[] args) {

        ApplicationContext context = new FileSystemXmlApplicationContext("WEB-INF/spring/ApplicationContext.xml");

        //Get the mailer instance
        MailService mailer = (MailService) context.getBean("mailService");

        //Send a composed mail
        mailer.sendMail("ainurmullin@mail.ru", "Test Subject", "Testing body", "ainur6969@gmail.com");

    }
}
