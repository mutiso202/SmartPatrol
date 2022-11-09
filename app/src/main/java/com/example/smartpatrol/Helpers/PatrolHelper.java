package com.example.smartpatrol.Helpers;

import static com.example.smartpatrol.Firebase.FirebaseCollections.GUARDS_REFERENCE;
import static com.example.smartpatrol.Firebase.FirebaseCollections.INCIDENT_REFERENCE;

import com.example.smartpatrol.Firebase.FirebaseConstants;
import com.example.smartpatrol.Firebase.FirebasePaths;
import com.example.smartpatrol.Firebase.FirebaseRepository;
import com.example.smartpatrol.Models.Incident;
import com.example.smartpatrol.Models.Patrol;
import com.example.smartpatrol.interfaces.callback;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;

import java.util.HashMap;
import java.util.Map;

public class PatrolHelper {
    public static void uploadPatrol(Patrol  patrol,String guardId, callback callback){

            CollectionReference reference= GUARDS_REFERENCE.document(guardId).collection(FirebasePaths.PATROLS_PATH);
            FirebaseRepository.setDocumentInCollection(createIncidentMap(patrol),reference,callback);

    }


    private static Map<String, Object> createIncidentMap(Patrol patrol) {
        Map<String,Object> hashMap = new HashMap();
        hashMap.put("Patrol", patrol);
        return hashMap;
    }

}
