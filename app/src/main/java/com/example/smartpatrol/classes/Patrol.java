package com.example.smartpatrol.classes;

import com.google.firebase.Timestamp;

public class Patrol {

    Timestamp Time;

    public Patrol(){ }

    public Patrol(Timestamp time) {
        Time = time;
    }

    public Timestamp getTime() {
        return Time;
    }

    public void setTime(Timestamp time) {
        Time = time;
    }
}
