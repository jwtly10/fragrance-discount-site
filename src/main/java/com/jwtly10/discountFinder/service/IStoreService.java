package com.jwtly10.discountFinder.service;

import com.jwtly10.discountFinder.models.Product;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

import java.util.List;

@CacheConfig(cacheNames = "products")
public interface IStoreService {
    @Caching(
            cacheable = {
                    @Cacheable(value = "mensProducts", condition = "#gender.equals('mens')"),
                    @Cacheable(value = "womensProducts", condition = "#gender.equals('womens')")
            }
    )
    List<Product> getDiscountedProducts(String apiUrl, String gender);

    void clearCache();

}
