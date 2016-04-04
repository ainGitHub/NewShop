package com.shop.itis.domain;


import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "cart")
public class Cart {
    @EmbeddedId
    CartId cartId;

    @Column
    Integer count;

    public Cart() {
    }

    public Cart(User user, Good good, Integer count) {
        this.cartId = new CartId(user.getUsername(), good.getId());
        this.count = count;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public CartId getCartId() {
        return cartId;
    }

    public void setCartId(CartId cartId) {
        this.cartId = cartId;
    }
}
