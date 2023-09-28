package com.jwtly10.discountFinder.service;

import com.jwtly10.discountFinder.models.Product;
import com.jwtly10.discountFinder.models.Sort;

import java.util.List;

public class SortService {

    public static List<Product> sort(List<Product> products, Sort sort) {
        return switch (sort) {
            case max_discount -> sortByDiscount(products);
            case max_price -> sortByPrice(products);
            case none -> products;
        };
    }

    private static List<Product> sortByDiscount(List<Product> products) {
        products.sort((p1, p2) -> Double.compare(p2.getDiscount(), p1.getDiscount()));
        return products;
    }

    private static List<Product> sortByPrice(List<Product> products) {
        products.sort((p1, p2) -> Double.compare(p2.getCurrentPrice(), p1.getCurrentPrice()));
        return products;
    }
}
