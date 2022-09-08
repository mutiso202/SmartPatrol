package com.example.smartpatrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class EditProfileActivity extends AppCompatActivity {
    ImageView imageViewProfile;
    EditText EditName;
    Button SaveProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        imageViewProfile=findViewById(R.id.imageViewProfile);
        EditName=findViewById(R.id.EditName);
        SaveProfile=findViewById(R.id.SaveProfile);

        imageViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        SaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadProfileImage();
                Intent intent=new Intent(EditProfileActivity.this,ProfileActivity.class);
                startActivity(intent);
            }

            private void uploadProfileImage() {
            }
        });
    }
}