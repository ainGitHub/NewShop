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
    User user;

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

    public Order(User user, Date createDate, Double total_sum) {
        this.user = user;
        this.createDate = createDate;
        this.total_sum = total_sum;
    }

    public Order(User user, Address address, Date createDate, Double total_sum, String status, String pay_type, Integer count) {
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
