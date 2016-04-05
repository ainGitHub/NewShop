package com.shop.itis.domain;

import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty(message = "Город должен быть не пустым")
    @Column(name = "city", length = 100)
    String city;

    @NotEmpty(message = "Улица должна быть не пустой")
    @Column(name = "street", length = 100)
    String street;

    @NotNull(message = "Дом должен быть не пустым")
    @Column(name = "house", length = 100)
    Integer house;

    @Column(name = "flat", length = 100)
    Integer flat;

    @Column(name = "index", length = 100)
    Integer index;


    public Address() {
    }


    public Address(String city, String street, Integer house, Integer flat, Integer index) {
        this.city = city;
        this.street = street;
        this.house = house;
        this.flat = flat;
        this.index = index;
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
}
