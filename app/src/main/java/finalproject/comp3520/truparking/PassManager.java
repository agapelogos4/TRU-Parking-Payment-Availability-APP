// File: PassManager.java
// Author: Zechariah Montgomery
// Description: Handles saving, loading, and managing parking passes using SharedPreferences.
// Date: [09/08/2025]

package finalproject.comp3520.truparking;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * The PassManager class provides methods for storing, retrieving,
 * updating, and deleting Pass objects using SharedPreferences.
 *
 * Data is organized into a single string with custom delimiters (string separators).
 *
 * What is handles:
 *  - manage pass (save/load)
 *  - unique pass IDs
 *  - provide other operations for Pass objects to manipulate them
 */
public class PassManager
{
    private static final String PREFS_NAME = "parking_preferences";
    private static final String KEY_PASSES = "passes";
    private static final String SEPARATOR = ";;";

    private final SharedPreferences sharedPreferences;

    public PassManager(Context context)
    {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    /**
     * grabs all saved passes from SharedPreferences.
     *
     * @return a List of Pass objects currently stored, or an empty list if none exist
     */
    public List<Pass> getAllPasses()
    {
        List<Pass> passes = new ArrayList<>();

        try
        {
            String storedData = sharedPreferences.getString(KEY_PASSES, "");

            if (storedData.isEmpty()) //if there is nothing in shared preferences
                return passes;
            String[] passEntries = storedData.split(SEPARATOR); //split the data from the SEPARATOR and store into the list
            for (String entry : passEntries)
            {
                String[] parts = entry.split("\\|"); //split each data item in the pass
                if (parts.length == 5)
                {
                    String id = parts[0];
                    String license = parts[1];
                    String expiration = parts[2];
                    String lot = parts[3];
                    boolean isMultiDay = Boolean.parseBoolean(parts[4]);
                    passes.add(new Pass(id, license, expiration, lot, isMultiDay)); //create a pass object and store it in our list
                }
            }
        }
        catch (Exception e)
        {
            Log.e("PassManager", "Error reading passes", e);
        }

        return passes;
    }

    /**
     * checks if a pass ID is unique among stored passes.
     *
     * @param passId the ID of the pass to check
     * @return true if the ID does not already exist, false otherwise
     */
    private boolean isPassIdUnique(String passId)
    {
        List<Pass> existingPasses = getAllPasses(); //this will grab all pass objects
        for (Pass pass : existingPasses)
        {
            if (pass.getId().equals(passId)) //if a passes id matches a already existing pass then it is not unique
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Saves the provided list of passes into SharedPreferences
     * by combining them into a single string.
     *
     * @param passes list of passes
     */
    private void savePassList(List<Pass> passes)
    {
        try
        {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < passes.size(); i++)
            {
                builder.append(passes.get(i).toString());
                if (i < passes.size() - 1) //keep adding the separators unless your the last one
                {
                    builder.append(SEPARATOR);
                }
            }
            sharedPreferences.edit().putString(KEY_PASSES, builder.toString()).apply(); //stored all the passes now formated into shared preferences as one long line.
        }
        catch (Exception e)
        {
            Log.e("PassManager", "Error saving passes", e);
        }
    }

    /**
     * Adds a new pass to storage, if its ID is unique.
     *
     * @param pass the Pass object to be added to sharedpreferences
     */
    public void addPass(Pass pass)
    {
        if (isPassIdUnique(pass.getId())) //if the pass does not have the same id as another pass
        {
            List<Pass> currentPasses = getAllPasses();
            currentPasses.add(pass);
            savePassList(currentPasses); //save the new pass to the long list found in sharedpreferences
        }
    }

    /**
     * Deletes a pass by its unique ID.
     *
     * @param id the ID of the pass that will be deleted
     */
    public void deletePassById(String id)
    {
        List<Pass> passes = getAllPasses(); //old passes
        List<Pass> updated = new ArrayList<>(); //new passes

        //remove the pass with the provided ID
        for (Pass pass : passes)
        {
            if (!pass.getId().equals(id))
            {
                updated.add(pass);
            }
        }
        savePassList(updated);
    }

    /**
     * Updates an existing pass with new information, matched by ID.
     *
     * @param updatedPass the pass object containing updated details
     */
    public void updatePass(Pass updatedPass) //this method will be used when the user changes the license plate that pass is for.
    {
        List<Pass> passes = getAllPasses();
        for (int i = 0; i < passes.size(); i++)
        {
            if (passes.get(i).getId().equals(updatedPass.getId())) //if the id matches both the updated passes and the list of passes then we know we have arrived at the correct pass
            {
                passes.set(i, updatedPass); //we will set the new pass in the list of passes
                break;
            }
        }
        savePassList(passes); //now we will save the passes.
    }
}
