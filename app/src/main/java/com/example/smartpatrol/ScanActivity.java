package com.example.smartpatrol;

import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.EditText;

import com.google.common.util.concurrent.ListenableFuture;

public class ScanActivity extends AppCompatActivity {
    private EditText qrCodeText;
           private PreviewView cameraPreview;
           private ListenableFuture<ProcessCameraProvider>cameraProviderListenableFuture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        qrCodeText = findViewById(R.id.qrCodeText);
        cameraPreview = findViewById(R.id.cameraPreview);


        //check camera for permissions
        if (ContextCompat.checkSelfPermission(ScanActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            init();
        }
        else {
            ActivityCompat.requestPermissions(ScanActivity.this,new String[]{Manifest.permission.CAMERA},101);
        }
    }

    private void init() {

    }
}