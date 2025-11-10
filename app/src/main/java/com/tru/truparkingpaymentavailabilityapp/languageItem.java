// File: LanguageItem.java
// Author: Zechariah Montgomery
// Description: Class representing a language option in the app.
// Date: [09/07/2025]

package com.tru.truparkingpaymentavailabilityapp;

/*
 * LanguageItem represents a single language this allows the user to select it as a option.
 * has the name of the language as a string
 */
public class languageItem
{
    String language;

    /**
     * Initializes a LanguageItem with the language name
     * @param language the name of the language
     */
    public languageItem(String language)
    {
        this.language = language;
    }

    /**
     * returns the name of the language.
     * @return language string
     */
    public String getLanguage()
    {
        return language;
    }
}
