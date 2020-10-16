package com.github.cjgd.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity @Data
@Table(name = "tbl_order")
public class Order {

    public Order() {
    }

    public Order(String buyerEmail) {
        this.buyerEmail = buyerEmail;
        this.createdOn = new Date();
        this.items = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String buyerEmail;

    private Date createdOn;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;

    public double totalPrice() {
        double tot = 0;
        if (items != null) {
            for (OrderItem x : items) {
                tot += x.getProduct().getPrice();
            }
        }
        return tot;
    }
}
