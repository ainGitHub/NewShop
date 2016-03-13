package com.shop.itis.controller;

import com.shop.itis.domain.Good;
import com.shop.itis.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
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
        model.addAttribute("goods", goods.subList((page - 1) * TEST_LIMIT, page * TEST_LIMIT < goods.size() ? page * TEST_LIMIT : goods.size()));

        List<Integer> pages = new ArrayList<Integer>();

        for (int i = 1; i <= goods.size() / TEST_LIMIT + 1; i++) {
            pages.add(i);
        }

        model.addAttribute("pages", pages);
        model.addAttribute("goodsCount", goods.size());
        return "pages/catalog";
    }

    @RequestMapping(value = "/showMore", method = RequestMethod.POST)
    public String showMoreGoods(Integer page, Integer limit, Model model) {
        // Эта страшная проверка с page и limit только для теста, так как у нас пока нет реальных данных
        System.out.println("here");
        List<Good> goods = goodService.getAllGoods();
        if (TEST_GOODS_COUNT + limit > page * limit)
            model.addAttribute("goods", (TEST_GOODS_COUNT > page * limit) ? goods : goods.subList(0, TEST_GOODS_COUNT + limit - page * limit));
        return "pages/ajaxGoods";
    }
}
