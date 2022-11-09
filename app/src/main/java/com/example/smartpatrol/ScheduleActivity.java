package com.example.smartpatrol;

import static com.example.smartpatrol.classes.Constants.PATROLS;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.smartpatrol.adapters.PatrolListAdapter;
import com.example.smartpatrol.adapters.ScheduleAdapter;
import com.example.smartpatrol.classes.Guard;
import com.example.smartpatrol.classes.Patrol;
import com.example.smartpatrol.classes.Schedule;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;

public class ScheduleActivity extends AppCompatActivity {
    RecyclerView Recycler_view;
    ArrayList<Schedule> arrayListSchedule;
    ScheduleAdapter ScheduleAdapter;
    FirebaseFirestore db;
    ImageView backFromPatrol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        backFromPatrol=findViewById(R.id.backFromPatrol);
        Recycler_view=findViewById(R.id.schedule_recyclerView);
        Recycler_view.setHasFixedSize(true);
        Recycler_view.setLayoutManager(new LinearLayoutManager(this));

        db=FirebaseFirestore.getInstance();
        arrayListSchedule=new ArrayList<Schedule>();
        ScheduleAdapter=new ScheduleAdapter(ScheduleActivity.this, arrayListSchedule);

        backFromPatrol.setOnClickListener(view -> {
            Intent intent=new Intent(ScheduleActivity.this, Homepage_activity.class);
            startActivity(intent);
        });

        /*Set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ScheduleActivity.this, Homepage_activity.class);
                startActivity(intent);

                String monday= Monday.getText().toString().trim();
                String tuesday= Tuesday.getText().toString().trim();
                String wednesday=Wednesday.getText().toString().trim();
                String thursday=Thursday.getText().toString().trim();
                String friday=Friday.getText().toString().trim();

                HashMap hashMapSchedule= new HashMap();
                hashMapSchedule.put("Monday",monday);
                hashMapSchedule.put("Tuesday",tuesday);
                hashMapSchedule.put("Wednesday",wednesday);
                hashMapSchedule.put("Thursday",thursday);
                hashMapSchedule.put("Friday",friday);

                dbSchedule.collection("Guard").document(new Guard().getuID()).collection("Schedule").document().set(hashMapSchedule)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(ScheduleActivity.this, "Schedule set.", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(ScheduleActivity.this, "Schedule has not been set.", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });*/
    }
}