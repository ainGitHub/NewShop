package com.shop.itis.repository;


import com.shop.itis.domain.Order;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {
    @Autowired
    SessionFactory sessionFactory;

    public void add(Order order) {
        sessionFactory.getCurrentSession().save(order);
    }
}
