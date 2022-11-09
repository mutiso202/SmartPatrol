package com.example.smartpatrol.util;

import androidx.annotation.NonNull;

import com.example.smartpatrol.Firebase.FunctionsConstants;
import com.example.smartpatrol.interfaces.callback;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.functions.FirebaseFunctions;
import com.google.firebase.functions.HttpsCallableResult;

import java.util.HashMap;
import java.util.Map;

public class validateQRcode {
    private static FirebaseFunctions mFunctions;

    public  validateQRcode(){
        mFunctions= FirebaseFunctions.getInstance();
    }

    public static Task<String> scanQrCode(int position) {

        String callbackName=getSelectedQR(position);

        return mFunctions
                .getHttpsCallable(callbackName)
                .call()
                .continueWith(task -> {

                    String result = (String) task.getResult().getData();
                    return result;
                });
    }
    public static boolean validateHttp(String httpFromScan){
        return httpFromScan.equals(FunctionsConstants.FIRST_CHECKPOINT_FUNCTION) ||
                httpFromScan.equals(FunctionsConstants.SECOND_CHECKPOINT_FUNCTION) ||
                httpFromScan.equals(FunctionsConstants.THIRD_CHECKPOINT_FUNCTION);
    }

    private static String getSelectedQR(int position) {
        switch (position){
            case 1:
                return FunctionsConstants.FIRST_CHECKPOINT_FUNCTION;

            case 2:
                 return FunctionsConstants.SECOND_CHECKPOINT_FUNCTION;

            case 3:
                return FunctionsConstants.THIRD_CHECKPOINT_FUNCTION;

        }
       return null;
    }

    public static void validateCodeScan(int count, callback callback) {
      scanQrCode(count)
                .addOnCompleteListener(v->callback.onSuccess(v))
                .addOnFailureListener(e->callback.onFailure(e));
    }
       /* validateQRcode.validateCodeScan(count,new callback() {
                                @Override
                                public void onSuccess(Object o) {


                                }

                                @Override
                                public void onFailure(Object o) {
                                    Toast.makeText(getApplicationContext(), "Error scanning code", Toast.LENGTH_SHORT).show();
                        }
                    });*/

}
