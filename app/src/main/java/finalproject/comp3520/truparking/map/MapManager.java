package finalproject.comp3520.truparking.map;

import android.content.Context;

import com.google.android.gms.maps.GoogleMap;

import finalproject.comp3520.truparking.map.geojson.LayerLoader;

public class MapManager {
    private final LayerLoader layerLoader;
    private final ParkingLotLoader parkingLotLoader;
    private final String TAG = "MapManager";

    public MapManager(GoogleMap map, Context context) {
        layerLoader = new LayerLoader(map, context);
        parkingLotLoader = new ParkingLotLoader();

        layerLoader.loadLayers(parkingLotLoader.getAllParkingLots());
        layerLoader.showAll();
    }

    public LayerLoader getLayerLoader() {
        return layerLoader;
    }

    public ParkingLotLoader getParkingLotLoader() {
        return parkingLotLoader;
    }

    public void showParkingLot(String name) {
        parkingLotLoader.getParkingLot(name).setVisible(true);
        layerLoader.show(name);
    }

    public void hideParkingLot(String name) {
        parkingLotLoader.getParkingLot(name).setVisible(false);
        layerLoader.hide(name);
    }
}
