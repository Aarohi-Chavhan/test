package com.caltex.test.model;

import lombok.Data;

@Data
public class ProductDetail {
    private String category;
    private String productName;
    private String date;
    private String units;
    private double value;

    public ProductDetail() {
    }

    public ProductDetail(String category, String productName, String date, String units, double value) {
        this.category = category;
        this.productName = productName;
        this.date = date;
        this.units = units;
        this.value = value;
    }
}
