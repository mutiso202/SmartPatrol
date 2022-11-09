package com.example.smartpatrol.Firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FirebaseConstants {
    public static final FirebaseFirestore db=FirebaseFirestore.getInstance();
    public static final FirebaseAuth mAuth=FirebaseAuth.getInstance();
    public static final FirebaseUser user=mAuth.getCurrentUser();
    private static final FirebaseStorage firebaseStorage=FirebaseStorage.getInstance();
    public static StorageReference firebaseStorageReference=firebaseStorage.getReference();
}
