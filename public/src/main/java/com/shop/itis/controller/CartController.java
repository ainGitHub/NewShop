package com.shop.itis.controller;

import com.shop.itis.Utils.Constants;
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


    /**
     *
     * @param goodId - id товара, для добавления
     * @return view
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String addGood(@RequestParam("goodId") Long goodId) {
        //TODO надо как то отрефакторить

        Good forAddGood = goodService.getGoodById(goodId);

        Cart userCart = (Cart) servletRequest.getSession().getAttribute(Constants.CART);

        if (userCart == null) {
            userCart = new Cart();
        }

        try {
            userCart.getGoods().add(forAddGood);
            cartService.update(userCart);
        } catch (NonUniqueObjectException e) {
            userCart.getGoods().remove(forAddGood);
        }

        servletRequest.getSession().setAttribute(Constants.CART_SUM, getSum(userCart));
        servletRequest.getSession().setAttribute(Constants.CART_GOODS_COUNT, userCart.getGoods().size());

        servletRequest.getSession().setAttribute(Constants.CART, userCart);
        return "ok";
    }

    private double getSum(Cart userCart) {
        double sum = 0.0;
        for (Good g : userCart.getGoods()) {
            sum += g.getPrice();
        }
        return sum;
    }


    @RequestMapping(method = RequestMethod.GET)
    public String cartPage(ModelMap map) {
        Cart cart = (Cart) servletRequest.getSession().getAttribute(Constants.CART);
        if (cart == null || cart.getGoods().isEmpty()) {
            map.put("cartError", "К сожалению в вашей корзине нет товаров");
        } else {
            map.put("cartGoods", cart.getGoods());
        }

        return "pages/cart";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestParam("goodId") Long goodId, ModelMap map) {
        if (goodId != null) {
            Good forDeleteGood = goodService.getGoodById(goodId);
            Cart cart = (Cart) servletRequest.getSession().getAttribute(Constants.CART);
            cart.getGoods().remove(forDeleteGood);
            cartService.flush();

            servletRequest.getSession().setAttribute(Constants.CART_SUM, getSum(cart));
            servletRequest.getSession().setAttribute(Constants.CART_GOODS_COUNT, cart.getGoods().size());

            map.put("cartGoods", cart.getGoods());
        }

        return "redirect:/cart";
    }


    @RequestMapping(value = "/count", method = RequestMethod.POST)
    @ResponseBody
    public String changeCount(@RequestParam("goodId") Integer goodId, @RequestParam("count") Integer count) {
        System.out.println(goodId + " " + count);
        return "ok";
    }
}
