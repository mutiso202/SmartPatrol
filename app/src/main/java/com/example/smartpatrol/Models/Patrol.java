package com.example.smartpatrol.Models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;


import java.util.Calendar;

public class Patrol implements Parcelable {

    LatLng startLocation,endLocation;
    Calendar calendar;
    int hour, minute,month,day,year;

    public Patrol() {
    }

    protected Patrol(Parcel in) {
        startLocation = in.readParcelable(LatLng.class.getClassLoader());
        endLocation = in.readParcelable(LatLng.class.getClassLoader());
        hour = in.readInt();
        minute = in.readInt();
        month = in.readInt();
        day = in.readInt();
        year = in.readInt();
    }

    public static final Creator<Patrol> CREATOR = new Creator<Patrol>() {
        @Override
        public Patrol createFromParcel(Parcel in) {
            return new Patrol(in);
        }

        @Override
        public Patrol[] newArray(int size) {
            return new Patrol[size];
        }
    };

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public LatLng getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(LatLng startLocation) {
        this.startLocation = startLocation;
    }

    public LatLng getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(LatLng endLocation) {
        this.endLocation = endLocation;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
        setHour(calendar.get(Calendar.HOUR_OF_DAY));
        setMinute(Calendar.MINUTE);
        setMonth(Calendar.MONTH);
        setDay(Calendar.DAY_OF_MONTH);
        setYear(Calendar.YEAR);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {

        parcel.writeParcelable(startLocation, i);
        parcel.writeParcelable(endLocation, i);
        parcel.writeInt(hour);
        parcel.writeInt(minute);
        parcel.writeInt(month);
        parcel.writeInt(day);
        parcel.writeInt(year);

    }
}
