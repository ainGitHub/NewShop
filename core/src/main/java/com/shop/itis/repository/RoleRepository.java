package com.shop.itis.repository;

import com.shop.itis.domain.UserRoles;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleRepository {
    @Autowired
    SessionFactory sessionFactory;

    public void add(UserRoles userRoles) {
        sessionFactory.getCurrentSession().save(userRoles);
    }
}
