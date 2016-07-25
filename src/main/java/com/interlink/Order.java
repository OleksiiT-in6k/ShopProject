package com.interlink;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by Алекс on 24.07.2016.
 */
public class Order {
    int id;
    int userId;
    BigDecimal totalSum;
    LocalDateTime dateTime;

    public Order() {
    }

    public Order(LocalDateTime dateTime, int userId, BigDecimal totalSum) {
        this.dateTime = dateTime;
        this.userId = userId;
        this.totalSum = totalSum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public BigDecimal getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(BigDecimal totalSum) {
        this.totalSum = totalSum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (userId != order.userId) return false;
        return dateTime != null ? dateTime.equals(order.dateTime) : order.dateTime == null;

    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        return result;
    }
}
