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
import com.example.smartpatrol.classes.Schedule;

import java.util.ArrayList;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder>{

    Context context;

    public ScheduleAdapter(Context context, ArrayList<Schedule> arrayListSchedule) {
        this.context = context;
        this.arrayListSchedule = arrayListSchedule;
    }

    ArrayList<Schedule> arrayListSchedule;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.schedule_card,parent,false);
        return new ScheduleAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Schedule schedule=arrayListSchedule.get(position);

    }

    @Override
    public int getItemCount() {
        return arrayListSchedule.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        public TextView DayScheduled;
        public TextView TimeScheduled;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            DayScheduled=(itemView).findViewById(R.id.DayScheduled);
            TimeScheduled=(itemView).findViewById(R.id.TimeScheduled);
        }
    }
}
