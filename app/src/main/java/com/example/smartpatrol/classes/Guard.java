package com.example.smartpatrol.classes;


import android.net.Uri;

import com.example.smartpatrol.Models.Incident;
import com.example.smartpatrol.Models.Patrol;
import com.example.smartpatrol.interfaces.callback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.ArrayList;

public class Guard  {
    ArrayList<Patrol> patrols;
    ArrayList<Incident> incidents;
   // Schedule schedule;
    FirebaseUser user;
    FirebaseAuth mAuth;

    public Guard(){
        mAuth=FirebaseAuth.getInstance();
        user=mAuth.getCurrentUser();
    }

    public String getContact() {return user.getPhoneNumber();}

    public void setContact(PhoneAuthCredential authCredential, callback callback) {
        user.updatePhoneNumber(authCredential);
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

    public ArrayList<Incident> getIncidents() {
        return incidents;
    }

    public void setIncidents(ArrayList<Incident> incidents) {
        this.incidents = incidents;
    }

    //public Schedule getSchedule() {
   //     return schedule;
   // }

   // public void setSchedule(Schedule schedule) {
     //   this.schedule = schedule;
   // }

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


}
