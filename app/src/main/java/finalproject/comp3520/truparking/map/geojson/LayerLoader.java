package finalproject.comp3520.truparking.map.geojson;

import android.content.Context;
import android.util.Log;

import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;

import com.google.android.gms.maps.GoogleMap;
import com.google.maps.android.data.geojson.GeoJsonLayer;
import com.google.maps.android.data.geojson.GeoJsonPolygonStyle;

import java.util.HashMap;

import finalproject.comp3520.truparking.R;
import finalproject.comp3520.truparking.map.data.ParkingLot;
import finalproject.comp3520.truparking.utils.ReflectionUtil;

public class LayerLoader {

    private static final String TAG = "LayerLoader";
    private final GoogleMap map;
    private final Context context;

    HashMap<String, GeoJsonLayer> layers = new HashMap<>();


    public LayerLoader(GoogleMap googleMap, Context context) {
        this.map = googleMap;
        this.context = context;
    }

    public void loadLayer(ParkingLot lot) {
        String name = lot.getName().toLowerCase();
        int jsonFile;
        try {
            jsonFile = ReflectionUtil.getResourceIdFromString(name, R.raw.class);
        } catch (Exception e) {
            Log.e(TAG, "Could not find " + name + ".json");
            return;
        }

        try {
            GeoJsonLayer layer = new GeoJsonLayer(map, jsonFile, context);

            GeoJsonPolygonStyle polygonStyle =  layer.getDefaultPolygonStyle();

            int colour = ContextCompat.getColor(context, lot.getColour());
            int fillColour = ColorUtils.setAlphaComponent(colour, 100);

            polygonStyle.setFillColor(fillColour);
            polygonStyle.setStrokeColor(colour);

            layers.put(name, layer);
        } catch (Exception e) {
            Log.e(TAG, "Could not load GeoJson from " + name + ".json");
        }
    }

    public void loadLayers(ParkingLot... parkingLots) {
        for(ParkingLot parkingLot : parkingLots) {
            loadLayer(parkingLot);
        }
    }


    public void show(String name) {
        name = name.toLowerCase();
        if(!layers.containsKey(name)) {
            Log.i(TAG, "show: Could not find " + name + " layer");
            return;
        }
        layers.get(name).addLayerToMap();
    }
    public void hide(String name) {
        name = name.toLowerCase();
        if(!layers.containsKey(name)) {
            Log.i(TAG, "show: Could not find " + name + " layer");
            return;
        }
        layers.get(name).removeLayerFromMap();
    }

    public void showAll() {
        for (GeoJsonLayer layer : layers.values()) {
            layer.addLayerToMap();
        }
    }

    public void hideAll() {
        for (GeoJsonLayer layer : layers.values()) {
            layer.addLayerToMap();
        }
    }
}
