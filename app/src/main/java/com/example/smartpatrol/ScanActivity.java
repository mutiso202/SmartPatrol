package com.example.smartpatrol;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.common.util.concurrent.ListenableFuture;

public class ScanActivity extends AppCompatActivity {

    private ImageView scanArrowBack;
           private PreviewView cameraPreview;
           private ListenableFuture<ProcessCameraProvider>cameraProviderListenableFuture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        cameraPreview = findViewById(R.id.cameraPreview);
        scanArrowBack=findViewById(R.id.scanArrowBack);

        //check camera for permissions
        if (ContextCompat.checkSelfPermission(ScanActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            init();
        }
        else {
            ActivityCompat.requestPermissions(ScanActivity.this,new String[]{Manifest.permission.CAMERA},101);
        }
        scanArrowBack.setOnClickListener(v -> {
            Intent intent=new Intent(ScanActivity.this, patrolActivity.class);
            startActivity(intent);
        });
    }

    private void init() {

    }
}