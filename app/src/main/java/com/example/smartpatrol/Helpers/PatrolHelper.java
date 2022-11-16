package com.example.smartpatrol.Helpers;

import static com.example.smartpatrol.Firebase.FirebaseCollections.GUARDS_REFERENCE;
import static com.example.smartpatrol.Firebase.FirebaseCollections.INCIDENT_REFERENCE;

import com.example.smartpatrol.Firebase.FirebaseConstants;
import com.example.smartpatrol.Firebase.FirebaseFields;
import com.example.smartpatrol.Firebase.FirebasePaths;
import com.example.smartpatrol.Firebase.FirebaseRepository;
import com.example.smartpatrol.Models.Incident;
import com.example.smartpatrol.Models.Patrol;
import com.example.smartpatrol.interfaces.callback;
import com.example.smartpatrol.util.LocationUtils;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.AggregateQuery;
import com.google.firebase.firestore.AggregateQuerySnapshot;
import com.google.firebase.firestore.AggregateSource;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PatrolHelper {
    public static void uploadPatrol(Patrol  patrol,String guardId, callback callback){

            CollectionReference reference= GUARDS_REFERENCE.document(guardId).collection(FirebasePaths.PATROLS_PATH);
            FirebaseRepository.setDocumentInCollection(createIncidentMap(patrol),reference,callback);

    }


    private static Map<String, Object> createIncidentMap(Patrol patrol) {
        Map<String,Object> hashMap = new HashMap();
      //  hashMap.put("patrol", patrol);
        hashMap.put(FirebaseFields.STARTINGLOCATION, LocationUtils.convertLatLongToGeopoint(patrol.getStartLocation()));
        hashMap.put(FirebaseFields.ENDINGLOCATION,  LocationUtils.convertLatLongToGeopoint(patrol.getEndLocation()));
        hashMap.put(FirebaseFields.DAY, patrol.getDay());
        hashMap.put(FirebaseFields.HOUR, patrol.getHour());
        hashMap.put(FirebaseFields.MINUTE, patrol.getMinute());
        hashMap.put(FirebaseFields.MONTH, patrol.getMonth());
        hashMap.put(FirebaseFields.YEAR, patrol.getYear());
        return hashMap;
    }

    public static void getPatrolsFromDb(String id, callback callback){
        CollectionReference reference= GUARDS_REFERENCE.document(id).collection(FirebasePaths.PATROLS_PATH);
        FirebaseRepository.getDocumentsInCollection(reference,new callback() {
            @Override
            public void onSuccess(Object object) {

                Task<QuerySnapshot> task=(Task<QuerySnapshot>)object;

                List<Patrol> patrolList=new ArrayList<>();

                if (task.isSuccessful()){
                    for (DocumentSnapshot snapshot:task.getResult())
                        patrolList.add(updateDetails(snapshot));

                    callback.onSuccess(patrolList);

                }

            }

            @Override
            public void onFailure(Object o) {

            }


        });
    }

    private static Patrol updateDetails(DocumentSnapshot snapshot) {
        Patrol patrol=new Patrol();
        patrol.setStartLocation(LocationUtils.convertGeopointToLatLong(Objects.requireNonNull(snapshot.getGeoPoint(FirebaseFields.STARTINGLOCATION))));
        patrol.setEndLocation(LocationUtils.convertGeopointToLatLong(Objects.requireNonNull(snapshot.getGeoPoint(FirebaseFields.ENDINGLOCATION))));
        Calendar calendar=Calendar.getInstance();

        calendar.set(Math.toIntExact(snapshot.getLong(FirebaseFields.YEAR)),
                Math.toIntExact(snapshot.getLong(FirebaseFields.MONTH)),
                Math.toIntExact(snapshot.getLong(FirebaseFields.DAY)),
                Math.toIntExact(snapshot.getLong(FirebaseFields.HOUR)),
                Math.toIntExact(snapshot.getLong(FirebaseFields.MINUTE))
                );

        patrol.setCalendar(calendar);
        return patrol;
    }

    /*public static void getNumberOfPatrolsByGuard(String guardId, FirebaseDocumentCount count){
        CollectionReference reference= GUARDS_REFERENCE.document(guardId).collection(FirebasePaths.PATROLS_PATH);
        AggregateQuery countQuery =reference.count();

        countQuery.get(AggregateSource.SERVER).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                AggregateQuerySnapshot snapshot = task.getResult();
                count.onCount(snapshot.getCount());
            } else {
                count.onError("Count failed: "+ task.getException());
            }
        });

    }*/

}
