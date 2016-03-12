package com.shop.itis.repository;

import com.shop.itis.domain.Cart;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CartRepository {
    @Autowired
    SessionFactory sessionFactory;

    public void add(Cart cart) {
        sessionFactory.getCurrentSession().save(cart);
    }

    public void update() {
        sessionFactory.getCurrentSession().flush();
    }
}
