package com.example.smartpatrol.classes;

import com.example.smartpatrol.interfaces.callback;
import com.example.smartpatrol.interfaces.logInGuard;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public abstract class FirebaseRepository {
    static FirebaseAuth mAuth;
    public FirebaseRepository(){
        mAuth=FirebaseAuth.getInstance();
    }
    public static void LoginUserWithEmailAndPassword(String email, String password, logInGuard logInGuard){
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnSuccessListener(authResult ->logInGuard.successfulLogIn())
                .addOnFailureListener(e -> logInGuard.failedLogIn(e.getMessage()));

    }

}
