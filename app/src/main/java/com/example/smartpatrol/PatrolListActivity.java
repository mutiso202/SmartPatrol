package com.example.smartpatrol;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartpatrol.Helpers.PatrolHelper;
import com.example.smartpatrol.adapters.PatrolListAdapter;
import com.example.smartpatrol.Models.Patrol;
import com.example.smartpatrol.classes.Guard;
import com.example.smartpatrol.interfaces.callback;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class PatrolListActivity extends AppCompatActivity {
    RecyclerView Recycler_view;
    ArrayList<Patrol> arrayListPatrol;
    PatrolListAdapter patrolListAdapter;

    ImageView backFromPatrol;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patrol_list);
        initializeViews();
        setListener();

        PatrolHelper.getPatrolsFromDb(new Guard().getuID(), new callback() {
            @Override
            public void onSuccess(Object o) {
                arrayListPatrol=(ArrayList<Patrol>) o;
                setAdapter();


            }

            @Override
            public void onFailure(Object o) {

            }
        });



    }

    private void setListener() {
        backFromPatrol.setOnClickListener(view ->
                startActivity(new Intent(PatrolListActivity.this, Homepage_activity.class))
        );
    }

    private void setAdapter() {
        patrolListAdapter=new PatrolListAdapter(PatrolListActivity.this,arrayListPatrol);
        Recycler_view.setAdapter(patrolListAdapter);
        Recycler_view.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL,false));
    }

    private void initializeViews() {

        backFromPatrol=findViewById(R.id.backFromPatrol);
        Recycler_view=findViewById(R.id.view_patrols_recyclerView);
        arrayListPatrol=new ArrayList<>();
    }

}