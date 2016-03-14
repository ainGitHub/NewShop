package com.shop.itis.controller;

import com.shop.itis.domain.Good;
import com.shop.itis.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CatalogController {
    private static final Integer TEST_GOODS_COUNT = 16;
    private static final Integer TEST_LIMIT = 6;

    @Autowired
    GoodService goodService;

    /*@RequestMapping(value = "/catalog", method = RequestMethod.GET)
    public String catalogPage(ModelMap map) {
        map.put("allGoods", goodService.getAllGoods());
        return "pages/catalog";
    }*/


    @RequestMapping(value = "/catalog", method = RequestMethod.GET)
    public String renderCatalog(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                Long limit,
                                Model model) {

        List<Good> goods = goodService.getAllGoods();
        model.addAttribute("goods", goods.subList(0, TEST_LIMIT));

        model.addAttribute("page", page);
        model.addAttribute("limit", limit == null ? TEST_LIMIT : limit);
        model.addAttribute("goodsCount", goods.size());
        return "pages/catalog";
    }

    @RequestMapping(value = "/showMore", method = RequestMethod.POST)
    public String showMoreGoods(Integer page, Integer limit, Model model) {
        List<Good> goods = goodService.getAllGoods();
        System.out.println(page);
        System.out.println(limit);

        int start = (page - 1) * limit;
        int end = page * limit;
        model.addAttribute("goods", goods.size() > end ? goods.subList(start, end) : goods.subList(start, goods.size()));
        return "pages/ajaxGood";
    }
}
