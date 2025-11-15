package org.example;

/**
 * This class creates methods that check the validity of the card being used to pay in the Parking App
 * There are different card checking methods for the different types of cards such as Visa and Mastercard
 * Created By Armaan Bains T00711438
 */

public class CardPaymentValidation
{
    public String checkCardInputError(String cardNum)
    {
        // This makes sure the input entered is not empty
        if (cardNum == null || cardNum.trim().isEmpty())
        {
            return "Error: Card number cannot be empty.";
        }

        // This takes out all the spaces that might have been entered
        cardNum = cardNum.replaceAll("\\s+", "");

        // This makes sure only numbers were entered
        if (!cardNum.matches("\\d+"))
        {
            return "Error: Card number must contain only digits.";
        }

        // This uses the validation method to see if the card is valid
        if (isValidCard(cardNum))
        {
            return "Card is valid.";
        }

        // This is what happens if the validation method fails
        else
        {
            return "Error: Card is not valid. Please check the number and try again.";
        }
    }

    // This calls the 3 card methods and sees if the input is valid for any of them
    public boolean isValidCard(String cardNum)
    {
        if (cardNum == null)
        {
            return false;
        }

        cardNum = cardNum.replaceAll("\\s+", ""); //This removes any spaces that may have been entered

        // Makes sure the card contains only numbers. Its redundant but required for the testing
        if (!cardNum.matches("\\d+"))
        {
            return false;
        }

        // This sees if the length of the card is valid
        if (cardNum.length() < 13 || cardNum.length() > 19)
        {
            return false;
        }

        return isValidVisa(cardNum) || isValidMasterCard(cardNum) || isValidAmex(cardNum);
    }

    // Method that checks if a Visa Card is Valid
    // Valid Visa cards start with 4 and are either 13, 16 (most common), or 19 digits long
    // It is also put through the Luhn Check to confirm it is valid
    public boolean isValidVisa(String cardNum)
    {
        if ((cardNum.startsWith("4")) &&
                (cardNum.length() == 13 || cardNum.length() == 16 || cardNum.length() == 19) &&
                luhnCheck(cardNum) == true)
        {
            return true;
        }

        else
        {
            return false;
        }
    }

    // Method that checks if a MasterCard is Valid
    // Valid MasterCards start with numbers between 51 and 55 plus 2221 through 2720
    // They are either 16 (most common) or 19 digits long
    // It is also put through the Luhn Check to confirm it is valid
    public boolean isValidMasterCard(String cardNum)
    {
        int prefix2 = Integer.parseInt(cardNum.substring(0, 2));
        int prefix4 = Integer.parseInt(cardNum.substring(0, 4));

        boolean prefixesMatch = (prefix2 >= 51 && prefix2 <= 55) ||
                (prefix4 >= 2221 && prefix4 <= 2720);

        if (prefixesMatch && (cardNum.length() == 16 || cardNum.length() == 19) && luhnCheck(cardNum) == true)
        {
            return true;
        }

        else
        {
            return false;
        }
    }

    // Method that checks if an AmericanExpress Card is Valid
    // Valid AmericanExpress Cards start with either 34 or 37
    // They are exactly 15 digits in length
    // It is also put through the Luhn Check to confirm it is valid
    public boolean isValidAmex(String cardNum)
    {
        if ((cardNum.startsWith("34") || cardNum.startsWith("37")) &&
                cardNum.length() == 15 && luhnCheck(cardNum) == true)
        {
            return true;
        }

        else
        {
            return false;
        }
    }

    // LUHN check algorithm that is used universally by the big card companies to verify their cards
    // It works by doubling every second digit from the right, sum all the resulting digits,
    // (if a number is > 9, it sums its digits. ex: 11 becomes 1 + 1 = 2.
    // This is done by subtracting 9 from the double-digit number: 11 - 9 = 2)
    // It then checks to see if the sum is divisble by 10 which it should be to pass the test
    private boolean luhnCheck(String cardNum)
    {
        int sum = 0;
        boolean alt = false;

        for (int i = cardNum.length() - 1; i >= 0; i--)
        {
            int digit = Character.getNumericValue(cardNum.charAt(i));

            if (alt)
            {
                digit *= 2;
                if (digit > 9)
                {
                    digit -= 9;
                }
            }

            sum += digit;
            alt = !alt; // This flips the boolean back and forth so it does every second number
        }

        return (sum % 10 == 0);
    }
}