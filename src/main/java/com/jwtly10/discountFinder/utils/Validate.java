package com.jwtly10.discountFinder.utils;

import com.jwtly10.discountFinder.models.Gender;
import com.jwtly10.discountFinder.models.Sort;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Validate {

    public static boolean isEmailValid(String email) {
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        return email.matches(regexPattern);
    }

    public static boolean isGender(String givenGender) {
        for (Gender gender : Gender.values()) {
            if (gender.name().equalsIgnoreCase(givenGender)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSort(String givenSort) {
        for (Sort sort : Sort.values()) {
            if (sort.name().equalsIgnoreCase(givenSort)) {
                return true;
            }
        }
        return false;
    }


}
