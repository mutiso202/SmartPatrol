package com.example.smartpatrol;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.smartpatrol.activities.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    EditText email_register, password_register, name_register, contact_register, ID_register, DOB_register;
    Button register,BackToLogin;
    FirebaseAuth F_Auth;
    FirebaseFirestore dbRegister;
    ProgressBar progressBarRegister;
    FirebaseUser firebaseuser;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email_register = findViewById(R.id.email_register);
        password_register = findViewById(R.id.password_register);
        name_register = findViewById(R.id.name_register);
        contact_register = findViewById(R.id.contact_register);
        ID_register = findViewById(R.id.ID_register);
        DOB_register = findViewById(R.id.DOB_register);
        register = findViewById(R.id.registerBtn);
        progressBarRegister = findViewById(R.id.progressbarRegister);
        BackToLogin=findViewById(R.id.BackToLogin);
        dbRegister = FirebaseFirestore.getInstance();
        F_Auth = FirebaseAuth.getInstance();
        firebaseuser=F_Auth.getCurrentUser();



        if (F_Auth.getCurrentUser() != null) {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            Toast.makeText(this, "Already registered!", Toast.LENGTH_SHORT).show();
            finish();
        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, Homepage_activity.class);
                startActivity(intent);

                String email = email_register.getText().toString();
                String name=name_register.getText().toString();
                String contact=contact_register.getText().toString();
                String ID=ID_register.getText().toString();
                String DOB=DOB_register.getText().toString();
                String password=password_register.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    email_register.setError("Email is Required.");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    password_register.setError("Password is Required.");
                    return;
                }
                if (password.length() < 6) {
                    password_register.setError("Password should be equal to or more than 6 characters.");
                    return;
                }
                progressBarRegister.setVisibility(View.VISIBLE);

                F_Auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            userID=F_Auth.getCurrentUser().getUid();
                            Toast.makeText(RegisterActivity.this, "User is created.", Toast.LENGTH_SHORT).show();
                            DocumentReference documentReference=dbRegister.collection("Guard").document(userID);

                            HashMap hashMapRegister= new HashMap();
                            hashMapRegister.put("Email", email);
                            hashMapRegister.put("Name",name);
                            hashMapRegister.put("Phone Number",contact);
                            hashMapRegister.put("ID",ID);
                            hashMapRegister.put("Date of Birth",DOB);
                            documentReference.set(hashMapRegister).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d("TAG","onSuccess: User profile is created for"+userID);
                                }
                            });


                            startActivity(new Intent(RegisterActivity.this, Homepage_activity.class));
                        } else {
                            Toast.makeText(RegisterActivity.this, "Error!" +task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }

                    ;
                });
            }

            ;

        });
        BackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
