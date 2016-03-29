package com.shop.itis.repository;

import com.shop.itis.domain.User;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    @Autowired
    SessionFactory sessionFactory;

    public void add(User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }


    public User getUserByUsername(String username) {
        List<User> users = sessionFactory.getCurrentSession().createCriteria(User.class).add(Restrictions.eq("username", username)).list();

        if (users.isEmpty()) return null;
        else return users.get(0);
    }

    public void update(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
