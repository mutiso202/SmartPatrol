package com.example.smartpatrol;

import static android.content.ContentValues.TAG;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.smartpatrol.classes.Guard;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AddDetailsActivity extends AppCompatActivity {
    ImageView profile;
    private final int GALLERY_REQ_CODE = 1000;
    Guard guard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_details);
        initializeData();
        setListeners();

    }

    private void setListeners() {

        profile.setOnClickListener(v -> {
            Intent open_gallery = new Intent(Intent.ACTION_PICK);
            open_gallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(open_gallery, GALLERY_REQ_CODE);
        });
    }

    private void initializeData() {
        profile=findViewById(R.id.profile);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == GALLERY_REQ_CODE) {
                profile.setImageURI(data.getData());
                Uri imageUri = data.getData();
                Bitmap bitmap ;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);

                    handleUpload(bitmap);
                } catch (IOException e) {e.printStackTrace();}
            }
        }
}

    private void handleUpload(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);

        //firebase storage setup and initialization
        StorageReference firebaseStorage= FirebaseStorage.getInstance().getReference()
                .child("ProfilePictures")
                .child(guard.getuID()+".jpeg");

        firebaseStorage.putBytes(byteArrayOutputStream.toByteArray())
                .addOnSuccessListener(taskSnapshot -> Toast.makeText(this, "Image uploaded successfully", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Log.e(TAG, "onFailure: ",e.getCause()));
    }
    }