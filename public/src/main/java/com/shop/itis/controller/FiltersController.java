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

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/search")
public class FiltersController {

    @Autowired
    GoodService goodService;

    @Autowired
    HttpServletRequest request;

    @CategoryMenu
    @RequestMapping(method = RequestMethod.POST)
    public String searchBy(ModelMap map, @RequestParam("categoryId") Long categoryId,
                           @RequestParam(value = "min", defaultValue = "0") Double min,
                           @RequestParam(value = "max", defaultValue = "1000000") Double max,
                           @RequestParam(value = "name", defaultValue = "") String name) {
        List<Good> goods = new ArrayList<Good>();
        if (categoryId != null) {
            goods = goodService.getGoodsByCategoryPriceName(categoryId, min, max, name);
        } else {
            goods = goodService.getGoodByPriceName(min, max, name);
        }

        map.put("categoryId", categoryId);
        map.put("min", min);
        map.put("max", max);
        map.put("name", name);


        map.put("limit", 0);
        map.put("goodsCount", 0);
        map.put(Constants.GOODS, goods);
        return "pages/catalog";
    }
}
