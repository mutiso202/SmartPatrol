package com.example.smartpatrol;

import static com.example.smartpatrol.classes.Constants.INCIDENT_PARCELABLE;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartpatrol.Models.Incident;
import com.example.smartpatrol.util.AppSystem;

public class Incident1Activity extends AppCompatActivity {
    Button next_incident1;
    ImageView back_incident1;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Incident incident;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incident1);
        initializeView();
        setListeners();

    }

    private void setListeners() {

        next_incident1.setOnClickListener(v -> {
            Intent intent=new Intent(Incident1Activity.this, Incident2Activity.class);
            intent.putExtra(INCIDENT_PARCELABLE, incident);
            startActivity(intent);
        });

        back_incident1.setOnClickListener(v ->
            startActivity(new Intent(Incident1Activity.this,ScanActivity.class))
        );
    }

    private void initializeView() {
        next_incident1=findViewById(R.id.next_incident1);
        back_incident1=findViewById(R.id.back_incident1);
        radioGroup=findViewById(R.id.radioGroup);
        incident=new Incident();
        incident.setIncidentId(AppSystem.getRandomString(12));

    }

    public void checkButton(View v){

        int radioId=radioGroup.getCheckedRadioButtonId();
        radioButton=findViewById(radioId);
        incident.setSituation(radioButton.getText().toString());

        Toast.makeText(this, "Selected option:" +radioButton.getText(), Toast.LENGTH_SHORT).show();

    }
}
