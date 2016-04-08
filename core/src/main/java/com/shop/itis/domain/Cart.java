package com.shop.itis.domain;

import javax.persistence.*;

@Entity
@Table(name = "cart",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"goodId", "username"}))
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", nullable = false)
    private UserInfo userInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goodId", nullable = false)
    private Good good;

    @Column
    Integer count;

    public Cart() {
    }

    public Cart(UserInfo userInfo, Good good, Integer count) {
        this.userInfo = userInfo;
        this.good = good;
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cart cart = (Cart) o;

        if (userInfo != null ? !userInfo.equals(cart.userInfo) : cart.userInfo != null) return false;
        return good != null ? good.equals(cart.good) : false;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userInfo != null ? userInfo.hashCode() : 0);
        result = 31 * result + (good != null ? good.hashCode() : 0);
        result = 31 * result + (count != null ? count.hashCode() : 0);
        return result;
    }
}
