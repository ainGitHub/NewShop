package com.shop.itis.service;

import com.shop.itis.domain.Good;
import com.shop.itis.domain.User;
import com.shop.itis.domain.UserGoods;
import com.shop.itis.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;

    @Transactional
    public void update(UserGoods userGoods) {
        cartRepository.update(userGoods);
    }

    @Transactional
    public void update(Good good, User user, Integer count) {
        cartRepository.update(new UserGoods(user, good, count));
    }

    @Transactional
    public UserGoods getById(Long id, String username) {
        return cartRepository.getById(id, username);
    }

    @Transactional
    public List<UserGoods> getUserAllGoods(String username) {
        return cartRepository.getUserAllGoods(username);
    }

    @Transactional
    public void delete(Long goodId, String username) {
        UserGoods cart = cartRepository.getById(goodId, username);
        cartRepository.delete(cart);
    }

    @Transactional
    public void deleteAll(User user) {
        List<UserGoods> userGoodses = cartRepository.getUserAllGoods(user.getUsername());
        for (UserGoods g : userGoodses) {
            cartRepository.delete(g);
        }
    }
}
