package com.jwtly10.discountFinder.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jwtly10.discountFinder.models.Product;
import com.jwtly10.discountFinder.service.ThePerfumeShopService;

@RestController
@RequestMapping("/api/v1/stores")
public class ThePerfumeShopController {

    @Autowired
    private ThePerfumeShopService thePerfumeShopService;
    
    @GetMapping("/theperfumeshop/{gender}")
    @ResponseBody
    public List<Product> getThePerfumeShopProducts(@PathVariable String gender) {
        return thePerfumeShopService.getDiscountedProducts(gender);
    }

}
