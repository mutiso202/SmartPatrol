package com.example.smartpatrol;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Incident1Activity extends AppCompatActivity {
    Button next_incident1;
    ImageView back_incident1;
    RadioGroup radioGroup;
    RadioButton radioButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incident1);

        next_incident1=findViewById(R.id.next_incident1);
        back_incident1=findViewById(R.id.back_incident1);
        radioGroup=findViewById(R.id.radioGroup);


        next_incident1.setOnClickListener(v -> {
            Intent intent=new Intent(Incident1Activity.this, Incident2Activity.class);
            intent.putExtra("RadioGroup", (Parcelable) radioButton);
            startActivity(intent);
        });


        back_incident1.setOnClickListener(v -> {
                Intent intent=new Intent(Incident1Activity.this,patrolActivity.class);
                startActivity(intent);
            });

    }
    public void checkButton(View v){
        int radioId=radioGroup.getCheckedRadioButtonId();
        radioButton=findViewById(radioId);
        Toast.makeText(this, "Selected option:" +radioButton.getText(), Toast.LENGTH_SHORT).show();

    }
}
