package com.shop.itis.service;


import com.shop.itis.domain.Order;
import com.shop.itis.domain.User;
import com.shop.itis.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Transactional
    public void add(Order order) {
        orderRepository.add(order);
    }

    @Transactional
    public List<Order> getAllOrders(User user) {
        return orderRepository.getUserOrders(user.getUsername());
    }
}
