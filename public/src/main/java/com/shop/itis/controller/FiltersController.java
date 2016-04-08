package com.shop.itis.controller;

import com.shop.itis.Utils.Constants;
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
@RequestMapping("/search")
public class FiltersController {

    @Autowired
    GoodService goodService;

    @CategoryMenu
    @RequestMapping(method = RequestMethod.POST)
    public String searchBy(ModelMap map, @RequestParam("categoryId") Long categoryId,
                           @RequestParam(value = "min", defaultValue = "0") Double min,
                           @RequestParam(value = "max", defaultValue = "1000000") Double max,
                           @RequestParam(value = "name", defaultValue = "") String name) {
        List<Good> goods = goodService.getGoodsByCategoryPriceName(categoryId, min, max, name);
        map.put("limit", 0);
        map.put("goodsCount", 0);
        map.put(Constants.GOODS, goods);
        return "pages/catalog";
    }
}
