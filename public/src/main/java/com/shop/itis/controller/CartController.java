package com.shop.itis.controller;

import com.shop.itis.Utils.Utils;
import com.shop.itis.domain.Cart;
import com.shop.itis.domain.Good;
import com.shop.itis.domain.User;
import com.shop.itis.service.CartService;
import com.shop.itis.service.GoodService;
import com.shop.itis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    GoodService goodService;

    @Autowired
    UserService userService;

    @Autowired
    CartService cartService;

    @Autowired
    HttpServletRequest servletRequest;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String addGood(@RequestParam("goodId") Long goodid) {
        Good good = goodService.getGoodById(goodid);
        if (good == null) return "good is not find";

        User user = Utils.getAutentificationUser(userService);

        if (user != null && servletRequest.getSession().getAttribute("cart") != null)
            user.setCart((Cart) servletRequest.getSession().getAttribute("cart"));


        Cart cart = null;
        try {
            if (user != null && user.getCart() != null) {
                cart = user.getCart();
                cart.getGoods().add(good);
                cartService.update();
            } else {
                cart = new Cart();
                cart.getGoods().add(good);
                cartService.add(cart);
            }
        } catch (DataIntegrityViolationException e) {
            return "good already exist";
        }


        servletRequest.getSession().setAttribute("cart", cart);
        return "ok";
    }
}
