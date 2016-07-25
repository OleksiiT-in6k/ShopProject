package com.interlink.entity;

import javax.persistence.*;

/**
 * Created by employee on 7/25/16.
 */
@Entity
@Table(name = "items")
public class User {
    int id;
    int name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column
    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }
}
