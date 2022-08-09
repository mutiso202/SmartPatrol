package com.example.smartpatrol.util;

import static com.example.smartpatrol.util.Errors.*;

import android.widget.EditText;

import com.example.smartpatrol.interfaces.callback;
import com.google.firebase.auth.FirebaseAuth;

public abstract class AppSystem implements callback {

    public AppSystem(){

    }
    public static String checkForLoginErrors(String emaiL, String password){
        if (checkEmaiLForErrors(emaiL)!=null)return checkEmaiLForErrors(emaiL);
        if (checkPasswordForErrors(password)!=null)return checkPasswordForErrors(password);
            return null;

    }
    private static String checkEmaiLForErrors(String email){
        if (email.isEmpty()) return EMAIL_IS_EMPTY_ERROR;
        if (!email.contains("@"))return EMAIL_INVALID_ERROR;
        if (!email.contains(".com"))return EMAIL_INVALID_ERROR;
        return null;

    }
    private static String checkPasswordForErrors(String password){
        if (password.isEmpty()) return PASSWORD_IS_EMPTY_ERROR;
        if (password.length()<=8)return PASSWORD_LENGTH_ERROR;
        return null;

    }
    @Override
    public void onSuccess(Object o) {

    }

    @Override
    public void onFailure(Object o) {

    }
}
