package com.interlink.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by employee on 7/22/16.
 */
@Entity
@Table(name = "items")
public class Item {

    private int id;
    private String name;
    private int number;
    private Category category;
    private BigDecimal price;


    public Item(String name, int number, BigDecimal price) {
        this.name = name;
        this.number = number;
        this.price = price;
    }

    public Item() {
    }

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
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Column
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @ManyToOne
    @JoinColumn(name = "category_id")
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (number != item.number) return false;
        if (name != null ? !name.equals(item.name) : item.name != null) return false;
        if (category != null ? !category.equals(item.category) : item.category != null) return false;
        return price != null ? price.equals(item.price) : item.price == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + number;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}
