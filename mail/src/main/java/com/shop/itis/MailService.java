package com.shop.itis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {

    private final String myMail = "ainur6969@gmail.com";

    @Autowired
    JavaMailSenderImpl javaMailSender;


    public void sendMail(String to, String subject, String body) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, false);
            helper.setFrom(myMail);
            helper.setTo(to);
            helper.setText(body, true);
            helper.setSubject(subject);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        try {
            javaMailSender.send(message);
            System.out.println("mail sended to email " + to);
        } catch (MailException e) {
            System.out.println("can't connect to internet");
        }
    }

}
