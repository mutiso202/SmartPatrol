package com.example.smartpatrol.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartpatrol.R;
import com.example.smartpatrol.classes.Patrol;

import java.util.ArrayList;

public class PatrolListAdapter extends RecyclerView.Adapter<PatrolListAdapter.MyViewHolder> {
    Context context;
    ArrayList<Patrol> arrayListPatrol;

    public PatrolListAdapter(Context context, ArrayList<Patrol> arrayListPatrol) {
        this.context = context;
        this.arrayListPatrol = arrayListPatrol;
    }

    @NonNull
    @Override
    public PatrolListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.patrol_card,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PatrolListAdapter.MyViewHolder holder, int position) {
    Patrol patrol=arrayListPatrol.get(position);

    }

    @Override
    public int getItemCount() {
        return arrayListPatrol.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView TimeTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            TimeTextView = itemView.findViewById(R.id.TimeTextView);
        }
    }
}
