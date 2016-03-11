package com.shop.itis.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "username", unique = true,
            nullable = false, length = 45)
    String username;

    @Column(name = "mail")
    String mail;

    @Column(name = "password")
    String password;

    @Column(name = "avatar", length = 100)
    String avatar;

    @Column(name = "name", length = 100)
    String name;

    @Column(name = "cheak")
    Boolean cheak;

    @Column(name = "status")
    Boolean status = false;

    @Column(name = "key")
    Integer key;

    @Column(name = "enabled")
    boolean enabled;

    @OneToMany
    List<Order> orders;

    @ManyToOne
    Cart cart;

    @OneToMany
    List<Address> address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    Set<UserRoles> roles = new HashSet<UserRoles>(0);


    public User() {
    }

    public User(String username, String password, String mail) {
        this.username = username;
        this.password = password;
        this.mail = mail;
    }

    public User(String username, String password, String avatar, String name, Boolean cheak, Integer key) {
        this.username = username;
        this.password = password;
        this.avatar = avatar;
        this.name = name;
        this.cheak = cheak;
        this.key = key;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String login) {
        this.username = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String hash_pass) {
        this.password = hash_pass;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getCheak() {
        return cheak;
    }

    public void setCheak(Boolean cheak) {
        this.cheak = cheak;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<UserRoles> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRoles> roles) {
        this.roles = roles;
    }
}
