package com.shop.itis.controller;

import com.shop.itis.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MailController {


    @Autowired
    MailService mailService;

    @RequestMapping("/mail")
    public String sendMail() {
        mailService.sendMail("ainurmullin@mail.ru", "Verify", "hello", "ainur6969@gmail.com");
        return "pages/catalog";
    }

}
