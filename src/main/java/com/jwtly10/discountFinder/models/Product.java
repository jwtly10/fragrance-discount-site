package com.jwtly10.discountFinder.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Product {
    String url;
    String brand;
    String itemName;
    double currentPrice;
    String type;
    double oldPrice;
    double saving;
    double discount;
    String imageUrl;
    boolean inStock;
    String size;
    Site site;
}
