package com.shop.itis.service;


import com.shop.itis.domain.*;
import com.shop.itis.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Transactional
    public void add(User user, Address address, Set<GoodWrapper> goodWrappers, String status, String payType) {
        for (GoodWrapper goodWrapper : goodWrappers) {
            Good g = goodWrapper.getGood();
            Order order = new Order(user, address, new Date(),
                    g.getPrice() * goodWrapper.getCount(),
                    status, payType, goodWrapper.getCount());
            order.setGood(g);
            orderRepository.add(order);
        }
    }

    @Transactional
    public List<Order> getAllOrders(User user) {
        return orderRepository.getUserOrders(user.getUsername());
    }
}
