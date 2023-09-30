package com.jwtly10.discountFinder.service;

import com.jwtly10.discountFinder.models.Product;

import java.util.List;

public interface IStoreService {
    List<Product> getDiscountedProducts(String apiUrl, String gender);
}
