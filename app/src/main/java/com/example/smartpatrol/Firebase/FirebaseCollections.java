package com.example.smartpatrol.Firebase;

import static com.example.smartpatrol.Firebase.FirebaseConstants.db;
import static com.example.smartpatrol.Firebase.FirebasePaths.GUARDS_PATH;
import static com.example.smartpatrol.Firebase.FirebasePaths.INCIDENT_PATH;

import com.google.firebase.firestore.CollectionReference;

public class FirebaseCollections {
    public static final CollectionReference GUARDS_REFERENCE=db.collection(GUARDS_PATH);
    public static final CollectionReference INCIDENT_REFERENCE=db.collection(INCIDENT_PATH);
}
