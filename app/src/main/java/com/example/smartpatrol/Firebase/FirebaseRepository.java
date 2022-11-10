package com.example.smartpatrol.Firebase;

import com.example.smartpatrol.interfaces.callback;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.Map;

public class FirebaseRepository {



   public static void getDocuments(DocumentReference reference, callback callback){
       reference.get()
               .addOnSuccessListener(v-> callback.onSuccess(v))
               .addOnFailureListener(e->callback.onFailure(e));
   }
    public static void getDocumentsInCollection(CollectionReference reference, callback callback){
        reference.get()
                .addOnCompleteListener(v-> callback.onSuccess(v))
                .addOnFailureListener(e->callback.onFailure(e));
    }

   public static void setDocument(Map<String,Object> map, DocumentReference reference, callback call){
       reference.set(map)
               .addOnSuccessListener(v-> call.onSuccess(v))
               .addOnFailureListener(e->call.onFailure(e));
   }
    public static void setDocumentInCollection(Map<String,Object> map, CollectionReference reference, callback call){
        reference.document().set(map)
                .addOnSuccessListener(v-> call.onSuccess(v))
                .addOnFailureListener(e->call.onFailure(e));
    }
   public static void checkIfDocumentExists(CollectionReference reference, String key, String object, callback callback){
       Query query=reference.whereEqualTo(key,object);
       query.get().addOnSuccessListener(v->{

           if (!v.isEmpty()) callback.onSuccess(v);
           else callback.onFailure(v);
                       }

                       )
               .addOnFailureListener(e -> callback.onFailure(e));
   }
}
