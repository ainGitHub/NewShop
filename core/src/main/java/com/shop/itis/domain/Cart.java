package com.shop.itis.domain;



import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "count")
    Integer count;

    @ManyToMany
    @JoinTable(name = "cart_good")
    Set<Good> goods = new HashSet<Good>();

    @ManyToOne
    User user;


    public Cart() {
    }

    public Cart(Integer count, User user, Set<Good> goods) {
        this.count = count;
        this.user = user;
        this.goods = goods;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Set<Good> getGoods() {
        return goods;
    }

    public void setGoods(Set<Good> goods) {
        this.goods = goods;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
