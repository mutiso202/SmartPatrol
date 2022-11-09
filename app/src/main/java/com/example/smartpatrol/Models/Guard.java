package com.example.smartpatrol.Models;

import com.example.smartpatrol.classes.Schedule;

import java.util.ArrayList;

public class Guard {
    ArrayList<Patrol> patrols;
    ArrayList<Incident> incidents;
    ArrayList<Schedule> schedule;
    String name,phoneNumber,email,password;

    public ArrayList<Patrol> getPatrols() {
        return patrols;
    }

    public void setPatrols(ArrayList<Patrol> patrols) {
        this.patrols = patrols;
    }

    public ArrayList<Incident> getIncidents() {
        return incidents;
    }

    public void setIncidents(ArrayList<Incident> incidents) {
        this.incidents = incidents;
    }

    public ArrayList<Schedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(ArrayList<Schedule> schedule) {
        this.schedule = schedule;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
