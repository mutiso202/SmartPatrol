package com.example.smartpatrol;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Initialize view
        View view=inflater.inflate(R.layout.fragment_map,container,false);

        SupportMapFragment supportMapFragment=(SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.google_map);

        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                //when map is loaded
                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    private Object LatLng;

                    @Override
                    //when clicked on map
                    public void onMapClick(@NonNull LatLng latLng) {
                        //initialize marker options
                        MarkerOptions markerOptions=new MarkerOptions();
                        markerOptions.position((com.google.android.gms.maps.model.LatLng) LatLng);
                        MarkerOptions title = markerOptions.title(((com.google.android.gms.maps.model.LatLng) LatLng).latitude + ":" + ((com.google.android.gms.maps.model.LatLng) LatLng).longitude);
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom((com.google.android.gms.maps.model.LatLng) LatLng,
                                10));
                        googleMap.addMarker(markerOptions);
                    }
                });
            }
        });
        return view;
    }
}