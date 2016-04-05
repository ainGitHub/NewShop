package com.shop.itis.repository;

import com.shop.itis.domain.UserGoods;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartRepository {
    @Autowired
    SessionFactory sessionFactory;

    public void add(UserGoods userGoods) {
        sessionFactory.getCurrentSession().save(userGoods);
    }

    public void update(UserGoods userGoods) {
        sessionFactory.getCurrentSession().saveOrUpdate(userGoods);
    }

    public UserGoods getById(Long goodId, String username) {
        return (UserGoods) sessionFactory.getCurrentSession()
                .createCriteria(UserGoods.class)
                .add(Restrictions.eq("cartId.goodId", goodId))
                .add(Restrictions.eq("cartId.userId", username))
                .uniqueResult();
    }

    public void flush() {
        sessionFactory.getCurrentSession().flush();
    }

    public List<UserGoods> getUserAllGoods(String username) {
        return sessionFactory.getCurrentSession().createCriteria(UserGoods.class).add(Restrictions.eq("cartId.userId", username)).list();
    }

    public void delete(UserGoods cart) {
        sessionFactory.getCurrentSession().delete(cart);
    }
}
