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

    @ManyToOne
    UserInfo userInfo;

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

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (city != null ? !city.equals(address.city) : address.city != null) return false;
        if (street != null ? !street.equals(address.street) : address.street != null) return false;
        if (house != null ? !house.equals(address.house) : address.house != null) return false;
        if (flat != null ? !flat.equals(address.flat) : address.flat != null) return false;
        if (index != null ? !index.equals(address.index) : address.index != null) return false;
        return !(userInfo != null ? !userInfo.equals(address.userInfo) : address.userInfo != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (house != null ? house.hashCode() : 0);
        result = 31 * result + (flat != null ? flat.hashCode() : 0);
        result = 31 * result + (index != null ? index.hashCode() : 0);
        result = 31 * result + (userInfo != null ? userInfo.hashCode() : 0);
        return result;
    }
}
