package com.example.smartpatrol.util;

import android.Manifest;
import android.app.Activity;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.smartpatrol.interfaces.callback;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.GeoPoint;

import java.util.Random;

public class LocationUtils {
    private static final int PICK_IMAGE = 100;
    private static final int REQUEST_CODE = 1001;

    public  static void requestLocationPermissions(Activity activity) {
        if (ContextCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {

        } else {
            ActivityCompat.requestPermissions(activity, new String[] {
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION },
                    2);
        }

    }
    public static void requestGps(Activity activity) {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);

        LocationServices.getSettingsClient(activity)
                .checkLocationSettings(builder.build())
                .addOnCompleteListener(task -> promptUserForGps(task,activity));
    }
    public static void getMyDefaultLocation(Activity activity, callback callback) {
        LocationRequest mLocationRequest = LocationRequest.create();
        mLocationRequest.setInterval(60000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        LocationCallback mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    if (location != null) {
                       //callback.onSuccess(location);
                    }
                }
            }
        };
        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity);
        if (ActivityCompat.checkSelfPermission(
                activity.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            callback.onFailure("faiil");
            return;
        }
        LocationServices.getFusedLocationProviderClient(activity).requestLocationUpdates(mLocationRequest, mLocationCallback, null);
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task
                .addOnSuccessListener(location -> {if (location != null) callback.onSuccess(location);})
                .addOnFailureListener(e -> callback.onFailure(e));
    }


    public static void promptUserForGps(Task<LocationSettingsResponse> task, Activity activity) {
        try {task.getResult(ApiException.class);}
        catch (ApiException exception) {
            switch (exception.getStatusCode()) {
                case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                    // Location settings are not satisfied. But could be fixed by showing the user a dialog.
                    try {
                        // Cast to a resolvable exception.
                        ResolvableApiException resolvable = (ResolvableApiException) exception;
                        // Show the dialog by calling startResolutionForResult(),
                        // and check the result in onActivityResult().
                        resolvable.startResolutionForResult(
                                activity,
                                LocationRequest.PRIORITY_HIGH_ACCURACY);
                    } catch (IntentSender.SendIntentException | ClassCastException ignored) {}
                    break;
                case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE: break;
            }
        }
    }


    public static GeoPoint convertLatLongToGeopoint(LatLng location) {
        return new GeoPoint(location.latitude, location.longitude);
    }
    public static LatLng convertGeopointToLatLong(GeoPoint location) {
        return new LatLng(location.getLatitude(), location.getLongitude());
    }



    //getLastLocation();


            /*String latitude=startingLatitude.getText().toString();
            String longitude=startingLongitude.getText().toString();

            DocumentReference documentRef=dbPatrol.collection("Guard").document(new Guard().getuID()).collection("Patrols").document();

            HashMap hashMapPatrol=new HashMap();
            hashMapPatrol.put("Longitude",longitude);
            hashMapPatrol.put("Latitude",latitude);


            documentRef.set(hashMapPatrol).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(Homepage_activity.this, "Patrol started.", Toast.LENGTH_SHORT).show();
                }
            });

        });

    }

    private void getLastLocation() {

 if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED)

            fusedLocationProviderClient.getLastLocation()
            .addOnSuccessListener(new OnSuccessListener<Location>() {
        @Override
        public void onSuccess(Location location) {
            if (location != null) {
                Geocoder geocoder = new Geocoder(Homepage_activity.this, Locale.getDefault());
                try {
                    List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                    startingLatitude.setText("Latitude:"+addresses.get(0).getLatitude());
                    startingLongitude.setText("Longitude:"+addresses.get(0).getLongitude());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
    });
        else{
        askPermission();

    }
    ;
    }

    private void askPermission() {
        ActivityCompat.requestPermissions(Homepage_activity.this,
                new String[
                        Integer.parseInt(Manifest.permission.ACCESS_FINE_LOCATION)],
                REQUEST_CODE);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE){

            if(grantResults.length> 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getLastLocation();

            }else{
                Toast.makeText(this, "Please proide the required permissions", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}*/


}
