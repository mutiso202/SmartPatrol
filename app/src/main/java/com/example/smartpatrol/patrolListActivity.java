package com.example.smartpatrol;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.smartpatrol.adapters.PatrolListAdapter;
import com.example.smartpatrol.classes.Patrol;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class patrolListActivity extends AppCompatActivity {
    RecyclerView Recycler_view;
    ArrayList<Patrol> arrayListPatrol;
    PatrolListAdapter patrolListAdapter;
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patrol_list);
        Recycler_view=findViewById(R.id.Recycler_view);
        Recycler_view.setHasFixedSize(true);
        Recycler_view.setLayoutManager(new LinearLayoutManager(this));

        db=FirebaseFirestore.getInstance();
        arrayListPatrol=new ArrayList<Patrol>();
        patrolListAdapter=new PatrolListAdapter(patrolListActivity.this,arrayListPatrol);

        ChangeListener();
    }

    private void ChangeListener() {
    }
}