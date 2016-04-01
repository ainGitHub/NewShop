package com.shop.itis.controller;


import com.shop.itis.annotation.CategoryMenu;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CategoryController {

    @CategoryMenu
    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public String showCategory(@RequestParam("category") Long categoryId, ModelMap map) {
        map.put("categoryGoods", categoryId);
        return "pages/category";
    }
}
