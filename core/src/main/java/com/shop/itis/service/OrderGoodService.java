package com.shop.itis.service;

import com.shop.itis.domain.OrderGood;
import com.shop.itis.repository.OrderGoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class OrderGoodService {
    @Autowired
    OrderGoodRepository orderGoodRepository;

    @Transactional
    public void add(OrderGood orderGood) {
        orderGoodRepository.add(orderGood);
    }

    @Transactional
    public void update(OrderGood orderGood) {
        orderGoodRepository.update(orderGood);
    }

    @Transactional
    public void delete(OrderGood orderGood) {
        orderGoodRepository.delete(orderGood);
    }
}
