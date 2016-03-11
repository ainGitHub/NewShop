package com.shop.itis.service;

import com.shop.itis.domain.UserRoles;
import com.shop.itis.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Transactional
    public void add(UserRoles userRoles) {
        roleRepository.add(userRoles);
    }
}
