package finalproject.comp3520.truparking.map;

public enum ParkingLotEnum {
    A1(new ParkingLot("A1", 10, ParkingLot.LotType.VISITOR, true)),
    A3(new ParkingLot("A3", 10, ParkingLot.LotType.RESERVED, true)),
    B(new ParkingLot("B", 10, ParkingLot.LotType.RESERVED, true)),
    C(new ParkingLot("C", 10, ParkingLot.LotType.PREMIUM, true)),
    NT(new ParkingLot("NT", 10, ParkingLot.LotType.ECONOMY, true)),
    N(new ParkingLot("N", 10, ParkingLot.LotType.ECONOMY, true)),
    H(new ParkingLot("H", 10, ParkingLot.LotType.GENERAL, true)),;
    public final ParkingLot parkingLot;
    ParkingLotEnum(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }
}
