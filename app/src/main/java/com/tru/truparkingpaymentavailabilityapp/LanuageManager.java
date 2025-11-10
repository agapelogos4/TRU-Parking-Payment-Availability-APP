// File: LanuageManager.java
// Author: Zechariah Montgomery
// Description: sets app language (locale) by reading from SharedPreferences
//              this class also sets the correct locale configuration to the app's resources
// Date: [09/07/2025]

package com.tru.truparkingpaymentavailabilityapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import java.util.Locale;

/*
 * LanuageManager sets the saved language preference
 * from SharedPreferences to the users current configuration.
 *
 * it will update the Locale and resource (strings.xml) so that all strings
 * show the user's chosen language when activities are reloaded.
 */
public class LanuageManager
{
    /**
     * this will read the saved language preference and apply it to the app context (currently running)
     * it will update the Locale so that text is displayed in the selected language.
     *
     * @param context The activity context used to access resources.
     */
    public static void applySavedLanguage(Context context)
    {
        //This will grab the language stored in shared preferences its default value is english if there is nothing stored in shared preferences.
        SharedPreferences preferences = context.getSharedPreferences("parking_preferences", Context.MODE_PRIVATE);
        String language = preferences.getString("language", "English");

        //each String file has a code to determine what language it is you set this in androids translation editor so I grab the code corresponding to the string
        String code = "en";// default english
        if (language.equalsIgnoreCase("French"))
        {
            code = "fr";
        }
        else if (language.equalsIgnoreCase("Spanish"))
        {
            code = "es";
        }

        //I set the the language or locale to the code indicating what strings.xml file I want to use then I set the object to the default locale
        Locale locale = new Locale(code);
        Locale.setDefault(locale);

        //this will apply our changes to the apps configuration.
        Configuration config = new Configuration();
        config.setLocale(locale);

        //with the configuration changed we need to update it so our changes can come into effect when the activity or context is restarted.
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
    }
}
