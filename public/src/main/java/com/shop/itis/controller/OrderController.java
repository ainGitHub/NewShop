package com.shop.itis.controller;

import com.shop.itis.Utils.Constants;
import com.shop.itis.Utils.Utils;
import com.shop.itis.domain.Cart;
import com.shop.itis.domain.Good;
import com.shop.itis.domain.User;
import com.shop.itis.service.CartService;
import com.shop.itis.service.GoodService;
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
    @Autowired
    HttpServletRequest request;

    @Autowired
    UserService userService;

    @Autowired
    GoodService goodService;

    @Autowired
    CartService cartService;

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
        Cart cart = (Cart) request.getSession().getAttribute(Constants.CART);
        Good good = goodService.getGoodById(goodId);
        if (good != null)
            cart.getGoods().remove(good);
        cartService.flush();
        request.getSession().setAttribute(Constants.CART, cart);
        if (cart.getGoods().isEmpty())
            return "redirect:/cart";

        return "pages/order";
    }
}
