package finalproject.comp3520.truparking.data;

import java.util.HashMap;

public class ParkingLotManager {
     private final HashMap<String, ParkingLot> parkingLotMap = new HashMap<>();
    public ParkingLotManager() {
        for(ParkingLotEnum parkingLotEnum : ParkingLotEnum.values()) {
            ParkingLot parkingLot = parkingLotEnum.getParkingLot();
           parkingLotMap.put(parkingLot.getName(), parkingLot);
        }
    }

    public  ParkingLot getParkingLot(String name) {
        return parkingLotMap.get(name);
    }

    public ParkingLot[] getAllParkingLots() {
        return parkingLotMap.values().toArray(new ParkingLot[0]);
    }


}
