package com.shop.itis.controller;

import com.shop.itis.domain.Cart;
import com.shop.itis.domain.Good;
import com.shop.itis.service.CartService;
import com.shop.itis.service.GoodService;
import com.shop.itis.service.UserService;
import org.hibernate.NonUniqueObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
        Good forAddGood = goodService.getGoodById(goodid);

        Cart userCart = (Cart) servletRequest.getSession().getAttribute("cart");

        if (userCart == null) {
            userCart = new Cart();
        }

        try {
            userCart.getGoods().add(forAddGood);
            cartService.update(userCart);
        } catch (NonUniqueObjectException e) {
            userCart.getGoods().remove(forAddGood);
        }

        servletRequest.getSession().setAttribute("cart", cartService.getById(userCart.getId()));
        return "ok";
    }


    @RequestMapping(method = RequestMethod.GET)
    public String cartPage(ModelMap map) {
        Cart cart = (Cart) servletRequest.getSession().getAttribute("cart");
        if (cart == null || cart.getGoods().isEmpty())
            map.put("cartError", "К сожалению в вашей корзине нет товаров");

        return "pages/cart";
    }
}
