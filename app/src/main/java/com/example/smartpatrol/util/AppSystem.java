package com.example.smartpatrol.util;

import static com.example.smartpatrol.util.Errors.*;

import android.widget.EditText;

import com.example.smartpatrol.interfaces.callback;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Random;

public abstract class AppSystem implements callback {
    private static final String ALLOWED_CHARACTERS ="0123456789qwertyuiopasdfghjklzxcvbnm";
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
    public static String getRandomString(final int sizeOfRandomString)
    {
        final Random random=new Random();
        final StringBuilder sb=new StringBuilder(sizeOfRandomString);
        for(int i=0;i<sizeOfRandomString;++i)
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        return sb.toString();
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
