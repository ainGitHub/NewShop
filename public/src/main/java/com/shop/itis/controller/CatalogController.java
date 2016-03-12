package com.shop.itis.controller;

import com.shop.itis.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CatalogController {
    @Autowired
    GoodService goodService;

    @RequestMapping(value = "/catalog", method = RequestMethod.GET)
    public String catalogPage(ModelMap map) {
        map.put("allGoods", goodService.getAllGoods());
        return "pages/catalog";
    }
}
