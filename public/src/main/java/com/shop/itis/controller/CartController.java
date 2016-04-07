package com.shop.itis.controller;

import com.shop.itis.Utils.Constants;
import com.shop.itis.Utils.Utils;
import com.shop.itis.annotation.CategoryMenu;
import com.shop.itis.domain.Good;
import com.shop.itis.domain.GoodWrapper;
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


    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Map addGood(@RequestParam("goodId") Long goodId) {
        Good forAddGood = goodService.getGoodById(goodId);

        Set<GoodWrapper> goods = Utils.getAttributeCartGoods(servletRequest);
        if (goods == null)
            goods = new HashSet<GoodWrapper>();

        Double sum = Utils.getAttrSum(servletRequest);

        String error = null;
        if (goods.add(new GoodWrapper(forAddGood, 1))) {
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
        Set<GoodWrapper> goods = Utils.getAttributeCartGoods(servletRequest);
        if (goods == null || goods.isEmpty()) {
            return "К сожалению в вашей корзине нет товаров";
        }
        return null;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestParam("goodId") Long goodId) {
        Good deleteGood = goodService.getGoodById(goodId);

        Set<GoodWrapper> goods = Utils.getAttributeCartGoods(servletRequest);
        GoodWrapper realGoodWr = null;
        for (GoodWrapper g : goods) {
            if (g.getGood().equals(deleteGood)) {
                realGoodWr = g;
                break;
            }
        }

        Double sum = Utils.getAttrSum(servletRequest);
        if (realGoodWr != null) {
            goods.remove(realGoodWr);
            sum -= (deleteGood.getPrice() * realGoodWr.getCount());
        }

        Utils.addAttributes(goods, sum, goods.size(), servletRequest);

        return "redirect:/cart";
    }


    @RequestMapping(value = "/count", method = RequestMethod.POST)
    @ResponseBody
    public Map changeCount(@RequestParam("goodId") Long goodId, @RequestParam("count") Integer count) {
        Map map = new HashMap();

        Good good = goodService.getGoodById(goodId);
        Double sum = Utils.getAttrSum(servletRequest);
        Set<GoodWrapper> goodWrappers = Utils.getAttributeCartGoods(servletRequest);

        for (GoodWrapper g : goodWrappers) {
            if (g.getGood().equals(good)) {
                Double newSum = (sum - g.getCount() * good.getPrice()) + good.getPrice() * count;

                Utils.addAttributes(goodWrappers, newSum, goodWrappers.size(), servletRequest);

                g.setCount(count);

                map.put(Constants.CART_SUM, newSum);
                return map;
            }
        }


        return new HashMap();
    }
}
