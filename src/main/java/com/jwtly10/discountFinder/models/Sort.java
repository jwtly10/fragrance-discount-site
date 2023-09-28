package com.jwtly10.discountFinder.models;

public enum Sort {
    max_discount,
    max_price,
    none;

    public class Default {
        public static final String DEFAULT_SORT = "none";
    }
}
