package com.shop.itis.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @Column(name = "id")   // ���������� ��� �������, ��������������� ������� ����
    @GeneratedValue(strategy = GenerationType.AUTO)  // ���������� ������ ���������
    private Long id;

    @Column(name = "name", length = 100)
    String name;

    @Column(name = "alias", length = 20)
    String alias;

    @ManyToOne
    Category parent;

    @OneToMany
    List<Category> categories;


    @OneToMany
    List<Good> goods;

    public Category() {
    }

    public Category(String name, String alias) {
        this.name = name;
        this.alias = alias;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Good> getGoods() {
        return goods;
    }

    public void setGoods(List<Good> goods) {
        this.goods = goods;
    }
}
