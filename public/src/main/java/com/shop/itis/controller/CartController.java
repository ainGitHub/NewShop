package com.shop.itis.controller;

import com.shop.itis.Utils.Constants;
import com.shop.itis.Utils.Utils;
import com.shop.itis.annotation.CategoryMenu;
import com.shop.itis.domain.Cart;
import com.shop.itis.domain.CartGood;
import com.shop.itis.domain.Good;
import com.shop.itis.domain.UserInfo;
import com.shop.itis.service.CartGoodService;
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
import java.util.Map;

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

    @Autowired
    CartGoodService cartGoodService;


    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Map addGood(@RequestParam("goodId") Long goodId) {
        Good good = goodService.getGoodById(goodId); // for add good

        Cart cart = (Cart) servletRequest.getSession().getAttribute(Constants.CART);
        if (cart == null) {
            cart = new Cart();
            cartService.add(cart);
        }

        CartGood cartGood = new CartGood(good, 1, cart);
        cartGoodService.add(cartGood);

        if (cart.getCartGood().add(cartGood)) {
            cart.setSum(cart.getSum() + good.getPrice() * 1);
            cart.setGoodsCount(cart.getGoodsCount() + 1);
        }
        cartService.update(cart);

        UserInfo user = Utils.getAutentificationUser(userService);
        if (user != null) {
            user.setCart(cart);
            userService.update(user);
        }


        servletRequest.getSession().setAttribute(Constants.CART, cart);
        servletRequest.getSession().setAttribute(Constants.CART_SUM, cart.getSum());
        servletRequest.getSession().setAttribute(Constants.CART_GOODS_COUNT, cart.getGoodsCount());


        Map<String, String> map = new HashMap<String, String>();
        map.put(Constants.CART_SUM, cart.getSum() + "");
        map.put(Constants.CART_GOODS_COUNT, cart.getGoodsCount() + "");
        return map;
    }


    @CategoryMenu
    @RequestMapping(method = RequestMethod.GET)
    public String cartPage(ModelMap map) {
        Cart cart = (Cart) servletRequest.getSession().getAttribute(Constants.CART);

        if (cart != null) {
            servletRequest.getSession().setAttribute(Constants.CART_SUM, cart.getSum());
            servletRequest.getSession().setAttribute(Constants.CART_GOODS_COUNT, cart.getGoodsCount());
        }

        map.put("pageTitle", "Корзина");
        map.put("cartError", getNotFoundCartGoods(cart));
        return "pages/cart";
    }

    private String getNotFoundCartGoods(Cart cart) {
        if (cart == null || cart.getCartGood().isEmpty()) {
            return "К сожалению в вашей корзине нет товаров";
        }
        return null;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestParam("goodId") Long goodId, ModelMap map) {
        Good good = goodService.getGoodById(goodId); // for delete good

        Cart cart = (Cart) servletRequest.getSession().getAttribute(Constants.CART);
        if (cart == null) {
            map.put("cartError", getNotFoundCartGoods(cart));
            return "redirect:/cart";
        }

        Double sum = 0.0;
        Integer goodsCount = 0;
        CartGood cartGood = new CartGood(good, 1, cart);
        if (cart.getCartGood().remove(cartGood)) {
            for (CartGood g : cart.getCartGood()) {
                sum += g.getGood().getPrice() * g.getCount();
                goodsCount += g.getCount();
            }

            cartGoodService.delete(cartGood);

            cart.setSum(sum);
            cart.setGoodsCount(goodsCount);
            cartService.update(cart);
        }

        servletRequest.getSession().setAttribute(Constants.CART, cart);
        servletRequest.getSession().setAttribute(Constants.CART_SUM, cart.getSum());
        servletRequest.getSession().setAttribute(Constants.CART_GOODS_COUNT, cart.getGoodsCount());
        return "redirect:/cart";
    }


    @RequestMapping(value = "/count", method = RequestMethod.POST)
    @ResponseBody
    public Map changeCount(@RequestParam("goodId") Long goodId, @RequestParam("count") Integer count) {
        Good good = goodService.getGoodById(goodId); //for change good

        Cart cart = (Cart) servletRequest.getSession().getAttribute(Constants.CART);

        Double sum = 0.0;
        Integer goodsCount = 0;
        for (CartGood g : cart.getCartGood()) {
            if (good.equals(g.getGood())) {
                g.setCount(count);
                cartGoodService.add(g);
            }
            sum += g.getGood().getPrice() * g.getCount();
            goodsCount += g.getCount();
            }

        cart.setSum(sum);
        cart.setGoodsCount(goodsCount);
        cartService.update(cart);

        servletRequest.getSession().setAttribute(Constants.CART, cart);
        servletRequest.getSession().setAttribute(Constants.CART_SUM, cart.getSum());
        servletRequest.getSession().setAttribute(Constants.CART_GOODS_COUNT, cart.getGoodsCount());

        Map<String, String> map = new HashMap<String, String>();
        map.put(Constants.CART_SUM, cart.getSum() + "");
        map.put(Constants.CART_GOODS_COUNT, cart.getGoodsCount() + "");
        return map;
    }
}
