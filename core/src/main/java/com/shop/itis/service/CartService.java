package com.shop.itis.service;

import com.shop.itis.domain.Cart;
import com.shop.itis.domain.UserInfo;
import com.shop.itis.repository.CartRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    @Transactional
    public void add(Cart cart) {
        try {
            cartRepository.add(cart);
        } catch (ConstraintViolationException e) {
            System.out.println("already Exists");
        }
    }

    @Transactional
    public Set<Cart> getAllCarts(UserInfo userInfo) {
        return cartRepository.getAllCarts(userInfo.getUsername());
    }
}
