// File: Settings.java
// Author: Zechariah Montgomery
// Description: Handles the settings screen, including account info, notifications, transactions, vehicles, passes, language, and dark mode toggle.
// Date: [08/15/2025]

package com.tru.truparkingpaymentavailabilityapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

public class Settings extends Fragment
{
    /*
     * onCreateView inflates the fragment layout and initializes all UI components.
     * Sets click listeners for buttons to show bottom sheet fragments.
     * Initializes the light/dark mode switch based on SharedPreferences.
     * Updates the language button text based on user's last selected language.
     */

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        /*
         * Each button opens a corresponding BottomSheetFragment when clicked:
         * - accountButton -> fragment_account_information
         * - notificationButton -> ToggleNotificationsFragment
         * - transactionButton -> TransactionHistoryFragment
         * - vehicleButton -> VehicleManagementFragment
         * - passButton -> PassManagementFragment
         * - languageButton -> LanguageFragment
         *
         * Bottom sheet fragments are displayed using getParentFragmentManager().
         */

        //account information button shows a bottom sheet fragment called: fragment_account_information (when clicked)
        Button accountButton = view.findViewById(R.id.accountInformationButton);
        accountButton.setOnClickListener(v ->
        {
            fragment_account_information bottomSheet = new fragment_account_information();
            try //if for some strange reason account_info_bottomsheet does not open here is a try catch.
            {
                bottomSheet.show(getParentFragmentManager(), "account_info_bottomsheet");
            }
            catch (IllegalStateException e)
            {
                Log.e("SettingsFragment", "Failed to show bottom sheet: account_info_bottomsheet", e);
                Toast.makeText(getContext(), "Unable to open section.", Toast.LENGTH_SHORT).show();
            }
        });

        //notification button shows a bottom sheet fragment called: ToggleNotificationsFragment (when clicked)
        Button notificationButton = view.findViewById(R.id.notificationButton);
        notificationButton.setOnClickListener(v ->
        {
            ToggleNotificationsFragment bottomSheet = new ToggleNotificationsFragment();
            try //if for some strange reason notifications_bottomsheet does not open here is a try catch.
            {
                bottomSheet.show(getParentFragmentManager(), "notifications_bottomsheet");
            }
            catch (IllegalStateException e)
            {
                Log.e("SettingsFragment", "Failed to show bottom sheet: notifications_bottomsheet", e);
                Toast.makeText(getContext(), "Unable to open section.", Toast.LENGTH_SHORT).show();
            }
        });

        //transaction button shows a bottom sheet fragment called: TransactionHistoryFragment (when clicked)
        Button transactionButton = view.findViewById(R.id.transactionHistoryButton);
        transactionButton.setOnClickListener(v ->
        {
            TransactionHistoryFragment bottomSheet = new TransactionHistoryFragment();
            try //if for some strange reason transaction_history_bottomsheet does not open here is a try catch.
            {
                bottomSheet.show(getParentFragmentManager(), "transaction_history_bottomsheet");
            }
            catch (IllegalStateException e)
            {
                Log.e("SettingsFragment", "Failed to show bottom sheet: transaction_history_bottomsheet", e);
                Toast.makeText(getContext(), "Unable to open section.", Toast.LENGTH_SHORT).show();
            }
        });

        //vehicle button shows a bottom sheet fragment called: VehicleManagementFragment (when clicked)
        Button vehicleButton = view.findViewById(R.id.vehicleManagementButton);
        vehicleButton.setOnClickListener(v ->
        {
            VehicleManagementFragment bottomSheet = new VehicleManagementFragment();
            try //if for some strange reason vehicle_management_bottomsheet does not open here is a try catch.
            {
                bottomSheet.show(getParentFragmentManager(), "vehicle_management_bottomsheet");
            }
            catch (IllegalStateException e)
            {
                Log.e("SettingsFragment", "Failed to show bottom sheet: vehicle_management_bottomsheet", e);
                Toast.makeText(getContext(), "Unable to open section.", Toast.LENGTH_SHORT).show();
            }
        });

        //pass button shows a bottom sheet fragment called: PassManagementFragment (when clicked)
        Button passButton = view.findViewById(R.id.passManagementButton);
        passButton.setOnClickListener(v ->
        {
            PassManagementFragment bottomSheet = new PassManagementFragment();
            try //if for some strange reason pass_management_bottomsheet does not open here is a try catch.
            {
                bottomSheet.show(getParentFragmentManager(), "pass_management_bottomsheet");
            }
            catch (IllegalStateException e)
            {
                Log.e("SettingsFragment", "Failed to show bottom sheet: pass_management_bottomsheet", e);
                Toast.makeText(getContext(), "Unable to open section.", Toast.LENGTH_SHORT).show();
            }
        });

        //language button shows a bottom sheet fragment called: LanguageFragment (when clicked)
        Button languageButton = view.findViewById(R.id.languageButton);
        languageButton.setOnClickListener(v ->
        {
            LanguageFragment bottomSheet = new LanguageFragment();
            try //if for some strange reason language_bottomsheet does not open here is a try catch.
            {
                bottomSheet.show(getParentFragmentManager(), "language_bottomsheet");
            }
            catch (IllegalStateException e)
            {
                Log.e("SettingsFragment", "Failed to show bottom sheet: language_bottomsheet", e);
                Toast.makeText(getContext(), "Unable to open section.", Toast.LENGTH_SHORT).show();
            }
        });

        //This is a switch widget.
        androidx.appcompat.widget.SwitchCompat lightDarkSwitch = view.findViewById(R.id.lightDarkModeSwitch);

        //connect to sharedpreferences and grab the the darkmode value
        SharedPreferences preferences = requireActivity().getSharedPreferences("parking_preferences", Context.MODE_PRIVATE);
        boolean isCurrentlyDarkMode = preferences.getBoolean("dark_mode", false);

        //when the fragment is run it will set if the switch should be on or off depending on the users last toggle
        lightDarkSwitch.setChecked(isCurrentlyDarkMode);


        //this grabs the last language set by the user or if not default will be English.
        String currentLanguage = preferences.getString("language", "English");


        //this checks if the switch is toggled or not if its toggled night mode enabled if not then day mode enabled it calls the themes and resets the main activity running this fragment.
        lightDarkSwitch.setOnCheckedChangeListener((buttonView, isChecked) ->
        {
            preferences.edit().putBoolean("dark_mode", isChecked).apply();//saves the current toggle mode

            if (isChecked)
            {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
            else
            {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });

        //this sets the text of the language button when its updated, if you look at the bottom sheet of the language fragment you will notice it resets the main activity so when the logic for selecting a language is run this will update the name of the button
        languageButton.setText(getString(R.string.LanguageText, currentLanguage));


        return view;
    }

}
