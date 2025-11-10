// File: TransactionHistoryFragment.java
// Author: Zechariah Montgomery
// Description: displays a list of all past transactions. Allows individual deletion (via adapter) and deletion of all transactions.
// Date: [09/08/2025]

package com.tru.truparkingpaymentavailabilityapp;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;


public class TransactionHistoryFragment extends BottomSheetDialogFragment
{

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_transaction_history, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.transactionRecyclerView);
        TextView noTransactionMessage = view.findViewById(R.id.noTransactionsMessage);
        Button deleteAllButton = view.findViewById(R.id.deleteAllButton);

        //this is used to get all the transactions in sharedpreferences
        TransactionManager transactionManager = new TransactionManager(getContext());
        List<Transaction> transactionList = transactionManager.getAllTransactions();

        if (transactionList.isEmpty()) //this will show the noTransactions method if there are no transactions in the list
        {
            recyclerView.setVisibility(View.GONE);
            noTransactionMessage.setVisibility(View.VISIBLE);
            deleteAllButton.setVisibility(View.GONE);
        }
        else //hide noTransactionsMethod
        {
            recyclerView.setVisibility(View.VISIBLE);
            noTransactionMessage.setVisibility(View.GONE);
            deleteAllButton.setVisibility(View.VISIBLE);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new TransactionAdapter(getContext(), transactionList, noTransactionMessage));

        deleteAllButton.setOnClickListener(v ->
        {
            if (transactionList.isEmpty()) //if the user presses the delete all button when there are no transactions
            {
                return;
            }

            new AlertDialog.Builder(getContext()).setTitle("Delete All Transactions").setMessage("Are you sure you want to (permanently) delete all transactions?").setPositiveButton("Confirm", ((dialog, which) ->
            {
                transactionManager.deleteAllTransactions(); //remove all transactions from sharedpreferences

                transactionList.clear(); //clear the list
                recyclerView.getAdapter().notifyDataSetChanged(); //notify the recyclerview of the change so it can update its view

                noTransactionMessage.setVisibility(View.VISIBLE); //set the noTransactionMessage to visible
                deleteAllButton.setVisibility(View.GONE); //hide the delete all button because there are no-longer any transactions left
            })).setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss()).show();
        });

        return view;
    }
}