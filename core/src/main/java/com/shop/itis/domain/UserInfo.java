package com.shop.itis.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserInfo implements Serializable {
    @Id
    @Column(name = "username", unique = true,
            nullable = false, length = 45)
    String username;

    @Column(name = "mail")
    String mail;

    @Column(name = "password")
    String password;

    @Column(name = "avatar", length = 100)
    String avatar = "default.png";

    @Column(name = "key")
    Integer key;

    @Column(name = "enabled")
    boolean enabled;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userInfo")
    Set<UserRoles> roles = new HashSet<UserRoles>(0);

    @ManyToOne
    Cart cart;

    @OneToMany(fetch = FetchType.LAZY)
    List<Order> orders = new ArrayList<Order>();

    public UserInfo() {
    }

    public UserInfo(String username, String password, String mail) {
        this.username = username;
        this.password = password;
        this.mail = mail;
    }

    public UserInfo(String username, String password, String avatar, Integer key) {
        this.username = username;
        this.password = password;
        this.avatar = avatar;
        this.key = key;
    }

    public UserInfo(String username, String mail, String password, String avatar, Set<UserRoles> roles, Cart cart) {
        this.username = username;
        this.mail = mail;
        this.password = password;
        this.avatar = avatar;
        this.roles = roles;
        this.cart = cart;
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

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
