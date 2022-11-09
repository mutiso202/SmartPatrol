package com.example.smartpatrol.Models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Calendar;

public class Incident implements Parcelable {
    private  String description,situation,attachment,incidentId;
    Calendar calendar;

    public void updateDate(){
        calendar=Calendar.getInstance();
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    protected Incident(Parcel in) {
        description = in.readString();
        situation = in.readString();
        attachment = in.readString();
        incidentId=in.readString();

    }

    public static final Creator<Incident> CREATOR = new Creator<Incident>() {
        @Override
        public Incident createFromParcel(Parcel in) {
            return new Incident(in);
        }

        @Override
        public Incident[] newArray(int size) {
            return new Incident[size];
        }
    };

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public Incident(){
    }

    public Incident(String description) {
        this.description = description;
        this.situation = situation;
        this.attachment = attachment;
    }



    public String getDescription() {
        return description;
    }

    public String getSituation() {
        return situation;
    }

    public String getAttachment() {
        return attachment;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(description);
        parcel.writeString(situation);
        parcel.writeString(attachment);
        parcel.writeString(incidentId);
    }
}

