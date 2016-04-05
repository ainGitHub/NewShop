package com.shop.itis.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CartId implements Serializable {
    String userId;
    Long goodId;

    public CartId(String userId, Long goodId) {
        this.userId = userId;
        this.goodId = goodId;
    }

    public CartId() {
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getGoodId() {
        return goodId;
    }

    public void setGoodId(Long goodId) {
        this.goodId = goodId;
    }
}
