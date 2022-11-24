package com.example.smartpatrol.activities;

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

import com.example.smartpatrol.AddDetailsActivity;
import com.example.smartpatrol.ContactInfoActivity;
import com.example.smartpatrol.ForgotPasswordActivity;
import com.example.smartpatrol.Helpers.GuardHelper;
import com.example.smartpatrol.Homepage_activity;
import com.example.smartpatrol.Models.Guard;
import com.example.smartpatrol.PostSiteActivity;
import com.example.smartpatrol.R;
import com.example.smartpatrol.RegisterActivity;
import com.example.smartpatrol.interfaces.callback;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    FirebaseAuth mAuth;
    ImageView passwordVisibility;
    Button signIn;
    EditText email, pass;
    private static boolean isPasswordVisible=false;
    ProgressBar progressBarLogin;
    TextView forgotPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeViews();
        checkIfGuardIsSignedIn();
        setListeners();

    }

    private void setListeners() {
        //ui
        passwordVisibility.setOnClickListener(v -> {
            if (!isPasswordVisible){
                pass.setTransformationMethod(null);
                passwordVisibility.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_visibility_on));
                isPasswordVisible=true;
            }
            else {
                pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                passwordVisibility.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_visibility_off_24));
                isPasswordVisible=false;
            }
        });

        //logging in
        signIn.setOnClickListener(v -> {
            progressBarLogin.setVisibility(View.VISIBLE);

            GuardHelper.logInGuard(gettingDataFromViews(), new callback() {
                        @Override
                        public void onSuccess(Object o) {
                            progressBarLogin.setVisibility(View.INVISIBLE);
                            startActivity( new Intent(LoginActivity.this, Homepage_activity.class));
                        }

                        @Override
                        public void onFailure(Object o) {
                            progressBarLogin.setVisibility(View.INVISIBLE);
                            Toast.makeText(LoginActivity.this, ((String)o), Toast.LENGTH_SHORT).show();
                        }
                    });
        });

        forgotPassword.setOnClickListener(view -> {
            Intent intent=new Intent(LoginActivity.this, ForgotPasswordActivity.class);
            startActivity(intent);
        });

        //redirect


    }

    private Guard gettingDataFromViews() {
        String Email = email.getText().toString().trim();
        String Password = pass.getText().toString().trim();
        Guard guard=new Guard();
        guard.setEmail(Email);
        guard.setPassword(Password);

        return guard;
    }

    private void checkIfGuardIsSignedIn() {
        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, Homepage_activity.class));
            Toast.makeText(this, "Logged in!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void initializeViews() {
        mAuth = FirebaseAuth.getInstance();
        signIn = findViewById(R.id.sign_In);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        passwordVisibility = findViewById(R.id.passwordVisibility);
        progressBarLogin=findViewById(R.id.progressbarLogin);
        forgotPassword=findViewById(R.id.forgotPassword);



        progressBarLogin.setVisibility(View.INVISIBLE);
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
