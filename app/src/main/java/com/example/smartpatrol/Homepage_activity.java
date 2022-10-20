package com.example.smartpatrol;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.ncorti.slidetoact.SlideToActView;

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
            Intent intent = new Intent(Homepage_activity.this, ScanActivity.class);
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
