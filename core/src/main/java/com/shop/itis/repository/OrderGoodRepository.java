package com.shop.itis.repository;

import com.shop.itis.domain.OrderGood;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderGoodRepository {
    @Autowired
    SessionFactory sessionFactory;

    public void add(OrderGood orderGood) {
        sessionFactory.getCurrentSession().saveOrUpdate(orderGood);
    }

    public void update(OrderGood orderGood) {
        sessionFactory.getCurrentSession().update(orderGood);
    }

    public void delete(OrderGood orderGood) {
        sessionFactory.getCurrentSession().delete(orderGood);
    }
}
