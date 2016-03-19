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

        Cart cart = null;

        try {
            if (servletRequest.getSession().getAttribute("cart") != null) {
                cart = (Cart) servletRequest.getSession().getAttribute("cart");
                cart.getGoods().add(good);
                servletRequest.getSession().setAttribute("cart", cart);
                cartService.update(cart);
                return "ok";
            }
            if (user != null && user.getCart() != null) {
                cart = user.getCart();
                cart.getGoods().add(good);
                cartService.update(cart);
            } else {
                cart = new Cart();
                cart.getGoods().add(good);
                cartService.add(cart);
            }
        } catch (DataIntegrityViolationException e) {
            return "good already exist";
        }

        servletRequest.getSession().setAttribute("cart", cart);
        if (cart != null) {
            servletRequest.getSession().setAttribute("cartGoodsCount", cart.getGoods().size());
            double sum = 0.0;
            for (Good g : cart.getGoods()) {
                sum += g.getPrice();
            }
            servletRequest.getSession().setAttribute("cartSum", sum);
        }
        return "ok";
    }


    @RequestMapping(method = RequestMethod.GET)
    public String cartPage() {
        Cart cart = (Cart) servletRequest.getSession().getAttribute("cart");


        return "pages/cart";
    }
}
