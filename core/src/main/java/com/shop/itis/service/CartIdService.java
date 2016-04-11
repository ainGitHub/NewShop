package com.shop.itis.service;

import com.shop.itis.domain.Cart;
import com.shop.itis.domain.Good;
import com.shop.itis.domain.GoodsWrapper;
import com.shop.itis.repository.CartIdRepository;
import org.hibernate.NonUniqueObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CartIdService {
    @Autowired
    CartIdRepository cartIdRepository;

    @Transactional
    public void add(GoodsWrapper goodsWrapper) {
        try {
            cartIdRepository.add(goodsWrapper);
        } catch (NonUniqueObjectException e) {
            cartIdRepository.merge(goodsWrapper);
        }
    }

    @Transactional
    public void flush() {
        cartIdRepository.flush();
    }

    @Transactional
    public void delete(Cart cart, Good good) {
        GoodsWrapper goodsWrapper = new GoodsWrapper(good, 1, cart);
        cartIdRepository.delete(goodsWrapper);
    }
}
