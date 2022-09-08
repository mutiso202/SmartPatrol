package com.example.smartpatrol.classes;

public class Incident {
    private  String description,situation,attachment;
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
}

