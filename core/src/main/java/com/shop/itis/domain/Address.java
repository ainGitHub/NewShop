package com.shop.itis.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @Column(name = "id")   // ���������� ��� �������, ��������������� ������� ����
    @GeneratedValue(strategy = GenerationType.AUTO)  // ���������� ������ ���������
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
