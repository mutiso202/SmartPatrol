package com.example.smartpatrol;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.ncorti.slidetoact.SlideToActView;

public class Homepage_activity extends AppCompatActivity {
   SlideToActView Swipe_button;
   Image user_icon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
         Swipe_button = findViewById(R.id.Swipe_button);
        Swipe_button.setOnSlideCompleteListener(slideToActView ->
            startActivity(new Intent(Homepage_activity.this,patrolActivity.class)));


    }
}
