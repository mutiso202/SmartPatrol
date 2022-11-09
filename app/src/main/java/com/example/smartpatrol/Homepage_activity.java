package com.example.smartpatrol;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.smartpatrol.classes.Guard;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.ncorti.slidetoact.SlideToActView;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class Homepage_activity extends AppCompatActivity {
    SlideToActView Swipe_button;
    ImageView user_icon;
    FirebaseFirestore dbPatrol;
    FusedLocationProviderClient fusedLocationProviderClient;
    CardView card_ViewSchedule, card_ViewPatrols;
    private final static int REQUEST_CODE = 100;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Swipe_button = findViewById(R.id.Swipe_button);
        user_icon = findViewById(R.id.user_icon);
        card_ViewSchedule=findViewById(R.id.card_ViewSchedule);
        card_ViewPatrols=findViewById(R.id.card_ViewPatrols);
        dbPatrol = FirebaseFirestore.getInstance();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);



        user_icon.setOnClickListener(v -> {
            Intent profile = new Intent(Homepage_activity.this, ProfileActivity.class);
            startActivity(profile);
        });

        card_ViewSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Homepage_activity.this, ScheduleActivity.class);
                startActivity(intent);
            }
        });

        card_ViewPatrols.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Homepage_activity.this, PatrolListActivity.class);
                startActivity(intent);
            }
        });

        Swipe_button.setOnSlideCompleteListener(slideToActView -> {
            Intent intent = new Intent(Homepage_activity.this, ScanActivity.class);
            startActivity(intent);
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

        });


    }}
