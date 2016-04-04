package com.shop.itis.controller;

import com.shop.itis.Utils.Constants;
import com.shop.itis.Utils.Utils;
import com.shop.itis.annotation.CategoryMenu;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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
    public Map addGood(@RequestParam("goodId") Long goodId) {
        Good forAddGood = goodService.getGoodById(goodId);

        User user = Utils.getAutentificationUser(userService);
        if (user != null) {
            Cart cart = new Cart(user, forAddGood, 1);
            cartService.update(cart);
        }

        Set<Good> goods = (Set<Good>) servletRequest.getSession().getAttribute("cart_goods");
        if (goods == null)
            goods = new HashSet<Good>();

        Double sum = (Double) servletRequest.getSession().getAttribute(Constants.CART_SUM);
        Integer count = (Integer) servletRequest.getSession().getAttribute(Constants.CART_GOODS_COUNT);
        if (goods.add(forAddGood)) {
            if (sum == null)
                sum = 0.0;
            sum += forAddGood.getPrice();

            if (count == null)
                count = goods.size();
        }

        servletRequest.getSession().setAttribute("cart_goods", goods);
        servletRequest.getSession().setAttribute(Constants.CART_SUM, sum);
        servletRequest.getSession().setAttribute(Constants.CART_GOODS_COUNT, count);
        servletRequest.getSession().setAttribute(Constants.CART_GOODS, goods);

        Map<String, String> map = new HashMap<String, String>();
        map.put(Constants.CART_SUM, sum + "");
        map.put(Constants.CART_GOODS_COUNT, goods.size() + "");
        return map;
    }



    @CategoryMenu
    @RequestMapping(method = RequestMethod.GET)
    public String cartPage(ModelMap map) {
        Set<Good> goods = (Set<Good>) servletRequest.getSession().getAttribute(Constants.CART_GOODS);
        if (goods == null || goods.isEmpty()) {
            map.put("cartError", "К сожалению в вашей корзине нет товаров");
        }

        return "pages/cart";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestParam("goodId") Long goodId) {
        if (goodId != null) {
            Good forDeleteGood = goodService.getGoodById(goodId);
            Set<Good> goods = (Set<Good>) servletRequest.getSession().getAttribute(Constants.CART_GOODS);
            goods.remove(forDeleteGood);
            servletRequest.getSession().setAttribute(Constants.CART_GOODS, goods);
            Double sum = (Double) servletRequest.getSession().getAttribute(Constants.CART_SUM);
            sum -= forDeleteGood.getPrice();

            servletRequest.getSession().setAttribute(Constants.CART_SUM, sum);
            servletRequest.getSession().setAttribute(Constants.CART_GOODS_COUNT, goods.size());
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
