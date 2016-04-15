package com.shop.itis.service;

import com.shop.itis.domain.Cart;
import com.shop.itis.domain.UserInfo;
import com.shop.itis.repository.CartRepository;
import org.hibernate.NonUniqueObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    @Transactional
    public void add(Cart cart) {
        try {
            cartRepository.add(cart);
        } catch (NonUniqueObjectException e) {
            cartRepository.merge(cart);
        }
    }

    @Transactional
    public Cart getCartByUsername(UserInfo userInfo) {
        return cartRepository.getCartByUsername(userInfo.getUsername());
    }

    @Transactional
    public Cart getById(Long id) {
        return cartRepository.getById(id);
    }

    @Transactional
    public void update(Cart cart) {
        cartRepository.update(cart);
    }

    @Transactional
    public void delete(Cart cart) {
        cartRepository.delete(cart);
    }
}
