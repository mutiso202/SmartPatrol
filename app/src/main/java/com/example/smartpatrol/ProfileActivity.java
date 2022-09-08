package com.example.smartpatrol;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.smartpatrol.classes.Guard;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

public class ProfileActivity extends AppCompatActivity {
    TextView user_name,user_email;
    TextView incident_number,patrol_number;
    Button signOutButton;
    CardView cardView_Report, cardView_EndPatrol;
    ImageView imageViewProfile,imageArrowBack,edit;
    String userID;
    FirebaseAuth mAuth;



    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        user_name=findViewById(R.id.user_name);
        user_email=findViewById(R.id.user_email);
        incident_number=findViewById(R.id.incident_number);
        patrol_number=findViewById(R.id.patrol_number);
        signOutButton=findViewById(R.id.signOutButton);
        cardView_Report=findViewById(R.id.cardView_Report);
        cardView_EndPatrol=findViewById(R.id.cardView_EndPatrol);
        imageViewProfile=findViewById(R.id.imageViewProfile);
        imageArrowBack=findViewById(R.id.imageArrowBack);
        edit=findViewById(R.id.edit);
        FirebaseUser user= mAuth.getCurrentUser();

        user_name.setText(mAuth.getCurrentUser().getDisplayName());



        imageArrowBack.setOnClickListener(v -> {
            Intent intent=new Intent(ProfileActivity.this, Homepage_activity.class);
            startActivity(intent);
        });

        cardView_Report.setOnClickListener(v -> {
            Intent intent=new Intent(ProfileActivity.this, Incident1Activity.class);
            startActivity(intent);
        });

        signOutButton.setOnClickListener(v -> {
                    moveTaskToBack(true);
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(1);
                });

            cardView_EndPatrol.setOnClickListener(v1 -> {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            });

    }


    }

