package com.shop.itis.controller;


import com.shop.itis.annotation.CategoryMenu;
import com.shop.itis.domain.Good;
import com.shop.itis.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    GoodService goodService;

    @CategoryMenu
    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public String showCategory(@RequestParam("category") Long categoryId, ModelMap map) {
        List<Good> goods = goodService.getGoodsByCategoryId(categoryId);
        map.put("categoryGoods", goods);
        return "pages/category";
    }
}
