package com.shop.itis.domain;



import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    UserInfo userInfo;

    @ManyToOne
    Address address;

    @Column(name = "date")
    Date createDate;

    @Column(name = "total_sum")
    Double total_sum;

    @Column(name = "status", length = 100)
    String status;

    @Column(name = "pay_type", length = 100)
    String pay_type;

    @ManyToOne
    Good good;

    @Column
    Integer count;

    public Order() {
    }

    public Order(UserInfo userInfo, Date createDate, Double total_sum) {
        this.userInfo = userInfo;
        this.createDate = createDate;
        this.total_sum = total_sum;
    }

    public Order(UserInfo userInfo, Address address, Date createDate, Double total_sum, String status, String pay_type, Integer count) {
        this.userInfo = userInfo;
        this.address = address;
        this.createDate = createDate;
        this.total_sum = total_sum;
        this.status = status;
        this.pay_type = pay_type;
        this.count = count;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Double getTotal_sum() {
        return total_sum;
    }

    public void setTotal_sum(Double total_sum) {
        this.total_sum = total_sum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPay_type() {
        return pay_type;
    }

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
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
}
