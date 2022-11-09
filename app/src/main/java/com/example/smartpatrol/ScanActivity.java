package com.example.smartpatrol;

import static com.example.smartpatrol.classes.Constants.PATROL_PARCELABLE;
import static com.example.smartpatrol.util.validateQRcode.validateCodeScan;
import static com.example.smartpatrol.util.validateQRcode.validateHttp;

import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartpatrol.Firebase.FunctionsConstants;
import com.example.smartpatrol.Helpers.PatrolHelper;
import com.example.smartpatrol.Models.Patrol;
import com.example.smartpatrol.classes.CaptureAct;
import com.example.smartpatrol.classes.Guard;
import com.example.smartpatrol.interfaces.callback;
import com.example.smartpatrol.util.LocationUtils;
import com.example.smartpatrol.util.validateQRcode;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.firestore.GeoPoint;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanIntentResult;
import com.journeyapps.barcodescanner.ScanOptions;

import org.checkerframework.checker.units.qual.C;

import java.util.Calendar;

public class ScanActivity extends AppCompatActivity {
    ImageView IconUser;
    TextView buttonReport;
    Button btn_scan;
    Location location,endLocation;
    ActivityResultLauncher<ScanOptions> barLaucher;
    String httpUrl;
    int count;
    Patrol patrol;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        initializeViews();
        setListeners();
        getLocationOnSwipe();


    }

    private void setListeners() {

        IconUser.setOnClickListener(view ->
                startActivity(new Intent(ScanActivity.this,ProfileActivity.class))
        );

        buttonReport.setOnClickListener(view ->
                startActivity(new Intent(ScanActivity.this, Incident1Activity.class))
        );

        btn_scan.setOnClickListener(v->
                scanCode()
        );
        barLaucher = registerForActivityResult(new ScanContract(), result->
                validateQRcodeData(result)
        );

    }

    private void validateQRcodeData(ScanIntentResult result) {
        if(result.getContents() !=null)
        {

            if (count<FunctionsConstants.FUNCTION_COUNT){

                httpUrl=result.getContents();
                if (validateHttp(httpUrl)){
                    count++;
                    if (count!=FunctionsConstants.FUNCTION_COUNT)
                        Toast.makeText(getApplicationContext(),
                                "Move to next checkpoint",
                                Toast.LENGTH_SHORT).show();
                    else{
                        getEndLocationOnSwipe();

                    }



                }
                else Toast.makeText(this, "The QR code is invalid", Toast.LENGTH_SHORT).show();

            }


        }
    }

    private void getLocationOnSwipe() {
        LocationUtils.getMyDefaultLocation(ScanActivity.this, new callback() {
            @Override
            public void onSuccess(Object o) {

                location=(Location)o;
                patrol.setStartLocation(new LatLng(location.getLatitude(),location.getLongitude()));
                patrol.setCalendar(Calendar.getInstance());
            }

            @Override
            public void onFailure(Object o) {

            }
        });

    }

    private void getEndLocationOnSwipe() {
        LocationUtils.getMyDefaultLocation(ScanActivity.this, new callback() {
            @Override
            public void onSuccess(Object o) {

                endLocation=(Location)o;
                patrol.setEndLocation(new LatLng(endLocation.getLatitude(),endLocation.getLongitude()));

                Guard signedInGuard=new Guard();
                PatrolHelper.uploadPatrol(patrol, signedInGuard.getuID(), new callback() {
                    @Override
                    public void onSuccess(Object o) {
                        Intent intent=new Intent(getApplicationContext(),ProfileActivity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(ScanActivity.this, "Succesfully done patrol", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Object o) {

                    }
                });


            }

            @Override
            public void onFailure(Object o) {

            }
        });

    }

    private void initializeViews() {
        btn_scan = findViewById(R.id.btn_scan);
        IconUser = findViewById(R.id.IconUser);
        buttonReport = findViewById(R.id.buttonReport);
        count=0;
        patrol=new Patrol();
    }

    private void scanCode()
    {

        ScanOptions options = new ScanOptions();
        options.setPrompt("Volume up to flash on");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLaucher.launch(options);
    }



}