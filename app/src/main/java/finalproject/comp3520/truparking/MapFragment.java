package finalproject.comp3520.truparking;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import finalproject.comp3520.truparking.map.MapManager;
import finalproject.comp3520.truparking.map.data.ParkingLotEnum;
import finalproject.comp3520.truparking.map.ParkingLotLoader;
import finalproject.comp3520.truparking.map.geojson.LayerLoader;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    MapView mapView;
    GoogleMap googleMap;
    Context context;
    MapManager mapManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        context = view.getContext();

        mapView = view.findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(this);

        return view;
    }

    @RequiresPermission(allOf = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})
    @Override
    public void onMapReady(@NonNull GoogleMap map) {
        googleMap = map;

        // Permissions
        googleMap.getUiSettings().setMyLocationButtonEnabled(false);
        googleMap.setMyLocationEnabled(true);

        // Zoom in on TRU and add overlay
        LatLng truTopLeft = new LatLng(50.677, -120.371);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(truTopLeft, 17.0f));

        mapManager = new MapManager(googleMap, context);

    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

}