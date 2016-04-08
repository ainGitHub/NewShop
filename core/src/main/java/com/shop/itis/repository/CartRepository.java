package com.shop.itis.repository;

import com.shop.itis.domain.Cart;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class CartRepository {
    @Autowired
    SessionFactory sessionFactory;

    public void add(Cart cart) {
        sessionFactory.getCurrentSession().saveOrUpdate(cart);
    }

    public Set<Cart> getAllCarts(String username) {
        return (Set<Cart>) sessionFactory.getCurrentSession().createCriteria(Cart.class)
                .add(Restrictions.eq("username", username));
    }
}
