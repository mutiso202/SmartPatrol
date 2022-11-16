package com.example.smartpatrol;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PostSiteActivity extends AppCompatActivity {
    TextView map_editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_site);
        initializeData();
        setListeners();
    }

    private void setListeners() {
        map_editText.setOnClickListener(view -> {
            Intent intent=new Intent(PostSiteActivity.this,LocatorMapActivity.class);
            startActivity(intent);
        });
    }

    private void initializeData() {
        map_editText=findViewById(R.id.map_edittext);
    }
}