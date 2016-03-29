package com.shop.itis.controller;

import com.shop.itis.Utils.Utils;
import com.shop.itis.domain.User;
import com.shop.itis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/order")
public class OrderController {
    //todo реализовать

    @Autowired
    HttpServletRequest request;

    @Autowired
    UserService userService;

    @RequestMapping
    public String page(ModelMap map) {
        User user = Utils.getAutentificationUser(userService);
        if (user != null) {
            map.put("user", user);
            return "pages/order";
        }
        return "redirect:/login";
    }

    @RequestMapping("/delete/good")
    public String deleteGood(@RequestParam("goodId") Long goodId) {
        return "pages/order";
    }
}
