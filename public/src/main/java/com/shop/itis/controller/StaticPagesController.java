package com.shop.itis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StaticPagesController {

    @RequestMapping("/contacts")
    public String contactsPage(ModelMap map) {
        map.put("info", "Наши контакты");
        return "pages/contacts";
    }
}
