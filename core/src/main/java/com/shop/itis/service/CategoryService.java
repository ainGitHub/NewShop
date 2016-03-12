package com.shop.itis.service;

import com.shop.itis.domain.Category;
import com.shop.itis.domain.Good;
import com.shop.itis.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Transactional
    public void add(Category category) {
        categoryRepository.add(category);
    }

    @Transactional
    public void update() {
        categoryRepository.update();
    }

    @Transactional
    public Category getById(Long id) {
        return categoryRepository.getById(id);
    }

    @Transactional
    public List<Good> getGoodsInCategory(String alias) {
        return categoryRepository.getGoodsInCategory(alias);
    }

    @Transactional
    public List<Category> getAllCategories() {
        return categoryRepository.getAllCategories();
    }
}
