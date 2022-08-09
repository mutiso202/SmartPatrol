package com.example.smartpatrol.classes;

import static com.example.smartpatrol.classes.FirebaseRepository.*;
import static com.example.smartpatrol.util.AppSystem.*;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.example.smartpatrol.interfaces.callback;
import com.example.smartpatrol.interfaces.logInGuard;
import com.example.smartpatrol.util.AppSystem;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.ArrayList;

public class Guard  {
    String uID,email,name;
    ArrayList<Patrol> patrols;
    ArrayList<Incidents> incidents;
    Schedule schedule;
    Uri profilePicture;
    FirebaseUser user;
    FirebaseAuth mAuth;


    public Guard(){
        user=mAuth.getCurrentUser();
    }

    public String getuID() {
        return user.getUid();
    }

    public String getEmail() {
        return user.getEmail();
    }

    public void setEmail(String email, callback callback) {
        user.updateEmail(email).addOnSuccessListener(unused -> {
          callback.onSuccess(unused);
        }).addOnFailureListener(e -> {
            callback.onFailure(e);
        });
    }

    public String getName() {
        return user.getDisplayName();
    }

    public void setName(String name,callback callback) {
      UserProfileChangeRequest userProfileChangeRequest=new UserProfileChangeRequest.Builder()
              .setDisplayName(name)
              .build();
      user.updateProfile(userProfileChangeRequest).addOnSuccessListener(unused -> callback.onSuccess(unused))
              .addOnFailureListener(e -> callback.onFailure(e));
    }

    public ArrayList<Patrol> getPatrols() {
        return patrols;
    }

    public void setPatrols(ArrayList<Patrol> patrols) {
        this.patrols = patrols;
    }

    public ArrayList<Incidents> getIncidents() {
        return incidents;
    }

    public void setIncidents(ArrayList<Incidents> incidents) {
        this.incidents = incidents;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Uri getProfilePicture() {
       return user.getPhotoUrl();
    }

    public void setProfilePicture(Uri profilePicture,callback callback) {
        UserProfileChangeRequest userProfileChangeRequest=new UserProfileChangeRequest.Builder()
                .setPhotoUri(profilePicture)
                .build();
       user.updateProfile(userProfileChangeRequest).addOnSuccessListener(unused ->callback.onSuccess(unused))
               .addOnFailureListener(e -> callback.onFailure(e));
    }
    public boolean isGuardLoggedIn(){
        if (user!=null)return true;
        else return false;
    }
    public void signOutGuard() {
      mAuth.signOut();
    }
    public int getPatrolCount(){
        return patrols.size();
    }
    public int getIncidentCount(){
        return incidents.size();
    }

    public void logInGuard(String email, String password, logInGuard logInGuard){
        if (checkForLoginErrors(email,password)!=null)logInGuard.failedLogIn(checkForLoginErrors(email,password));
        else LoginUserWithEmailAndPassword(email, password,logInGuard);
    }

}
