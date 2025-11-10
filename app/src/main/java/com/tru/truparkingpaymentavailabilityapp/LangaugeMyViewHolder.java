// File: LanguageMyViewHolder.java
// Author: Zechariah Montgomery
// Description: ViewHolder for displaying a single language item in a RecyclerVview.
// Date: [09/07/2025]


package com.tru.truparkingpaymentavailabilityapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/*
 * LanguageMyViewHolder holds the views for a single language item in the Recyclerview
 * gives access to the TextView displaying the language name
 */

public class LangaugeMyViewHolder extends RecyclerView.ViewHolder
{
    TextView langaugeView;

    /**
     * Initializes the ViewHolder and binds the TextView from the item layout.
     * @param itemView the layout view for a single item that will be displayed on the Recyclerview
     */
    public LangaugeMyViewHolder(@NonNull View itemView) {
        super(itemView);
        langaugeView = itemView.findViewById(R.id.language);
    }
}
