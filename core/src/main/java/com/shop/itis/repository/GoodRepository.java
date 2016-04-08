package com.shop.itis.repository;


import com.shop.itis.domain.Good;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class GoodRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public void add(Good good) {
        sessionFactory.getCurrentSession().save(good);
        sessionFactory.getCurrentSession().flush();
    }

    public List<Good> getAllGoods() {
        return sessionFactory.getCurrentSession().createCriteria(Good.class).list();
    }

    public Good getGoodById(Long id) {
        return (Good) sessionFactory.getCurrentSession().load(Good.class, id);
    }

    public void delete(Good good) {
        sessionFactory.getCurrentSession().delete(good);
    }

    public void update() {
        sessionFactory.getCurrentSession().flush();
    }

    public List<Good> getGoodsLikeName(String name) {
        return sessionFactory.getCurrentSession().createCriteria(Good.class)
                .add(Restrictions.like("name", "%" + name + "%"))
                .list();
    }

    public List<Good> getGoodByCategory(Long categoryId) {
        return sessionFactory.getCurrentSession().createCriteria(Good.class)
                .createCriteria("category")
                .add(Restrictions.eq("id", categoryId))
                .list();
    }

    public List<Good> getGoodsByPriceAndName(Long categoryId, Double min, Double max, String name) {
        return sessionFactory.getCurrentSession().createCriteria(Good.class)
                .add(Restrictions.gt("price", min))
                .add(Restrictions.lt("price", max))
                .add(Restrictions.like("name", name + "%"))
                .add(Restrictions.eq("category.id", categoryId))
                .list();
    }
}
