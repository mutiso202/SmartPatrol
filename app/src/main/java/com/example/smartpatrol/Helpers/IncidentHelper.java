package com.example.smartpatrol.Helpers;

import static com.example.smartpatrol.Firebase.FirebaseCollections.INCIDENT_REFERENCE;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.smartpatrol.Firebase.FirebaseRepository;
import com.example.smartpatrol.Incident2Activity;
import com.example.smartpatrol.Models.Incident;
import com.example.smartpatrol.interfaces.callback;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;

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
        Map<String,Object> hashMap = new HashMap();
        hashMap.put("incident", incident);
        return hashMap;
    }

}
