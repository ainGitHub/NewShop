package com.shop.itis.repository;

import com.shop.itis.domain.Cart;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CartRepository {
    @Autowired
    SessionFactory sessionFactory;

    public void add(Cart cart) {
        sessionFactory.getCurrentSession().saveOrUpdate(cart);
    }

    public Cart getCartByUsername(String username) {
        return (Cart) sessionFactory.getCurrentSession().createCriteria(Cart.class)
                .add(Restrictions.eq("username", username)).uniqueResult();
    }

    public Cart getById(Long cartId) {
        return (Cart) sessionFactory.getCurrentSession().createCriteria(Cart.class).add(Restrictions.eq("id", cartId)).uniqueResult();
    }

    public void update(Cart cart) {
        sessionFactory.getCurrentSession().update(cart);
    }

    public void flush() {
        sessionFactory.getCurrentSession().flush();
    }

    public void delete(Cart cart) {
        sessionFactory.getCurrentSession().delete(cart);
    }

    public void merge(Cart cart) {
        sessionFactory.getCurrentSession().merge(cart);
    }
}
