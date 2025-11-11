package finalproject.comp3520.truparking.map;

import java.util.HashMap;

import finalproject.comp3520.truparking.map.data.ParkingLot;
import finalproject.comp3520.truparking.map.data.ParkingLotEnum;

public class ParkingLotLoader {
     private final HashMap<String, ParkingLot> parkingLotMap = new HashMap<>();
    public ParkingLotLoader() {
        for(ParkingLotEnum parkingLotEnum : ParkingLotEnum.values()) {
            ParkingLot parkingLot = parkingLotEnum.getParkingLot();
           parkingLotMap.put(parkingLot.getName(), parkingLot);
        }

        // Attempt to load from webserver
    }

    public  ParkingLot getParkingLot(String name) {
        return parkingLotMap.get(name);
    }

    public ParkingLot[] getAllParkingLots() {
        return parkingLotMap.values().toArray(new ParkingLot[0]);
    }


}
