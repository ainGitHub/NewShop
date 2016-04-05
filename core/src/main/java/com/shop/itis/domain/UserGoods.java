package com.shop.itis.domain;


import javax.persistence.*;

@Entity
@Table(name = "user_goods")
public class UserGoods {
    @EmbeddedId
    CartId cartId;

    @ManyToOne
    Good good;

    @ManyToOne
    User user;

    @Column
    Integer count;

    public UserGoods() {
    }

    public UserGoods(User user, Good good, Integer count) {
        this.cartId = new CartId(user.getUsername(), good.getId());
        this.count = count;
        this.user = user;
        this.good = good;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    private CartId getCartId() {
        return cartId;
    }

    public Good getGood() {
        return good;
    }

    public void setGood(Good good) {
        this.good = good;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
