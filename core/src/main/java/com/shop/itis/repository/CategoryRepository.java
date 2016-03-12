package com.shop.itis.repository;


import com.shop.itis.domain.Category;
import com.shop.itis.domain.Good;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepository {
    @Autowired
    SessionFactory sessionFactory;

    public void add(Category category) {
        sessionFactory.getCurrentSession().save(category);
        sessionFactory.getCurrentSession().flush();
    }

    public void update() {
        sessionFactory.getCurrentSession().flush();
    }

    public Category getById(Long id) {
        return (Category) sessionFactory.getCurrentSession().load(Category.class, id);
    }

    public List<Good> getGoodsInCategory(String alias) {
        Category category = (Category) sessionFactory.getCurrentSession().createCriteria(Category.class).add(Restrictions.eq("alias", alias)).uniqueResult();
        if (category != null)
            return category.getGoods();
        return null;
    }

    public List<Category> getAllCategories() {
        return sessionFactory.getCurrentSession().createCriteria(Category.class).list();
    }
}
