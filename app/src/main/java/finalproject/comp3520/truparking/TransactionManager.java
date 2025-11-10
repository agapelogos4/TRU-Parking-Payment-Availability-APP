// File: TransactionManager.java
// Author: Zechariah Montgomery
// Description: handles storing, retrieving, adding, and deleting transaction objects in sharedpreferences
// Date: [09/08/2025]

package finalproject.comp3520.truparking;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

public class TransactionManager
{
    private static final String PREFS_NAME = "parking_preferences";
    private static final String KEY_TRANSACTIONS = "transactions";
    private static final String SEPARATOR = ";;";

    private SharedPreferences sharedPreferences;

    /**
     * constructor initializes sharedpreferences for transactions
     *
     * @param context app context
     */
    public TransactionManager(Context context)
    {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    /**
     * saves the list of transactions to sharedpreferences
     * each transaction is converted to a string and separated by SEPARATOR
     *
     * @param transactions list of Transaction objects to store
     */
    private void saveAllTransactions(List<Transaction> transactions)
    {
        StringBuilder builder = new StringBuilder();
        for (Transaction t : transactions)
        {
            builder.append(t.toStorageString()).append(SEPARATOR); //add the separator at the end of each transaction stored in sharedpreferences
        }
        sharedPreferences.edit().putString(KEY_TRANSACTIONS, builder.toString()).apply(); //save it
    }

    /**
     * grabs all saved transactions from sharedpreferences
     * converts each storage string back into a transaction object
     *
     * @return list of Transaction objects
     */
    public List<Transaction> getAllTransactions()
    {
        String stored = sharedPreferences.getString(KEY_TRANSACTIONS, "");
        List<Transaction> transactions = new ArrayList<>();

        if (!stored.isEmpty()) //if we have data in sharedpreferences
        {
            String[] items = stored.split(SEPARATOR); //seperate the data into each transaction
            for (String item : items)
            {
                Transaction t = Transaction.fromStorageString(item); //seperate the data in each transaction see Transaction class
                if (t != null) //if we get the data correctly seperated
                {
                    transactions.add(t); //add the transaction to the list
                }
            }
        }
        return transactions; //return the list
    }

    /**
     * checks whether a transaction ID already exists in storage
     *
     * @param transactionId check
     * @return true if unique, false if duplicate
     */
    private boolean isTransactionIdUnique(String transactionId)
    {
        List<Transaction> existingTransactions = getAllTransactions();
        for (Transaction t : existingTransactions)
        {
            if (t.getId().equals(transactionId)) //if the transaction id does not mach a already existing transaction id then this if statement is false and the program will return true
            {
                return false;
            }
        }
        return true;
    }

    /**
     * deletes all stored transactions
     * clears the sharedpreferences
     */
    public void deleteAllTransactions()
    {
        saveAllTransactions(new ArrayList<>()); //save a empty list which would override the existing one allowing us to delete all transactions from sharedpreferences
    }

    /**
     * adds a new transaction to storage if the ID is unique
     *
     * @param transaction transaction object to add
     */
    public void addTransaction(Transaction transaction)
    {
        if (isTransactionIdUnique(transaction.getId())) //if the transaction you want to add is unique
        {
            List<Transaction> currentTransactions = getAllTransactions();
            currentTransactions.add(transaction); //take the current list of transactions and add the newest transaction to it
            saveAllTransactions(currentTransactions); //then save the new list with the new transaction
        }
    }

    /**
     * deletes a specific transaction by its ID
     *
     * @param transactionID ID of the transaction so we can delete it
     */
    public void deleteTransaction(String transactionID)
    {
        List<Transaction> current = getAllTransactions(); //old list of transactions
        List<Transaction> updated = new ArrayList<>(); //new list of transactions (empty currently)

        for (Transaction t : current)
        {
            if (!t.getId().equals(transactionID)) //if the id does not equal the id we want to delete add it to the updated list
            {
                updated.add(t);
            }
        }

        saveAllTransactions(updated); //save the updated list
    }
}
