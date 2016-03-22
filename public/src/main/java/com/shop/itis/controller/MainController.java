package com.shop.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {
    @Autowired
    HttpServletRequest request;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainPage(ModelMap model) {
        return "redirect:/catalog";
    }


    @RequestMapping(value = "/err")
    public String err() {
        return "pages/error";
    }


    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String adminPage() {
        return "pages/search";
    }

}
