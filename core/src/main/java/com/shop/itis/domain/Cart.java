package com.shop.itis.domain;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private Long id;

    @OneToMany(mappedBy = "cart")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    Set<CartGood> cartGood = new HashSet<CartGood>();

    @Column
    Double sum = 0.0;

    @Column
    Integer goodsCount = 0;

    public Cart() {
    }

    public Set<CartGood> getCartGood() {
        return cartGood;
    }

    public void setCartGood(Set<CartGood> cartGood) {
        this.cartGood = cartGood;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        if (sum < 0.0)
            this.sum = 0.0;
        else
            this.sum = sum;
    }

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        if (goodsCount < 0)
            this.goodsCount = 0;
        else
            this.goodsCount = goodsCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cart cart = (Cart) o;

        return !(id != null ? !id.equals(cart.id) : cart.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
