package com.github.cjgd.model.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity @Data
@Table(name="tbl_product")
public class Product {

    public Product() {
    }

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
        this.createdOn = new Date();
        this.enabled = true;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private double price; // in euros

    private Date createdOn;

    private boolean enabled;

}
