package com.shop.itis.repository;


import com.shop.itis.domain.Order;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepository {
    @Autowired
    SessionFactory sessionFactory;

    public void add(Order order) {
        sessionFactory.getCurrentSession().saveOrUpdate(order);
    }

    public List<Order> getUserOrders(String username) {
        return sessionFactory.getCurrentSession().createCriteria(Order.class)
                .add(Restrictions.eq("userInfo.username", username)).list();
    }

    public void delete(Order order) {
        sessionFactory.getCurrentSession().delete(order);
    }

    public Order getById(Long id) {
        return (Order) sessionFactory.getCurrentSession().createCriteria(Order.class)
                .add(Restrictions.eq("id", id)).uniqueResult();
    }
}
