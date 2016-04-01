package com.shop.itis.aspect;

import com.shop.itis.domain.Category;
import com.shop.itis.service.CategoryService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Aspect
@Component
public class CategoryMenuAspect {
    private final String CATEGORY_MENU = "menuList";

    @Autowired
    CategoryService categoryService;

    @Autowired
    HttpServletRequest request;

    @Pointcut("@annotation(com.shop.itis.annotation.CategoryMenu)")
    public void includeCategoryMenu() {
    }

    @Before("includeCategoryMenu()")
    public void includeCategory() {
        List<Category> categories = categoryService.getAllCategories();
        request.getSession().setAttribute(CATEGORY_MENU, categories);
    }
}
