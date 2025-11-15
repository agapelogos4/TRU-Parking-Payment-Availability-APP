package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
// This class tests out the methods for the PaymentValidation class
// Created By Armaan Bains T00711438

public class CardPaymentValidationTest
{

    CardPaymentValidation validate = new CardPaymentValidation();

    @Test
    void testValidVisa()
    {
        assertTrue(validate.isValidVisa("4111111111111111")); // Valid Visa
        assertTrue(validate.isValidVisa("4012888888881881")); // Valid Visa
    }

    @Test
    void testInvalidVisa()
    {
        assertFalse(validate.isValidVisa("4111111111111"));  // This is the wrong length
        assertFalse(validate.isValidVisa("5111111111111111")); // This starts with an invalid number
        assertFalse(validate.isValidVisa("4111111111111112")); // This one should fail the luhn check
    }

    @Test
    void testValidMasterCard()
    {
        assertTrue(validate.isValidMasterCard("5555555555554444")); // Valid MasterCard with first range (51-55)
        assertTrue(validate.isValidMasterCard("2221000000000009")); // Valid Mastercard with second range (2221-2720)
    }

    @Test
    void testInvalidMasterCard()
    {
        assertFalse(validate.isValidMasterCard("550000000000000"));  // This is the wrong length
        assertFalse(validate.isValidMasterCard("341111111111111")); // This has the wrong prefix
        assertFalse(validate.isValidMasterCard("5555555555554445")); // This should fail the luhn check
    }

    @Test
    void testValidAmericanExpress() {
        assertTrue(validate.isValidAmex("378282246310005")); // Valid AmericanExpress CardNumber
        assertTrue(validate.isValidAmex("371449635398431")); // Valid AmericanExpress CardNumber
    }

    @Test
    void testInvalidAmericanExpress()
    {
        assertFalse(validate.isValidAmex("37828224631000"));  // This is the wrong length
        assertFalse(validate.isValidAmex("4111111111111111")); // This has thw wrong prefix
        assertFalse(validate.isValidAmex("378282246310006")); // This should fail the luhn test
    }

    @Test
    void testIsValidCard()
    {
        // Should return true if the card is valid for any of the 3 methods
        assertTrue(validate.isValidCard("4111111111111111")); // Valid Visa
        assertTrue(validate.isValidCard("5555555555554444")); // Valid MasterCard
        assertTrue(validate.isValidCard("378282246310005"));  // Valid American Express
    }

    @Test
    void testIsNotValidCard()
    {
        assertFalse(validate.isValidCard("1234567890123456")); // Random Invalid numbers
        assertFalse(validate.isValidCard("")); // Letters and Numbers Mixed
        assertFalse(validate.isValidCard("abcdefghijklmnop")); // Letters are added instead of numbers
    }

    @Test
    void testCardInputErrorEmpty()
    {
        assertEquals("Error: Card number cannot be empty.", validate.checkCardInputError(""));
    }

    @Test
    void testCardInputErrorNonNumeric()
    {
        assertEquals("Error: Card number must contain only digits.", validate.checkCardInputError("abc123"));
    }

    @Test
    void testCardInputErrorInvalid()
    {
        assertEquals("Error: Card is not valid. Please check the number and try again.",
                validate.checkCardInputError("4111111111111112"));
    }

    @Test
    void testCardInputErrorValidNumber()
    {
        assertEquals("Card is valid.", validate.checkCardInputError("4111111111111111"));
    }
}