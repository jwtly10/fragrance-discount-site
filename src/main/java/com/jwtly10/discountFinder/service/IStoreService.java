package com.jwtly10.discountFinder.service;

import java.util.List;

import com.jwtly10.discountFinder.models.Product;

public interface IStoreService {
    public List<Product> getDiscountedProducts(String gender);
}
