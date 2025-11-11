package finalproject.comp3520.truparking.map;

import java.util.ArrayList;
import java.util.List;

import finalproject.comp3520.truparking.R;

public class ParkingLot {
    private final int MAX_CAPACITY;
    int currentCapacity;
    private final LotType TYPE;
    private final String NAME;
    int close; // 4 digit number for when the lot is closed 1000 -> 10:00 AM
    int open; // 4 digit number for when the lot is open 1400 -> 2:00 PM
    boolean isOpen; // For construction, road close, etc.
    boolean isVisible = true;

    public ParkingLot(String name, int maxCapacity, LotType type, boolean isOpen) {
        this.MAX_CAPACITY = maxCapacity;
        this.TYPE = type;
        this.NAME = name;

    }

    public ParkingLot(String name, int maxCapacity, LotType type, boolean isOpen, int close, int open) {
        this(name, maxCapacity, type, isOpen);
        this.close = close;
        this.open = open;
    }

    public String getName() {
        return this.NAME;
    }
    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public int getColour() {
        return TYPE.colour;
    }

    public enum LotType {
        ECONOMY(R.color.economy_lot, new ArrayList<>()),
        GENERAL(R.color.general_lot, new ArrayList<>()),
        VISITOR(R.color.visitor_lot, new ArrayList<>()),
        PREMIUM(R.color.premium_lot, new ArrayList<>()),
        RESERVED(R.color.reserved_lot, new ArrayList<>());
        int colour;
        List<Object> passes;

        LotType(int colour, List<Object> passes) {
            this.colour = colour;
            this.passes = passes; // Temporary, should use pass object once available
        }
    }
}

