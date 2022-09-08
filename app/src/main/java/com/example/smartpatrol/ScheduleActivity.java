package com.example.smartpatrol;

import static com.example.smartpatrol.classes.Constants.PATROLS;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smartpatrol.classes.Guard;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class ScheduleActivity extends AppCompatActivity {
    Button Set;
    EditText Monday, Tuesday, Wednesday, Thursday, Friday;
    FirebaseFirestore dbSchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        Set=findViewById(R.id.Set);
        Monday=findViewById(R.id.Monday);
        Tuesday=findViewById(R.id.Tuesday);
        Wednesday=findViewById(R.id.Wednesday);
        Thursday=findViewById(R.id.Thursday);
        Friday=findViewById(R.id.Friday);
        dbSchedule=FirebaseFirestore.getInstance();

        Set.setOnClickListener(new View.OnClickListener() {
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
        });
    }
}