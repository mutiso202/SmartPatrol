package com.example.smartpatrol.Helpers;

import static com.example.smartpatrol.Firebase.FirebaseConstants.mAuth;

import com.example.smartpatrol.Firebase.FirebaseCollections;
import com.example.smartpatrol.Firebase.FirebaseFields;
import com.example.smartpatrol.Firebase.FirebaseRepository;
import com.example.smartpatrol.Models.Guard;
import com.example.smartpatrol.interfaces.callback;
import com.google.firebase.firestore.DocumentReference;

import java.util.HashMap;
import java.util.Map;

public class GuardHelper {
    static Guard guardInHelper;
    public static void logInGuard(Guard guard, callback callback){
        guardInHelper=guard;
        if (!guard.getEmail().equals("") && !guard.getPassword().equals("")){
            mAuth
                    .signInWithEmailAndPassword(guard.getEmail(), guard.getPassword())
                    .addOnSuccessListener
                            (authResult -> {
                                //creating document reference
                                DocumentReference reference= FirebaseCollections.GUARDS_REFERENCE
                                        .document
                                                (authResult.getUser().getUid());


                                FirebaseRepository.
                                        checkIfDocumentExists
                                                (FirebaseCollections.GUARDS_REFERENCE, FirebaseFields.EMAIL,guard.getEmail(), new callback() {
                                                    @Override
                                                    public void onSuccess(Object o) {
                                                       callback.onSuccess(o);
                                                    }

                                                    @Override
                                                    public void onFailure(Object o) {
                                                        createGuardCollectionInFirebase(reference,callback);
                                                    }
                                                });

                            })

                    .addOnFailureListener
                            (e ->callback.onFailure(e.getMessage()));


        }
        else {
            callback.onFailure("You didn't fill in all the fields.");
        }

       //
    }

    private static void createGuardCollectionInFirebase(DocumentReference reference,callback callback) {
        FirebaseRepository.setDocument(createGuard(),reference,callback);
    }

    private static Map<String, Object> createGuard() {
        Map<String, Object> map=new HashMap<>();
        map.put(FirebaseFields.EMAIL,guardInHelper.getEmail());
        return map;
    }
}
