package com.jwtly10.discountFinder.exceptions;

public class RateLimitException extends RuntimeException {

    public RateLimitException(String message) {
        super(message);
    }
    
}
