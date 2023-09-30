package com.jwtly10.discountFinder.service;

import com.jwtly10.discountFinder.exceptions.SortServiceException;
import com.jwtly10.discountFinder.models.Product;
import com.jwtly10.discountFinder.models.Sort;
import com.jwtly10.discountFinder.utils.Validate;

import java.util.List;

public class SortService {

    public static List<Product> sort(List<Product> products, String sort) {
        if (!Validate.isSort(sort)) {
            throw new SortServiceException("Invalid Sort: " + sort);
        }
        return switch (Sort.valueOf(sort)) {
            case max_discount -> sortByDiscount(products);
            case max_price -> sortByPrice(products);
            case max_saving -> sortBySaving(products);
            case none -> products;
        };
    }

    private static List<Product> sortBySaving(List<Product> products) {
        products.sort((p1, p2) -> Double.compare(p2.getSaving(), p1.getSaving()));
        return products;
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
