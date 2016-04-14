package com.shop.itis.repository;

import com.shop.itis.domain.CartGood;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CartGoodRepository {
    @Autowired
    SessionFactory sessionFactory;

    public void add(CartGood cartGood) {
        sessionFactory.getCurrentSession().saveOrUpdate(cartGood);
    }

    public void flush() {
        sessionFactory.getCurrentSession().flush();
    }

    public void delete(CartGood cartGood) {
        sessionFactory.getCurrentSession().delete(cartGood);
    }

    public void merge(CartGood cartGood) {
        sessionFactory.getCurrentSession().merge(cartGood);
    }
}
