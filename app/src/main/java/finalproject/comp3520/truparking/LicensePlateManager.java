// Author: Rajveer Rupal
// Description: Handles license plate add/remove/save logic.
// Date: 11/07/2025

package finalproject.comp3520.truparking;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;

public class LicensePlateManager {

    private static final String PREFS_NAME = "UserPrefs";
    private static final String PLATE_LIST_KEY = "PLATE_LIST";
    private static final int MAX_PLATES = 4;

    private final SharedPreferences prefs;
    private final ArrayList<String> plateList = new ArrayList<>();

    public LicensePlateManager(Context context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        loadFromPrefs();
    }

    // Returns saved license plates
    public ArrayList<String> getPlates() {
        return new ArrayList<>(plateList);
    }

    // Adds a plate if not duplicate or full
    public boolean addPlate(String newPlate) {
        reloadFromPrefs();
        if (plateList.contains(newPlate)) return false;
        if (isFull()) return false;
        plateList.add(newPlate);
        saveToPrefs();
        return true;
    }

    // Removes a plate
    public void removePlate(String plate) {
        plateList.remove(plate);
        saveToPrefs();
    }

    // Reloads plates from storage
    private void reloadFromPrefs() {
        plateList.clear();
        loadFromPrefs();
    }

    // Loads saved data
    private void loadFromPrefs() {
        String saved = prefs.getString(PLATE_LIST_KEY, "");
        if (!TextUtils.isEmpty(saved)) {
            String[] parts = saved.split(",");
            for (String p : parts) {
                String plate = p.trim();
                if (!plate.isEmpty()) plateList.add(plate);
            }
        }
    }

    // Saves plates to SharedPreferences
    private void saveToPrefs() {
        prefs.edit()
                .putString(PLATE_LIST_KEY, TextUtils.join(",", plateList))
                .apply();
    }

    // Checks if list is full
    public boolean isFull() {
        return plateList.size() >= MAX_PLATES;
    }

    // Moves last plate to first
    public void moveLastToFirst() {
        if (plateList.size() > 1) {
            String last = plateList.remove(plateList.size() - 1);
            plateList.add(0, last);
            saveToPrefs();
        }
    }
}
