package com.interlink.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by Алекс on 24.07.2016.
 */
@Entity
@Table(name = "orders")
public class Order {
    int id;
    int user;
    BigDecimal totalSum;
    LocalDateTime dateTime;

    public Order() {
    }

    public Order(LocalDateTime dateTime, int user, BigDecimal totalSum) {
        this.dateTime = dateTime;
        this.user = user;
        this.totalSum = totalSum;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    @Column(name = "dateTime")
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


}
