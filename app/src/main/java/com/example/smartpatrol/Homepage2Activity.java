package com.example.smartpatrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.ncorti.slidetoact.SlideToActView;

public class Homepage2Activity extends AppCompatActivity {
    SlideToActView Swipe_homepage2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage2);
        Swipe_homepage2 = findViewById(R.id.Swipe_homepage2);
        Swipe_homepage2.setOnSlideCompleteListener(slideToActView ->
                startActivity(new Intent(Homepage2Activity.this,patrolActivity.class)));
    }
}