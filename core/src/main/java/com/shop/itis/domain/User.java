package com.shop.itis.domain;

import javax.persistence.*;
import java.util.ArrayList;
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
    String avatar = "default.png";


    @Column(name = "key")
    Integer key;

    @Column(name = "enabled")
    boolean enabled;

    @OneToMany
    List<Address> address = new ArrayList<Address>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    Set<UserRoles> roles = new HashSet<UserRoles>(0);


    public User() {
    }

    public User(String username, String password, String mail) {
        this.username = username;
        this.password = password;
        this.mail = mail;
    }

    public User(String username, String password, String avatar, Integer key) {
        this.username = username;
        this.password = password;
        this.avatar = avatar;
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

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
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
