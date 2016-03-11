package com.shop.itis.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @Column(name = "id")   // обозначает имя колонки, соответствующей данному полю
    @GeneratedValue(strategy = GenerationType.AUTO)  // определяет способ генерации
    private Integer id;

    @Column(name = "city", length = 100)
    String city;

    @Column(name = "street", length = 100)
    String street;

    @Column(name = "house")
    Integer house;

    @Column(name = "flat")
    Integer flat;

    @Column(name = "index")
    Integer index;


    @Column(name = "area")
    String area;

    @ManyToOne
    User user;

    @OneToMany
    List<Order> orders;


    public Address() {
    }


    public Address(String city, String street, Integer house, Integer flat, Integer index, String area, User user) {
        this.city = city;
        this.street = street;
        this.house = house;
        this.flat = flat;
        this.index = index;
        this.area = area;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getHouse() {
        return house;
    }

    public void setHouse(Integer house) {
        this.house = house;
    }

    public Integer getFlat() {
        return flat;
    }

    public void setFlat(Integer flat) {
        this.flat = flat;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
