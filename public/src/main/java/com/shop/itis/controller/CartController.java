package com.shop.itis.controller;

import com.shop.itis.Utils.Constants;
import com.shop.itis.Utils.Utils;
import com.shop.itis.annotation.CategoryMenu;
import com.shop.itis.domain.Cart;
import com.shop.itis.domain.Good;
import com.shop.itis.domain.UserInfo;
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
    HttpServletRequest servletRequest;

    @Autowired
    CartService cartService;


    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Map addGood(@RequestParam("goodId") Long goodId) {
        Good forAddGood = goodService.getGoodById(goodId);

        Set<Cart> cartGoods = Utils.getAttributeCartGoods(servletRequest);
        if (cartGoods == null)
            cartGoods = new HashSet<Cart>();

        UserInfo userInfo = Utils.getAutentificationUser(userService);

        Map<String, String> map = forNullUser(userInfo, forAddGood, cartGoods);

        return map;
    }


    private Map<String, String> forNullUser(UserInfo userInfo, Good forAddGood, Set<Cart> goods) {
        Double sum = Utils.getAttrSum(servletRequest);
        String error = null;
        Cart cart = new Cart(userInfo, forAddGood, 1);

        if (goods.add(cart)) {
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
        Set<Cart> cartGoods = Utils.getAttributeCartGoods(servletRequest);
        if (cartGoods == null || cartGoods.isEmpty()) {
            return "К сожалению в вашей корзине нет товаров";
        }
        return null;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestParam("goodId") Long goodId) {
        Good deleteGood = goodService.getGoodById(goodId);

        Set<Cart> cartGoods = Utils.getAttributeCartGoods(servletRequest);
        Cart realCart = null;
        for (Cart cart : cartGoods) {
            if (cart.getGood().equals(deleteGood)) {
                realCart = cart;
                break;
            }
        }

        Double sum = Utils.getAttrSum(servletRequest);
        if (realCart != null) {
            cartGoods.remove(realCart);
            sum -= (deleteGood.getPrice() * realCart.getCount());
        }

        Utils.addAttributes(cartGoods, sum, cartGoods.size(), servletRequest);

        return "redirect:/cart";
    }


    @RequestMapping(value = "/count", method = RequestMethod.POST)
    @ResponseBody
    public Map changeCount(@RequestParam("goodId") Long goodId, @RequestParam("count") Integer count) {
        Map map = new HashMap();

        Good good = goodService.getGoodById(goodId);
        Double sum = Utils.getAttrSum(servletRequest);
        Set<Cart> cartGoods = Utils.getAttributeCartGoods(servletRequest);

        for (Cart g : cartGoods) {
            if (g.getGood().equals(good)) {
                Double newSum = (sum - g.getCount() * good.getPrice()) + good.getPrice() * count;

                Utils.addAttributes(cartGoods, newSum, cartGoods.size(), servletRequest);

                g.setCount(count);

                map.put(Constants.CART_SUM, newSum);
                return map;
            }
        }


        return new HashMap();
    }
}
