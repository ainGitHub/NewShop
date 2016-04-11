package com.shop.itis.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private Long id;

    @OneToMany
    @JoinColumn(name = "cart")
    Set<GoodsWrapper> goodsWrapper = new HashSet<GoodsWrapper>();

    @ManyToOne
    UserInfo userInfo;

    @Column
    Double sum = 0.0;

    @Column
    Integer goodsCount = 0;

    public Cart() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<GoodsWrapper> getGoodsWrapper() {
        return goodsWrapper;
    }

    public void setGoodsWrapper(Set<GoodsWrapper> goodsWrapper) {
        this.goodsWrapper = goodsWrapper;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
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
