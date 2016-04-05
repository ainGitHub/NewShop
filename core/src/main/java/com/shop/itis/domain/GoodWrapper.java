package com.shop.itis.domain;

public class GoodWrapper {
    Good good;
    Integer count;

    public GoodWrapper() {
    }

    public GoodWrapper(Good good, Integer count) {
        this.good = good;
        this.count = count;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoodWrapper that = (GoodWrapper) o;

        return good != null ? good.equals(that.good) : that.good != null;

    }

    @Override
    public int hashCode() {
        return good != null ? good.hashCode() : 0;
    }
}
