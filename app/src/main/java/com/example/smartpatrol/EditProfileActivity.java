package com.example.smartpatrol;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class EditProfileActivity extends AppCompatActivity {
    EditText name_edittext,phoneNumber_edittext, email_edittext;
    ImageView profile, backToProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        initializeData();
        setListeners();


    }

    private void setListeners() {
        backToProfile.setOnClickListener(view -> {
            Intent intent=new Intent(EditProfileActivity.this,ProfileActivity.class);
            startActivity(intent);
        });
    }

    private void initializeData() {
        name_edittext=findViewById(R.id.name_edittext);
        phoneNumber_edittext=findViewById(R.id.phoneNumber_edittext);
        email_edittext=findViewById(R.id.email_register);
        profile=findViewById(R.id.profile);
        backToProfile=findViewById(R.id.imageView);


    }
}