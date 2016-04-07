package com.shop.itis.repository;

import com.shop.itis.domain.UserInfo;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    @Autowired
    SessionFactory sessionFactory;

    public void add(UserInfo userInfo) {
        sessionFactory.getCurrentSession().saveOrUpdate(userInfo);
    }


    public UserInfo getUserByUsername(String username) {
        List<UserInfo> users = sessionFactory.getCurrentSession().createCriteria(UserInfo.class).add(Restrictions.eq("username", username)).list();

        if (users.isEmpty()) return null;
        else return users.get(0);
    }

    public void update(UserInfo userInfo) {
        sessionFactory.getCurrentSession().update(userInfo);
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
