package com.example.smartpatrol;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartpatrol.adapters.PatrolListAdapter;
import com.example.smartpatrol.classes.Patrol;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class PatrolListActivity extends AppCompatActivity {
    RecyclerView Recycler_view;
    ArrayList<Patrol> arrayListPatrol;
    PatrolListAdapter patrolListAdapter;
    FirebaseFirestore db;
    ImageView backFromPatrol;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patrol_list);

        backFromPatrol=findViewById(R.id.backFromPatrol);
        Recycler_view=findViewById(R.id.view_patrols_recyclerView);
        Recycler_view.setHasFixedSize(true);
        Recycler_view.setLayoutManager(new LinearLayoutManager(this));

        db=FirebaseFirestore.getInstance();
        arrayListPatrol=new ArrayList<Patrol>();
        patrolListAdapter=new PatrolListAdapter(PatrolListActivity.this,arrayListPatrol);

        backFromPatrol.setOnClickListener(view -> {
            Intent intent=new Intent(PatrolListActivity.this, Homepage_activity.class);
            startActivity(intent);
        });

        ChangeListener();
    }

    private void ChangeListener() {
    }
}