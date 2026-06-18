package com.shubham.core.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {
    private UUID id;
    private String name;
    private BigDecimal price;
    private Integer qunaltity;

    public Product() {
    }

    public Product(UUID id, String name, BigDecimal price, Integer qunaltity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.qunaltity = qunaltity;
    }

    public Product(UUID id, Integer qunaltity) {
        this.id = id;
        this.qunaltity = qunaltity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQunaltity() {
        return qunaltity;
    }

    public void setQunaltity(Integer qunaltity) {
        this.qunaltity = qunaltity;
    }
}
