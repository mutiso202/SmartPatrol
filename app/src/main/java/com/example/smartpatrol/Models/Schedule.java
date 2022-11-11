package com.example.smartpatrol.Models;

import java.util.Date;
import java.util.Map;

public class Schedule {
    Map<String, Date> Monday,Tuesday,Wednesday,Thursday,Friday;

    public Map<String, Date> getMonday() {
        return Monday;
    }

    public void setMonday(Map<String, Date> monday) {
        Monday = monday;
    }

    public Map<String, Date> getTuesday() {
        return Tuesday;
    }

    public void setTuesday(Map<String, Date> tuesday) {
        Tuesday = tuesday;
    }

    public Map<String, Date> getWednesday() {
        return Wednesday;
    }

    public void setWednesday(Map<String, Date> wednesday) {
        Wednesday = wednesday;
    }

    public Map<String, Date> getThursday() {
        return Thursday;
    }

    public void setThursday(Map<String, Date> thursday) {
        Thursday = thursday;
    }

    public Map<String, Date> getFriday() {
        return Friday;
    }

    public void setFriday(Map<String, Date> friday) {
        Friday = friday;
    }
}
