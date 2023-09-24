package com.jwtly10.discountFinder.utilsTests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.jwtly10.discountFinder.utils.Validate;

public class ValidateTests {

    @Test
    public void testIsEmailValid() {
        assertTrue(Validate.isEmailValid("test@test.com"));
        assertTrue(Validate.isEmailValid("test_393@test.com"));
        assertTrue(Validate.isEmailValid("test-393@test.com"));
        assertTrue(Validate.isEmailValid("test.3@test.com"));

        assertFalse(Validate.isEmailValid("test@test"));
        assertFalse(Validate.isEmailValid(".test@test"));
        assertFalse(Validate.isEmailValid("_test@test"));
    }   

}
