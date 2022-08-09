package com.example.smartpatrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Incident1Activity extends AppCompatActivity {
    Button next_incident1;
    ImageView back_incident1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incident1);
        next_incident1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Incident1Activity.this, Incident2Activity.class);
                startActivity(intent);
            }
        });
        back_incident1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Incident1Activity.this,patrolActivity.class);
                startActivity(intent);
            }
        });
    }
}