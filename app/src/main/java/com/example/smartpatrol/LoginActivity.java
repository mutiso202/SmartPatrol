package com.example.smartpatrol;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private FirebaseAuth mAuth;

    Button signIn;
    EditText email, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        signIn = findViewById(R.id.signIn);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        FirebaseUser user =mAuth.getCurrentUser();
        if (user!=null) Toast.makeText(this, "User is signed in", Toast.LENGTH_SHORT).show();
        else Toast.makeText(this, "user is signed out", Toast.LENGTH_SHORT).show();




    signIn.setOnClickListener(v -> {
        String Email = email.getText().toString();
        String Password = pass.getText().toString();
        if (!email.equals("") && !pass.equals("")){
            mAuth.signInWithEmailAndPassword(Email, Password).addOnSuccessListener(authResult -> {
                startActivity( new Intent(LoginActivity.this, Homepage_activity.class));
            }).addOnFailureListener(e -> Toast.makeText(LoginActivity.this, "login failed", Toast.LENGTH_SHORT).show());


        }
        else {
            toastMessage("You didn't fill in all the fields.");
        }
    });



    }

    private void toastMessage(String s) {
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

}
