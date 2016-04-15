package com.shop.itis.service;


import com.shop.itis.domain.Good;
import com.shop.itis.repository.GoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class GoodService {
    @Autowired
    private GoodRepository goodRepository;

    @Transactional
    public void add(Good good) {
        goodRepository.add(good);
    }

    @Transactional
    public List<Good> getAllGoods() {
        return goodRepository.getAllGoods();
    }

    @Transactional
    public Good getGoodById(Long id) {
        return goodRepository.getGoodById(id);
    }

    @Transactional
    public void delete(Good good) {
        goodRepository.delete(good);
    }

    @Transactional
    public void update() {
        goodRepository.update();
    }

    @Transactional
    public List<Good> getGoodsLikeName(String name) {
        return goodRepository.getGoodsLikeName(name);
    }

    @Transactional
    public List<Good> getGoodsByCategoryId(Long categoryId) {
        return goodRepository.getGoodByCategory(categoryId);
    }

    @Transactional
    public List<Good> getGoodsByCategoryPriceName(Long categoryId, Double min, Double max, String name) {
        return goodRepository.getGoodsByCategoryPriceName(categoryId, min, max, name);
    }

    @Transactional
    public List<Good> getGoodByPriceName(Double min, Double max, String name) {
        return goodRepository.getGoodByPriceName(min, max, name);
    }
}
