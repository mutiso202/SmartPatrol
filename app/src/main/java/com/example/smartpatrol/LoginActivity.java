package com.example.smartpatrol;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    FirebaseAuth mAuth;
    ImageView passwordVisibility;
    Button signIn;
    EditText email, pass;
    private static boolean isPasswordVisible=false;
    TextView RegisterBtn;
    ProgressBar progressBarLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        signIn = findViewById(R.id.signIn);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        passwordVisibility = findViewById(R.id.passwordVisibility);
        progressBarLogin=findViewById(R.id.progressbarLogin);
        RegisterBtn=findViewById(R.id.RegisterBtn);
        FirebaseUser user =mAuth.getCurrentUser();

        progressBarLogin.setVisibility(View.INVISIBLE);

        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, Homepage_activity.class));
            Toast.makeText(this, "Logged in!", Toast.LENGTH_SHORT).show();
            finish();
        }


        passwordVisibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPasswordVisible==false){
                    pass.setTransformationMethod(null);
                    passwordVisibility.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_visibility_on));
                    isPasswordVisible=true;
                }
                else if (isPasswordVisible==true){
                    pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    passwordVisibility.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_visibility_off_24));
                    isPasswordVisible=false;
                }
                else {
                    Toast.makeText(LoginActivity.this, "password boolean is not set ", Toast.LENGTH_SHORT).show();
                }
            }
        });

    signIn.setOnClickListener(v -> {
        String Email = email.getText().toString().trim();
        String Password = pass.getText().toString().trim();
        progressBarLogin.setVisibility(View.VISIBLE);

        if (!email.equals("") && !pass.equals("")){
            mAuth.signInWithEmailAndPassword(Email, Password).addOnSuccessListener(authResult -> {
                startActivity( new Intent(LoginActivity.this, Homepage_activity.class));
            }).addOnFailureListener
                    (e -> Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show());


        }
        else {
            toastMessage("You didn't fill in all the fields.");
        }
    });

    RegisterBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
            startActivity(intent);
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
