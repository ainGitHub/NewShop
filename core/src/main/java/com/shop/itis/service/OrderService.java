package com.shop.itis.service;


import com.shop.itis.domain.Order;
import com.shop.itis.domain.OrderGood;
import com.shop.itis.domain.UserInfo;
import com.shop.itis.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderGoodService orderGoodService;

    @Transactional
    public void add(Order order) {
        orderRepository.add(order);
    }

    @Transactional
    public List<Order> getAllOrders(UserInfo userInfo) {
        return orderRepository.getUserOrders(userInfo.getUsername());
    }

    @Transactional
    public void deleteOrder(Order order) {
        for (OrderGood g : order.getOrderGoods()) {
            orderGoodService.delete(g);
        }
        orderRepository.delete(order);
    }

    @Transactional
    public Order getById(Long id) {
        return orderRepository.getById(id);
    }

}
