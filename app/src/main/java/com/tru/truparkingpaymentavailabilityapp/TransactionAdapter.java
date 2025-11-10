// File: TransactionAdapter.java
// Author: Zechariah Montgomery
// Description: adapter for displaying a list of transactions in a recyclerview. can delete a item with confirmation dialog.
// Date: [09/08/2025]

package com.tru.truparkingpaymentavailabilityapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * recyclerview adapter for displaying transaction items
 * each item shows transaction details.
 */
public class TransactionAdapter extends RecyclerView.Adapter<TransactionViewHolder>
{
    Context context;
    List<Transaction> items;
    TextView noTransactionsText;

    /**
     * @param context the context for inflating (meaning take the item and store the reference [if you dont know much about recyclerViews look them up online]) layouts and creating dialogs
     * @param items list of transactions to display
     * @param noTransactionsText TextView displayed when there are no transactions
     */
    public TransactionAdapter(Context context, List<Transaction> items, TextView noTransactionsText)
    {
        this.context = context;
        this.items = items;
        this.noTransactionsText = noTransactionsText;
    }

    /**
     * Inflates the item layout and creates the ViewHolder
     */
    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return new TransactionViewHolder(LayoutInflater.from(context).inflate(R.layout.transaction_item_view, parent, false));
    }

    /**
     * Binds transaction data to each ViewHolder.
     * Sets click listener for the delete button with confirmation dialog.
     */
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position)
    {
         Transaction transaction = items.get(position);

         holder.transactionDate.setText(transaction.getDate());
         holder.transactionAmount.setText("$" + transaction.getAmount());
         holder.transactionDuration.setText(transaction.getDuration());
         holder.transactionLot.setText(transaction.getLot());
         holder.licensePlate.setText(transaction.getLicensePlate());
         holder.cardDetails.setText(transaction.getCardDigits());

        holder.deleteButton.setOnClickListener(v ->
        {
            int adapterPosition = holder.getAdapterPosition();
            if (adapterPosition != RecyclerView.NO_POSITION) //because we can remove items from the recyclerview we need to check if it exists before we run the items.get(adapterPosition) cant get one if its out of index it would crash
            {
                Transaction transactionToDelete = items.get(adapterPosition); //this will tell us where in the list of objects our Transaction object exists so we can delete it

                new AlertDialog.Builder(context).setTitle("Delete Transaction").setMessage("Are you sure you want to (permanently) delete the transaction on " + transactionToDelete.getDate() + "?").setPositiveButton("Confirm", ((dialog, which) ->
                {
                    TransactionManager transactionManager = new TransactionManager(context); //create a references to our TransactionManger so we can use the delete method to remove it from sharedprefrences
                    transactionManager.deleteTransaction(transactionToDelete.getId());

                    items.remove(adapterPosition); //remove it from our updated list
                    notifyItemRemoved(adapterPosition); //this will tell recyclerview that a item was removed and it removes it from view
                    notifyItemRangeChanged(adapterPosition, items.size()); //this tells the recyclerview that the list size has changed so it can adjust the position of the others by shifting them up 1 value if they were below the previously deleted value

                    if (items.isEmpty()) //if there is nothing left in the recyclerview show the TextView that tells the user that there are no transactions currently
                    {
                        noTransactionsText.setVisibility(View.VISIBLE);
                    }
                })).setNegativeButton("Cancel", ((dialog, which) ->
                {
                    dialog.dismiss();
                })).show();
            }
        });

    }

    /**
     * Returns the number of transactions in the list.
     */
    @Override
    public int getItemCount() {
        return items.size();
    }
}
