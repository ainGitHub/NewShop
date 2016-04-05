package com.shop.itis.controller;

import com.shop.itis.Utils.Constants;
import com.shop.itis.Utils.Utils;
import com.shop.itis.annotation.CategoryMenu;
import com.shop.itis.domain.UserGoods;
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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Map addGood(@RequestParam("goodId") Long goodId) {
        Good forAddGood = goodService.getGoodById(goodId);

        User user = Utils.getAutentificationUser(userService);
        if (user != null) {
            UserGoods userGoods = new UserGoods(user, forAddGood, 1);
            cartService.update(userGoods);
        }

        Set<Good> goods = Utils.getAttributeCartGoods(servletRequest);
        if (goods == null)
            goods = new HashSet<Good>();

        Double sum = Utils.getAttrSum(servletRequest);

        String error = null;
        if (goods.add(forAddGood)) {
            error = "ok";
            if (sum == null)
                sum = 0.0;
            sum += forAddGood.getPrice();
        }

        Utils.addAttributes(goods, sum, goods.size(), servletRequest);

        Map<String, String> map = new HashMap<String, String>();
        map.put(Constants.CART_SUM, sum + "");
        map.put(Constants.CART_GOODS_COUNT, goods.size() + "");
        map.put("exist", error);
        return map;
    }


    @CategoryMenu
    @RequestMapping(method = RequestMethod.GET)
    public String cartPage(ModelMap map) {
        map.put("cartError", getNotFoundCartGoods());
        return "pages/cart";
    }

    private String getNotFoundCartGoods() {
        Set<Good> goods = Utils.getAttributeCartGoods(servletRequest);
        if (goods == null || goods.isEmpty()) {
            return "К сожалению в вашей корзине нет товаров";
        }
        return null;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestParam("goodId") Long goodId) {
        Good deleteGood = goodService.getGoodById(goodId);
        Set<Good> goods = Utils.getAttributeCartGoods(servletRequest);
        goods.remove(deleteGood);

        Double sum = Utils.getAttrSum(servletRequest);
        sum -= deleteGood.getPrice();

        Utils.addAttributes(goods, sum, goods.size(), servletRequest);

        return "redirect:/cart";
    }


    @RequestMapping(value = "/count", method = RequestMethod.POST)
    @ResponseBody
    public String changeCount(@RequestParam("goodId") Integer goodId, @RequestParam("count") Integer count) {
        System.out.println(goodId + " " + count);
        return "ok";
    }
}
