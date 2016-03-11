package com.shop.itis.domain;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @Column(name = "id")   // обозначает имя колонки, соответствующей данному полю
    @GeneratedValue(strategy = GenerationType.AUTO)  // определяет способ генерации
    private Integer id;

    @Column(name = "count")
    Integer count;

    @OneToMany(cascade = CascadeType.REFRESH,
            fetch = FetchType.LAZY)
    List<Good> goods;

    @ManyToOne
    User user;


    public Cart() {
    }

    public Cart(Integer count, User user, List<Good> goods) {
        this.count = count;
        this.user = user;
        this.goods = goods;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Good> getGoods() {
        return goods;
    }

    public void setGoods(List<Good> goods) {
        this.goods = goods;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
