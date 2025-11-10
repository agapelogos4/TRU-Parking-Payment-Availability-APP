// File: PassAdapter.java
// Author: Zechariah Montgomery
// Description: RecyclerView adapter displays parking passes, showing license plate,
//              expiration date, and lot info with color-coded indicators for day or multi-day passes.
// Date: [09/08/2025]

package com.tru.truparkingpaymentavailabilityapp;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/*
 * PassAdapter connects a list of Pass objects to RecyclerView items (see PassViewHolder)
 * It updates each pass item view with the license plate, expiration date,and lot
 * information, and colors to show the difference between single-day and multi-day passes.
 *
 * The adapter also detects dark mode and switches color schemes this is used for the colors of the passes.
 */
public class PassAdapter extends RecyclerView.Adapter<PassViewHolder>
{

    private final Context context;
    private List<Pass> passList;
    private final PassManager passManager;

    /**
     * PassAdapter for displaying a list of parking passes.
     *
     * @param context The current context, used for inflating layouts and getting resources
     * @param passList The list of Pass objects to be displayed in the recyclerview.
     */
    public PassAdapter(Context context, List<Pass> passList)
    {
        this.context = context;
        this.passList = passList;
        this.passManager = new PassManager(context);
    }

    /**
     * Inflates (meaning take the item and store the reference [if you dont know much about recyclerViews look them up online]) the item_view layout (langaugeItem) and creates a ViewHolder (See LanguageMyViewHolder class).
     * @param parent the parent ViewGroup
     * @param viewType view type of the new View
     * @return LanguageMyViewHolder containing references to item views
     */
    @NonNull
    @Override
    public PassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return new PassViewHolder(LayoutInflater.from(context).inflate(R.layout.pass_item_view, parent, false));
    }

    /**
     * Binds data from a Pass object to the ViewHolder.
     * - adds data to UI (license plate, expiration date, lot).
     * - chooses color scheme based on dark/light mode.
     * - sets a visual indicator for single-day vs multi-day passes.
     * - sets click listeners for action buttons (e.g., edit).
     *
     * @param holder the ViewHolder which should be updated to represent the item
     * @param position the position of the item within the list passList
     */
    @Override
    public void onBindViewHolder(@NonNull PassViewHolder holder, int position)
    {
        //grabs the current pass object for the pass in the said position
        Pass pass = passList.get(position);

        //set the text using the PassViewHolder.
        holder.licensePlate.setText(pass.getLicensePlate());
        holder.expirationDate.setText("Expires: " + pass.getExpirationDate());
        holder.lot.setText("Lot: " + pass.getLot());

        //this will grab the color from resources
        int dayPassColor = ContextCompat.getColor(context, R.color.day_pass_color_light);
        int multiDayPassColor = ContextCompat.getColor(context, R.color.multiday_pass_color_light);

        //if the app is in darkmode dont use the light values for color go with the darker colors
        if (isDarkMode())
        {
            dayPassColor = ContextCompat.getColor(context, R.color.day_pass_color);
            multiDayPassColor = ContextCompat.getColor(context, R.color.multiday_pass_color);
        }

        //if the pass is a multiday pass then set the color of the LinearLayout square box to multidaypass color.
        if (pass.isMultiDay())
        {
            holder.passType.setBackgroundResource(R.drawable.pass_indicator_background);
            holder.passType.setBackgroundTintList(ColorStateList.valueOf(multiDayPassColor));
        }
        else //if the pass is a single day pass
        {
            holder.passType.setBackgroundResource(R.drawable.pass_indicator_background);
            holder.passType.setBackgroundTintList(ColorStateList.valueOf(dayPassColor));
        }

        //edit button for editing license plates on passes.
        holder.editButton.setOnClickListener(v ->
        {

        });
    }

    /**
     * Returns the total number of Pass items managed by the adapter.
     *
     * @return the size of the pass list (0 if null)
     */
    @Override
    public int getItemCount() {
        return passList.size();
    }

    /**
     * Checks the current UI mode to find out if the app is running in dark mode.
     *
     * @return true if night mode is enabled, false if its day mode.
     */
    private boolean isDarkMode()
    {
        int nightModeFlags = context.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        return nightModeFlags == Configuration.UI_MODE_NIGHT_YES;
    }
}
