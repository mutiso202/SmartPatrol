package com.example.smartpatrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class patrolActivity extends AppCompatActivity {
    Button buttonScan, buttonMultimedia, buttonIncident, buttonEndPatrol, btnViewMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patrol);
        buttonScan=findViewById(R.id.buttonScan);
        buttonMultimedia=findViewById(R.id.buttonMultimedia);
        buttonIncident=findViewById(R.id.buttonIncident);
        buttonEndPatrol=findViewById(R.id.buttonEndPatrol);
        btnViewMap=findViewById(R.id.btn_viewMap);

        buttonIncident.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(patrolActivity.this, IncidentActivity.class);
                startActivity(intent);
                finish();
            }
        });
        buttonEndPatrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(patrolActivity.this, Homepage_activity.class);
                startActivity(intent);
                finish();
            }
        });
        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(patrolActivity.this, ScanActivity.class);
                startActivity(intent);
                finish();
            }
        });
        buttonMultimedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(patrolActivity.this, MultimediaActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnViewMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(patrolActivity.this, patrolmapActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}