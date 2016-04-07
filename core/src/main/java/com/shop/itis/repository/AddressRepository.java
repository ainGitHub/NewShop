package com.shop.itis.repository;

import com.shop.itis.domain.Address;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AddressRepository {
    @Autowired
    SessionFactory sessionFactory;

    public void add(Address address) {
        sessionFactory.getCurrentSession().saveOrUpdate(address);
    }

    public void update(Address address) {
        sessionFactory.getCurrentSession().saveOrUpdate(address);
    }

    public List<Address> userAddress(String username) {
        return sessionFactory.getCurrentSession().createCriteria(Address.class)
                .add(Restrictions.eq("userInfo.username", username)).list();
    }

}
