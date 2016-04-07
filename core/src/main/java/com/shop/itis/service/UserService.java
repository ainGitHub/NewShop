package com.shop.itis.service;

import com.shop.itis.domain.UserInfo;
import com.shop.itis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {


    @Autowired
    UserRepository userRepository;

    @Transactional
    public void add(UserInfo userInfo) {
        userRepository.add(userInfo);
    }


    @Transactional
    public UserInfo getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Transactional
    public void update(UserInfo userInfo) {
        userRepository.update(userInfo);
    }
}
