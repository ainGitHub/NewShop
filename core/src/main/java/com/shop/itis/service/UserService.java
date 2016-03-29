package com.shop.itis.service;

import com.shop.itis.domain.User;
import com.shop.itis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {


    @Autowired
    UserRepository userRepository;

    @Transactional
    public void add(User user) {
        userRepository.add(user);
    }


    @Transactional
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Transactional
    public void update(User user) {
        userRepository.update(user);
    }
}
