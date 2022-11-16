package com.example.smartpatrol.Helpers;

import static com.example.smartpatrol.Firebase.FirebaseCollections.INCIDENT_REFERENCE;

import android.telephony.SmsManager;

import com.example.smartpatrol.Firebase.FirebaseRepository;
import com.example.smartpatrol.Models.Incident;
import com.example.smartpatrol.classes.Guard;
import com.example.smartpatrol.interfaces.callback;
import com.google.firebase.firestore.DocumentReference;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class IncidentHelper {
    public static void uploadIncident(Incident incident, callback callback){
        if (validateIncident(incident)){
            DocumentReference reference= INCIDENT_REFERENCE.document(incident.getIncidentId());
            FirebaseRepository.setDocument(createIncidentMap(incident),reference,callback);
        }
        else callback.onFailure("Incident is not valid");

    }

    private static boolean validateIncident(Incident incident) {
        if (incident.getDescription().isEmpty())return false;
        return true;
    }

    private static Map<String, Object> createIncidentMap(Incident incident) {
        String phoneNumber="0746493912";
        String description=incident.getDescription();

        try {
            SmsManager smsManager=SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber,null,description,null,null);

        } catch (Exception  e){
            e.printStackTrace();

        }

        
        Map<String,Object> hashMap = new HashMap();
        hashMap.put("Date", new Date());
        hashMap.put("Description", incident.getDescription());
        hashMap.put("Guard", new Guard().getuID());
        hashMap.put("Title", incident.getSituation());
        return hashMap;
    }

}
