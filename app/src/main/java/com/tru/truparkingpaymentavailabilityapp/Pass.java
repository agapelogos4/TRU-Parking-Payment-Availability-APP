// File: Pass.java
// Author: Zechariah Montgomery
// Description: Represents a parking pass, including vehicle plate, expiration date,
//              parking lot, and whether it supports multiple days (true meaning its a multi day pass and false meaning its a single day pass (used for color coding in recyclerview display)).
// Date: [09/08/2025]


package com.tru.truparkingpaymentavailabilityapp;

import java.util.UUID;

/*
 * The Pass class is a user's parking pass
 * each pass has a unique ID (using UUID), license plate, expiration date,
 * associated parking lot, and an indicator of whether it’s a multi-day pass.
 *
 * This class can be used both for creating new passes and for restoring
 * saved passes from SharedPreferences or a database.
 */
public class Pass
{
    private String id;
    private String licensePlate;
    private String expirationDate;
    private String lot;
    private boolean isMultiDay;

    /**
     * Constructor for creating a new parking pass.
     * Automatically generates a unique ID.
     *
     * @param licensePlate The license plate associated with the pass.
     * @param expirationDate The date the pass expires.
     * @param lot The parking lot assigned to the pass.
     * @param isMultiDay True if the pass is good for multiple days.
     */
    public Pass(String licensePlate, String expirationDate, String lot, boolean isMultiDay)
    {
        this.id = UUID.randomUUID().toString();
        this.licensePlate = licensePlate;
        this.expirationDate = expirationDate;
        this.lot = lot;
        this.isMultiDay = isMultiDay;
    }

    /**
     * Constructor used for restoring a pass object from saved data
     * Used when loading passes from SharedPreferences or storage
     *
     * @param id The unique identifier for the pass.
     * @param licensePlate The license plate associated with the pass.
     * @param expirationDate The date the pass expires.
     * @param lot The parking lot assigned to the pass.
     * @param isMultiDay True if the pass is good for multiple days.
     */
    public Pass (String id, String licensePlate, String expirationDate, String lot, boolean isMultiDay)
    {
        this.id = id;
        this.licensePlate = licensePlate;
        this.expirationDate = expirationDate;
        this.lot = lot;
        this.isMultiDay = isMultiDay;
    }

    public String getId()
    {
        return id;
    }

    public String getLicensePlate()
    {
        return licensePlate;
    }

    public String getExpirationDate()
    {
        return expirationDate;
    }

    public String getLot()
    {
        return lot;
    }

    public boolean isMultiDay()
    {
        return isMultiDay;
    }

    /**
     * Returns a compact string representation of the pass for storage
     * the inputs are separated by "|" to make it easy to parse.
     *
     * @return A pipe-separated string containing all pass details.
     */
    @Override
    public String toString()
    {
        return id + "|" + licensePlate + "|" + expirationDate + "|" + lot + "|" + isMultiDay;
    }
}
