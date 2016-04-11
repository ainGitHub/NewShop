package com.shop.itis.repository;

import com.shop.itis.domain.GoodsWrapper;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CartIdRepository {
    @Autowired
    SessionFactory sessionFactory;

    public void add(GoodsWrapper goodsWrapper) {
        sessionFactory.getCurrentSession().saveOrUpdate(goodsWrapper);
    }

    public void flush() {
        sessionFactory.getCurrentSession().flush();
    }

    public void delete(GoodsWrapper goodsWrapper) {
        sessionFactory.getCurrentSession().delete(goodsWrapper);
    }

    public void merge(GoodsWrapper goodsWrapper) {
        sessionFactory.getCurrentSession().merge(goodsWrapper);
    }
}
