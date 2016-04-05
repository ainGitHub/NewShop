package com.shop.itis.repository;

import com.shop.itis.domain.Address;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AddressRepository {
    @Autowired
    SessionFactory sessionFactory;

    public void add(Address address) {
        sessionFactory.getCurrentSession().save(address);
    }

    public void update(Address address) {
        sessionFactory.getCurrentSession().update(address);
    }
}
