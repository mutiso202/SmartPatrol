package com.example.smartpatrol;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smartpatrol.activities.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {

    Button resetPassword;
    EditText email;
    //LoadingDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);



            resetPassword=(Button) findViewById(R.id.reset_password_btn);
            email=(EditText) findViewById(R.id.email_reset_password);
            //dialog=new LoadingDialog(ForgotPassword.this);

            resetPassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //dialog.startLoadingAlertDialog();
                    String emailString=email.getText().toString().trim();
                    if(emailString.isEmpty()) {
                        email.setError("invalid email");
                        //dialog.dismissDialog();
                    }
                    else {
                        sendEmailResetLink(emailString);
                    }
                }


            });


        }

        private void sendEmailResetLink(String emailAdress) {
            FirebaseAuth auth = FirebaseAuth.getInstance();


            auth.sendPasswordResetEmail(emailAdress)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Log.d("email", "Email sent.");

                                try {
                                    Intent intent = new Intent(Intent.ACTION_MAIN);
                                    intent.addCategory(Intent.CATEGORY_APP_EMAIL);
                                    startActivity(intent);
                                    //dialog.dismissDialog();
                                } catch (android.content.ActivityNotFoundException e) {
                                    Toast.makeText(ForgotPasswordActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                    startActivity(intent);
                                    //dialog.dismissDialog();
                                }
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ForgotPasswordActivity.this,"error in resetting password",Toast.LENGTH_SHORT).show();
                        }
                    });
    }
}