package com.example.smartpatrol;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class patrolActivity extends AppCompatActivity {
    Button buttonScan;
    ImageView IconUser;
    TextView OfficerName;
    TextView buttonReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_patrol);
        buttonReport= findViewById(R.id.buttonReport);
        buttonScan=findViewById(R.id.buttonScan);
        IconUser=findViewById(R.id.IconUser);
        OfficerName=findViewById(R.id.OfficerName);

        IconUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(patrolActivity.this,ProfileActivity.class);
                startActivity(intent);
            }
        });
        buttonScan.setOnClickListener(v -> {
            Intent intent=new Intent(patrolActivity.this, ScanActivity.class);
            startActivity(intent);
        });
        buttonReport.setOnClickListener(v -> {
            Intent intent=new Intent(patrolActivity.this, Incident1Activity.class);
            startActivity(intent);
        });

    }
}