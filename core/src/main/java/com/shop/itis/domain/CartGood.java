package com.shop.itis.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class CartGood implements Serializable, GoodWrapper {
    @Id
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "good")
    Good good;

    @Column(name = "count")
    Integer count = 1;

    @Id
    @ManyToOne
    @JoinColumn(name = "id")
    Cart cart;

    public CartGood() {
    }


    public CartGood(Good good, Integer count, Cart cart) {
        this.good = good;
        this.count = count;
        this.cart = cart;
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

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartGood cartGood = (CartGood) o;

        if (good != null ? !good.equals(cartGood.good) : cartGood.good != null) return false;
        return !(cart != null ? !cart.equals(cartGood.cart) : cartGood.cart != null);

    }

    @Override
    public int hashCode() {
        int result = good != null ? good.hashCode() : 0;
        result = 31 * result + (cart != null ? cart.hashCode() : 0);
        return result;
    }
}
