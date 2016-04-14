package com.shop.itis.service;

import com.shop.itis.domain.CartGood;
import com.shop.itis.repository.CartGoodRepository;
import org.hibernate.NonUniqueObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CartGoodService {
    @Autowired
    CartGoodRepository cartGoodRepository;

    @Transactional
    public void add(CartGood cartGood) {
        try {
            cartGoodRepository.add(cartGood);
        } catch (NonUniqueObjectException e) {
            cartGoodRepository.merge(cartGood);
        }
    }

    @Transactional
    public void flush() {
        cartGoodRepository.flush();
    }

    @Transactional
    public void delete(CartGood cartGood) {
        cartGoodRepository.delete(cartGood);
    }
}
