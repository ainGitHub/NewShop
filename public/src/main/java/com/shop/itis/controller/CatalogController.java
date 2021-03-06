package com.shop.itis.controller;

import com.shop.itis.Utils.Constants;
import com.shop.itis.annotation.CategoryMenu;
import com.shop.itis.domain.Good;
import com.shop.itis.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CatalogController {

    @Autowired
    GoodService goodService;


    @CategoryMenu
    @RequestMapping(value = "/catalog", method = RequestMethod.GET)
    public String renderCatalog(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                Long limit,
                                ModelMap map) {

        List<Good> goods = goodService.getAllGoods();

        if (goods != null && !goods.isEmpty())
            map.addAttribute(Constants.GOODS, goods.subList(0, Constants.TEST_LIMIT));

        map.addAttribute("page", page);
        map.addAttribute("limit", limit == null ? Constants.TEST_LIMIT : limit);

        if (goods != null) {
            map.addAttribute("goodsCount", goods.size());
        }

        map.put("pageTitle", "Каталог");

        return "pages/catalog";
    }

    @RequestMapping(value = "/showMore", method = RequestMethod.POST)
    public String showMoreGoods(Integer page, Integer limit, Model model) {
        List<Good> goods = goodService.getAllGoods();

        int start = (page - 1) * limit;
        int end = page * limit;

        model.addAttribute(Constants.GOODS, goods.size() > end ?
                goods.subList(start, end) :
                goods.subList(start, goods.size()));
        return "pages/ajaxGood";
    }


    @CategoryMenu
    @RequestMapping(value = "/catalog/good")
    public String showOneGood(@RequestParam("goodId") Long goodId, ModelMap map) {
        Good good = goodService.getGoodById(goodId);
        map.put("oneGood", good);
        if (good != null)
            map.put("pageTitle", "Страница товара: " + good.getName());
        return "pages/oneGood";
    }
}
