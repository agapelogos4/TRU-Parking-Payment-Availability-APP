// File: Transaction.java
// Author: Zechariah Montgomery
// Description: represents a parking payment transaction has methods to serialize/deserialize for storage
// Date: [09/08/2025]

package com.tru.truparkingpaymentavailabilityapp;

import java.util.UUID;

/**
 * Represents a single parking transaction.
 * contains all transaction details and methods for storage
 */
public class Transaction {
    private String id;
    private String date;
    private double amount;
    private String duration;
    private String lot;
    private String licensePlate;
    private String cardHolder;
    private String cardDigits;

    /**
     * Constructor for creating a new transaction with a generated unique ID
     */
    public Transaction(String date, double amount, String duration, String lot, String licensePlate, String cardHolder, String cardDigits)
    {
        this.id = UUID.randomUUID().toString(); //random unique id
        this.date = date;
        this.amount = amount;
        this.duration = duration;
        this.lot = lot;
        this.licensePlate = licensePlate;
        this.cardHolder = cardHolder;
        this.cardDigits = cardDigits;
    }

    /**
     * Constructor used when restoring a transaction from storage.
     */
    public Transaction(String id, String date, double amount, String duration, String lot, String licensePlate, String cardHolder, String cardDigits)
    {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.duration = duration;
        this.lot = lot;
        this.licensePlate = licensePlate;
        this.cardHolder = cardHolder;
        this.cardDigits = cardDigits;
    }

    public String getId()
    {
        return id;
    }

    public String getDate()
    {
        return date;
    }

    public double getAmount()
    {
        return amount;
    }

    public String getDuration()
    {
        return duration;
    }

    public String getLot()
    {
        return lot;
    }

    public String getLicensePlate()
    {
        return licensePlate;
    }

    public String getCardHolder()
    {
        return cardHolder;
    }

    public String getCardDigits()
    {
        return cardDigits;
    }

    /**
     * Converts the transaction into a single string for storage
     * data is separated by |
     * @return A string representation data for saving in sharedpreferences
     */
    public String toStorageString()
    {
        return id + "|" + date + "|" + amount + "|" + duration + "|" + lot + "|" + licensePlate + "|" + cardHolder + "|" + cardDigits;
    }

    /**
     * sets the data for a Transaction object from a storage string.
     * @param storageString the string retrieved from storage.
     * @return transaction object or null if the string is invalid.
     */
    public static Transaction fromStorageString(String storageString)
    {
        if (storageString == null || storageString.isEmpty()) //if the data retrieved is null or empty
            return null;

        String[] parts = storageString.split("\\|"); //if there is not enough data
        if (parts.length != 8)
            return null;

        String id = parts[0];
        String date = parts[1];
        double amount = Double.parseDouble(parts[2]);
        String duration = parts[3];
        String lot = parts[4];
        String licensePlate = parts[5];
        String cardHolder = parts[6];
        String cardDigits = parts[7];

        return new Transaction(id, date, amount, duration, lot, licensePlate, cardHolder, cardDigits);
    }
}
