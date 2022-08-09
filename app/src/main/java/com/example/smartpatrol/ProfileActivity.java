package com.example.smartpatrol;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartpatrol.classes.Guard;

public class ProfileActivity extends AppCompatActivity {
    TextView user_name,user_email;
    TextView incident_number,patrol_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        user_name=findViewById(R.id.user_name);
        user_email=findViewById(R.id.user_email);
        incident_number=findViewById(R.id.incident_number);
        patrol_number=findViewById(R.id.patrol_number);

        Guard guard=new Guard();
        int inc_number=guard.getIncidentCount();
        incident_number.setText(inc_number);

        int pat_number=guard.getPatrolCount();
        patrol_number.setText(pat_number);

        String userName=guard.getName();
        user_name.setText(userName);

        String userEmail=guard.getEmail();
        user_email.setText(userEmail);
    }
}