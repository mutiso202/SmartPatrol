package com.example.smartpatrol;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.smartpatrol.classes.Incident;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class Incident2Activity extends AppCompatActivity {
    Button send_incident2;
    EditText editText;
    CardView cameraView;
    ImageView back_incident2;
    ImageView cameraIcon, galleryIcon;
    private final int GALLERY_REQ_CODE = 1000;
    private final int IMAGE_REQ_CODE=100;
    /*
    TextView textViewGallery ,textViewTakePhoto;*/
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incident2);


        cameraView = findViewById(R.id.cameraView);
        back_incident2 = findViewById(R.id.back_incident2);
        cameraIcon = findViewById(R.id.cameraIcon);
        galleryIcon = findViewById(R.id.galleryIcon);
        /*galleryIcon=findViewById(R.id.galleryView);

        textViewGallery=findViewById(R.id.textViewGallery);
        textViewTakePhoto=findViewById(R.id.textViewTakePhoto);*/
        editText = findViewById(R.id.editText);
        send_incident2 = findViewById(R.id.send_incident2);
        db = FirebaseFirestore.getInstance();
        Intent intent = getIntent();

        if (ContextCompat.checkSelfPermission(Incident2Activity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Incident2Activity.this,
                    new String[]{
                            Manifest.permission.CAMERA}, 100);
        }


        back_incident2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Incident2Activity.this, Incident1Activity.class);
                startActivity(intent);
            }
        });


        galleryIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent open_gallery = new Intent(Intent.ACTION_PICK);
                open_gallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(open_gallery, GALLERY_REQ_CODE);
            }
        });

        cameraIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent open_camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(open_camera, 100);
            }
        });

        /*galleryIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent open_gallery=new Intent();
                open_gallery.setType("image");
                open_gallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(galleryIcon,"Select Picture",));
            }
        });*/

        send_incident2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Incident2Activity.this, patrolActivity.class);
                startActivity(intent);

                String description = editText.getText().toString().trim();
                Date date = new Date(System.currentTimeMillis());
                String radioButton = intent.getStringExtra("RadioGroup");


                HashMap hashMap = new HashMap();
                hashMap.put("Title", radioButton);
                hashMap.put("Description", description);
                hashMap.put("Date", date);


                db.collection("Incidents").document().set(hashMap)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(Incident2Activity.this, "Incident has been sent", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Incident2Activity.this, "Incidents has not been sent.", Toast.LENGTH_SHORT).show();
                            }
                        });


            }


        });

    }

    ;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            Bitmap captureImage = (Bitmap) data.getExtras().get("data");
            cameraIcon.setImageBitmap(captureImage);
        }
        if (resultCode == RESULT_OK) {
            if (requestCode == GALLERY_REQ_CODE) {
                galleryIcon.setImageURI(data.getData());
            }
        }
    }
}
