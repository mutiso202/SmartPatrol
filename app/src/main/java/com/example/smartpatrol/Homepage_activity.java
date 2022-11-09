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


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        initializeViews();
        setListeners();

    }

    private void setListeners() {
        user_icon.setOnClickListener(v ->
            startActivity(new Intent(Homepage_activity.this, ProfileActivity.class))
        );

        card_ViewSchedule.setOnClickListener(view ->
            startActivity(new Intent(Homepage_activity.this, ScheduleActivity.class))
        );

        card_ViewPatrols.setOnClickListener(view ->
            startActivity(new Intent(Homepage_activity.this, PatrolListActivity.class))
        );

        Swipe_button.setOnSlideCompleteListener(slideToActView ->
            startActivity( new Intent(Homepage_activity.this, ScanActivity.class))
        );
    }

    private void initializeViews() {
        Swipe_button = findViewById(R.id.Swipe_button);
        user_icon = findViewById(R.id.user_icon);
        card_ViewSchedule=findViewById(R.id.card_ViewSchedule);
        card_ViewPatrols=findViewById(R.id.card_ViewPatrols);
        dbPatrol = FirebaseFirestore.getInstance();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

    }
}
