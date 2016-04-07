package com.shop.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Controller
@RequestMapping("/locale")
public class LocaleController {

    @Autowired
    LocaleResolver localeResolver;

    @Autowired
    HttpServletRequest request;


    @RequestMapping("/change")
    public String change(@RequestParam String locale, HttpServletResponse response) {
        String[] localeData = locale.split("_");
        localeResolver.setLocale(request, response, new Locale(localeData[0], localeData[1]));
        return "redirect:/catalog";
    }
}
