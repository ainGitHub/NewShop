package com.shop.itis.service;

import com.shop.itis.domain.Cart;
import com.shop.itis.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;

    @Transactional
    public void add(Cart cart) {
        cartRepository.add(cart);
    }

    @Transactional
    public void update(Cart cart) {
        cartRepository.update(cart);
    }
}
