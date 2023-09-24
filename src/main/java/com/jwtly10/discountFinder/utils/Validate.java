package com.jwtly10.discountFinder.utils;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Validate {

    public static boolean isEmailValid(String email) {
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        return email.matches(regexPattern);
    }

    
}
