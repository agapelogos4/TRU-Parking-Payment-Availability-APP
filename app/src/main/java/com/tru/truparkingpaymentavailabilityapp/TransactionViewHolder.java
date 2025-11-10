// File: TransactionViewHolder.java
// Author: Zechariah Montgomery
// Description: holds references to the views for a single transaction item in the recyclerview
// Date: [09/08/2025]

package com.tru.truparkingpaymentavailabilityapp;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TransactionViewHolder extends RecyclerView.ViewHolder
{
    TextView transactionDate, transactionAmount, transactionDuration, transactionLot, licensePlate, cardDetails;
    ImageButton deleteButton;

    public TransactionViewHolder(@NonNull View itemView)
    {
        super(itemView);
        transactionDate = itemView.findViewById(R.id.transactionDate);
        transactionAmount = itemView.findViewById(R.id.transactionAmount);
        transactionDuration = itemView.findViewById(R.id.transactionDuration);
        transactionLot = itemView.findViewById(R.id.transactionLot);
        licensePlate = itemView.findViewById(R.id.licensePlate);
        cardDetails = itemView.findViewById(R.id.cardDetails);
        deleteButton = itemView.findViewById(R.id.deleteTransactionButton);
    }
}
