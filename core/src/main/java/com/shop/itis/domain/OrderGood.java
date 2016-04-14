package com.shop.itis.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class OrderGood implements Serializable, GoodWrapper {
    @Id
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "good")
    Good good;

    @Column(name = "count")
    Integer count = 1;

    @Id
    @ManyToOne
    Order order;

    public OrderGood() {
    }

    public OrderGood(Good good, Integer count, Order order) {
        this.good = good;
        this.count = count;
        this.order = order;
    }

    public Good getGood() {
        return good;
    }

    public void setGood(Good good) {
        this.good = good;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderGood orderGood = (OrderGood) o;

        return !(good != null ? !good.equals(orderGood.good) : orderGood.good != null);

    }

    @Override
    public int hashCode() {
        return good != null ? good.hashCode() : 0;
    }
}
