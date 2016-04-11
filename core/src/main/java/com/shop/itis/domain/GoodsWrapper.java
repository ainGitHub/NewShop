package com.shop.itis.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "good_wrapper",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"cart", "good"}
        ))
public class GoodsWrapper implements Serializable {
    @Id
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "good")
    Good good;

    @Column(name = "count")
    Integer count = 1;

    @Id
    @ManyToOne
    Cart cart;

    public GoodsWrapper() {
    }


    public GoodsWrapper(Good good, Integer count, Cart cart) {
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

        GoodsWrapper that = (GoodsWrapper) o;

        if (good != null ? !good.equals(that.good) : that.good != null) return false;
        return !(cart != null ? !cart.equals(that.cart) : that.cart != null);

    }

    @Override
    public int hashCode() {
        int result = good != null ? good.hashCode() : 0;
        result = 31 * result + (cart != null ? cart.hashCode() : 0);
        return result;
    }
}
