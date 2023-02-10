package com.example.crunchwrapsupreme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.Manifest;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.example.crunchwrapsupreme.databinding.ActivityCreateAdBinding;

import java.util.HashMap;
import java.util.Map;

public class CreateAdActivity extends AppCompatActivity {
    private Uri resultUri;
    private ActivityCreateAdBinding binding;
    private DatabaseReference adsRef;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateAdBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();

        adsRef = FirebaseDatabase.getInstance().getReference("ads").child(mAuth.getUid());

        binding.adImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAndroidVersion();
            }
        });


        binding.createAdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.advertisementEt.getText().toString().isEmpty()) {
                    binding.advertisementEt.setError("Please enter name");
                    return;
                }
                if (binding.descriptionEt.getText().toString().isEmpty()) {
                    binding.descriptionEt.setError("Please enter description");
                    return;
                }

                createAd();

            }
        });

    }

    private void createAd() {

        final String key = adsRef.push().getKey();

        if (resultUri != null) {
            final ProgressDialog loading = ProgressDialog.show(CreateAdActivity.this, "Create Ad...", "Please Wait...", false, false);

            final StorageReference filePath = FirebaseStorage.getInstance().getReference().child("Ads").child(resultUri.getLastPathSegment());

            UploadTask uploadTask = filePath.putFile(resultUri);

            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(CreateAdActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Map<String, Object> ad = new HashMap<String, Object>();
                            ad.put("adId", key);
                            ad.put("name", binding.advertisementEt.getText().toString());
                            ad.put("description", binding.descriptionEt.getText().toString());
                            ad.put("image", uri.toString());

                            if (!binding.urlEt.getText().toString().isEmpty()) {
                                ad.put("url", binding.urlEt.getText().toString());
                            }

                            ad.put("timestamp", ServerValue.TIMESTAMP);

                            adsRef.child(key).setValue(ad).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {


                                    Toast.makeText(CreateAdActivity.this, "Ad created successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(CreateAdActivity.this, Services_Page.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                    finish();

                                }
                            });

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            Toast.makeText(CreateAdActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });


        } else {
            Map<String, Object> ad = new HashMap<String, Object>();
            ad.put("adId", key);
            ad.put("name", binding.advertisementEt.getText().toString());
            ad.put("image", "");

            if (!binding.urlEt.getText().toString().isEmpty()) {
                ad.put("url", binding.urlEt.getText().toString());
            }

            ad.put("timestamp", ServerValue.TIMESTAMP);

            adsRef.child(key).setValue(ad).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {


                    Toast.makeText(CreateAdActivity.this, "Ad created successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CreateAdActivity.this, Services_Page.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();

                }
            });
        }


    }

    public void checkAndroidVersion() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 555);
            } catch (Exception e) {


            }
        } else {
            pickImage();
        }

    }

    public void pickImage() {
        CropImage.startPickImageActivity(this);
    }

    private void croprequest(Uri imageUri) {
        CropImage.activity(imageUri)

                .start(this);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 555 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            pickImage();
        } else {

            checkAndroidVersion();

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        //RESULT FROM SELECTED IMAGE
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri imageUri = CropImage.getPickImageResultUri(CreateAdActivity.this, data);
            croprequest(imageUri);
        }

        //RESULT FROM CROPING ACTIVITY
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                resultUri = result.getUri();
                Glide.with(getApplicationContext())
                        .load(resultUri)
                        .into(binding.adImage);


            }
        }
    }

}