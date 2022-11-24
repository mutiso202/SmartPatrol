package com.example.smartpatrol;

import static android.content.ContentValues.TAG;
import static com.example.smartpatrol.classes.Constants.INCIDENT_PARCELABLE;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.smartpatrol.Helpers.IncidentHelper;
import com.example.smartpatrol.Models.Incident;
import com.example.smartpatrol.interfaces.callback;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;

public class Incident2Activity extends AppCompatActivity {
    Button send_incident2;
    EditText editText;
    CardView cameraView, galleryView;
    ImageView back_incident2;
    Incident incident;
    ImageView cameraIcon, galleryIcon;
    private final int GALLERY_REQ_CODE = 1000;
    private final int IMAGE_REQ_CODE=100;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incident2);
        intializeViews();
        checkCameraPermissions();
        setListeners();
        


        /*galleryIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent open_gallery=new Intent();
                open_gallery.setType("image");
                open_gallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(galleryIcon,"Select Picture",));
            }
        });*/


    }

    private void setListeners() {

        back_incident2.setOnClickListener(v ->
                startActivity( new Intent(Incident2Activity.this, Incident1Activity.class))
        );


        galleryView.setOnClickListener(v -> {
            Intent open_gallery = new Intent(Intent.ACTION_PICK);
            open_gallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(open_gallery, GALLERY_REQ_CODE);
        });

        cameraView.setOnClickListener(v -> {
            Intent open_camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(open_camera, 1540);
        });


        send_incident2.setOnClickListener(v -> {
            incident.setDescription(editText.getText().toString());

            IncidentHelper.uploadIncident(incident, new callback() {
                        @Override
                        public void onSuccess(Object o) {
                            startActivity(new Intent(Incident2Activity.this, ScanActivity.class));
                        }

                        @Override
                        public void onFailure(Object o) {

                        }
                    });

        });

    }

    private void checkCameraPermissions() {

        if (ContextCompat.checkSelfPermission(Incident2Activity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Incident2Activity.this,
                    new String[]{
                            Manifest.permission.CAMERA}, 100);
        }

    }

    private void handleUpload(Bitmap bitmap) {

        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);

        //firebase storage setup and initialization
        StorageReference firebaseStorage= FirebaseStorage.getInstance().getReference()
                .child("profileImages")
                .child(incident.getIncidentId()+".jpeg");

        firebaseStorage.putBytes(byteArrayOutputStream.toByteArray())
                .addOnSuccessListener(taskSnapshot -> Toast.makeText(this, "Image uploaded successfully", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Log.e(TAG, "onFailure: ",e.getCause()));


    }

    private void intializeViews() {
        cameraView = findViewById(R.id.cameraView);
        galleryView= findViewById(R.id.galleryView);
        back_incident2 = findViewById(R.id.back_incident2);
        cameraIcon = findViewById(R.id.cameraIcon);
        galleryIcon = findViewById(R.id.galleryIcon);
        editText = findViewById(R.id.editText);
        send_incident2 = findViewById(R.id.send_incident2);
        incident=getIntent().getParcelableExtra(INCIDENT_PARCELABLE);
    }



    ;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @NonNull Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != RESULT_CANCELED){
            if (requestCode == 1540) {
                Bitmap captureImage = (Bitmap) data.getExtras().get("data");

                cameraIcon.setImageBitmap(captureImage);



                //handleUpload(captureImage);
            }
        }

        if (resultCode == RESULT_OK) {
            if (requestCode == GALLERY_REQ_CODE) {
                galleryIcon.setImageURI(data.getData());
                Uri imageUri = data.getData();
                Bitmap bitmap ;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);

                    handleUpload(bitmap);
                } catch (IOException e) {e.printStackTrace();}
            }
        }
    }
}
