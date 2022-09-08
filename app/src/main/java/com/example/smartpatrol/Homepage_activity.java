package com.example.smartpatrol;

import static com.example.smartpatrol.classes.Constants.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartpatrol.classes.Constants;
import com.example.smartpatrol.classes.Guard;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;
import com.ncorti.slidetoact.SlideToActView;

import java.util.HashMap;

public class Homepage_activity extends AppCompatActivity {
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    SlideToActView Swipe_button;
    ImageView user_icon;
    TextView textViewPatrol, Time;
    FirebaseFirestore dbPatrol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Swipe_button = findViewById(R.id.Swipe_button);
        user_icon = findViewById(R.id.user_icon);
        textViewPatrol=findViewById(R.id.textViewPatrol);
        Time=findViewById(R.id.Time);
        dbPatrol=FirebaseFirestore.getInstance();




        textViewPatrol.setOnClickListener(v -> {
            Intent patrolList=new Intent(Homepage_activity.this,patrolListActivity.class);
            startActivity(patrolList);
        });

        user_icon.setOnClickListener(v -> {
            Intent profile = new Intent(Homepage_activity.this, ProfileActivity.class);
            startActivity(profile);
        });


        Swipe_button.setOnSlideCompleteListener(slideToActView -> {
            Intent intent = new Intent(Homepage_activity.this, patrolActivity.class);
            startActivity(intent);

            /*int startPatrol=getTaskId();

            DocumentReference documentRef=dbPatrol.collection("Guard").document(new Guard().getuID()).collection(PATROLS).document();

            HashMap hashMapPatrol=new HashMap();
            hashMapPatrol.put("Id",startPatrol);

            documentRef.set(hashMapPatrol).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(Homepage_activity.this, "Patrol started.", Toast.LENGTH_SHORT).show();
                }
            });*/





        });

    }
}
