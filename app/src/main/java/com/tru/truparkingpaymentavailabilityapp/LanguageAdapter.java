// File: LanguageAdapter.java
// Author: Zechariah Montgomery
// Description: Adapter for displaying a list of available languages in a Recyclerview seen on the LanguageFragment which is a bottom sheet.
//              This class controls selection of a language and it saves it in SharedPreferences, sets the text via LanguageManager dimisses the bottom sheet, and recreating the activity.
// Date: [09/07/2025]

package com.tru.truparkingpaymentavailabilityapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/*
 * LanguageAdapter binds a list of languageItem objects to a Recyclerview.
 * Each item shows the language name and handles user selection:
 * - Saves the selected language to SharedPreferences
 * - Applies the new langauge using LanguageManager
 * - Dismisses the language bottom sheet
 * - Recreates the activity to apply new strings (this will restart the activity running settings.)
 */

public class LanguageAdapter extends RecyclerView.Adapter<LangaugeMyViewHolder>
{
    Context context;
    List<languageItem> items;

    /**
     * This constructor initializes the adapter with context and list of language items.
     * @param context the hosting activity context see https://www.geeksforgeeks.org/android/what-is-context-in-android/
     * @param items list of languageItem objects
     */
    public LanguageAdapter(Context context, List<languageItem> items)
    {
        this.context = context;
        this.items = items;
    }

    /**
     * Inflates (meaning take the item and store the reference [if you dont know much about recyclerViews look them up online]) the item_view layout (langaugeItem) and creates a ViewHolder (See LanguageMyViewHolder class).
     * @param parent the parent ViewGroup
     * @param viewType view type of the new View
     * @return LanguageMyViewHolder containing references to item views
     */
    @NonNull
    @Override
    public LangaugeMyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return new LangaugeMyViewHolder(LayoutInflater.from(context).inflate(R.layout.language_item_view, parent, false));
    }

    /**
     * combines the languageItem data to the ViewHolder.
     * Sets the language name and click listener to handle selection.
     * @param holder the ViewHolder (See LanguageMyViewHolder class).
     * @param position the position in the list
     */
    @Override
    public void onBindViewHolder(@NonNull LangaugeMyViewHolder holder, int position)
    {

        String selectedLanguage = items.get(position).getLanguage();//this basicly grabs the position which is the value the item is located in the list and gets the language of that item using getLanguage() method. (see languageItem class)
        holder.langaugeView.setText(selectedLanguage);//sets the text of each item of each LanguageItem that exist

        //if one of the items is clicked then it will save the selected language to shared preferences and then tell the system which strings.xml file it should be using based of the language selected via the LanguageManager class then close the bottom sheet
        holder.itemView.setOnClickListener(v ->
        {
            if (context instanceof androidx.fragment.app.FragmentActivity) //check the context to see if it is a Fragment activity because if we try to cast to a fragment activity it can crash without this check
            {
                //we need to cast the context becuase if its not of the correct type we will not be able to access the FragmentManager and if we cant access the fragment manger we cant dismiss a bottomsheet.
                androidx.fragment.app.FragmentActivity activity = (androidx.fragment.app.FragmentActivity) context;

                try
                {
                    context.getSharedPreferences("parking_preferences", Context.MODE_PRIVATE).edit().putString("language", selectedLanguage).apply();

                    //set the language the system is currently using
                    LanuageManager.applySavedLanguage(context);

                    //dismiss the bottom sheet
                    androidx.fragment.app.Fragment fragment = activity.getSupportFragmentManager().findFragmentByTag("language_bottomsheet"); //I learned about the fragmentManger here: https://developer.android.com/guide/fragments/fragmentmanager
                    if (fragment instanceof com.google.android.material.bottomsheet.BottomSheetDialogFragment)
                    {
                        ((com.google.android.material.bottomsheet.BottomSheetDialogFragment) fragment).dismiss();
                    }

                    //recreate activity to reload all strings with new locale (locale is the regional and language setting that the app uses)
                    activity.recreate();
                }
                catch (Exception e)
                {
                    android.util.Log.e("LanguageAdapter", "Error selecting language: " + selectedLanguage, e);
                }
            }
        });

    }

    /*
     * Returns the number of language items in the list.
     */
    @Override
    public int getItemCount()
    {
        return items.size();
    }
}
