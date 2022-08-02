package com.example.smartpatrol;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.ncorti.slidetoact.SlideToActView;

class Homepage_activity extends AppCompatActivity {
   //SlideToActView Swipe_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        /*SlideToActView Swipe_button = findViewById(R.id.Swipe_button);
        Swipe_button.setOnSlideCompleteListener(new SlideToActView.OnSlideCompleteListener() {
            @Override
            public void onSlideComplete(@NonNull SlideToActView slideToActView) {
                Intent intent= new Intent(Homepage_activity.this, patrolActivity.class);
                startActivity(intent);
                finish();
            }
        });*/

    }
}
