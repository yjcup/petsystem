package com.ruoyi.system.domain;

import java.util.Objects;

public class OrderRec {
    private int userId;
    private int productId;

    public OrderRec(int userId, int productId) {
        this.userId = userId;
        this.productId = productId;
    }

    public int getUserId() {
        return userId;
    }

    public int getProductId() {
        return productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderRec orderRec = (OrderRec) o;
        return userId == orderRec.userId &&
                productId == orderRec.productId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, productId);
    }
}
