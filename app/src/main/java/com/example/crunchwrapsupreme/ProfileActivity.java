package com.example.crunchwrapsupreme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;

public class ProfileActivity extends AppCompatActivity {

    private ImageButton userImage;

    private TextView userName;
    private TextView userEmail;
    private TextView userPhone;
    private TextView userBio;

    private Uri imagePath;

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private StorageReference storageRef;

    public static UserProfile currentUserProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        userImage = (ImageButton)findViewById(R.id.imageButtonUserPicture);
        userName = findViewById(R.id.textViewProfileUserName);
        userEmail = findViewById(R.id.textViewProfileUserEmail);
        userPhone = findViewById(R.id.textViewProfileUserPhone);
        userBio = findViewById(R.id.textViewProfileBio);

        userImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent photoIntent = new Intent(Intent.ACTION_PICK);
                photoIntent.setType("image/*");
                startActivityForResult(photoIntent,1);
            }
        });

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        Button btnBack = findViewById(R.id.buttonProfileBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMainActivity();
            }
        });

        Button btnLogout = findViewById(R.id.buttonProfileLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUserOut();
                showMainActivity();
            }
        });

        Button btnResume = findViewById(R.id.buttonProfileViewResume);
        btnResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showResumeActivity();
            }
        });

        Button btnEditBio = findViewById(R.id.buttonProfileEditBio);
        btnEditBio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEditBioActivity();
            }
        });


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("Profiles").child(currentUser.getUid());
        reference.addValueEventListener((new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                currentUserProfile = snapshot.getValue(UserProfile.class);
                    userName.setText(currentUserProfile.getFirstName() + " " + currentUserProfile.getLastName());
                    userPhone.setText(currentUserProfile.getPhone());
                    userEmail.setText(currentUserProfile.getEmail());
                    userBio.setText(currentUserProfile.getBio());
                    if (!currentUserProfile.getProfilePicture().toString().matches("")) {
                        try {
                            Picasso.get().load(currentUserProfile.getProfilePicture().toString()).into(userImage);
                        }catch (Exception e) {

                        }
                    }
                }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        }));
    }

    private void showMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void signUserOut() {
        FirebaseAuth.getInstance().signOut();
        finish();
    }

    private void showResumeActivity() {
        Intent intent = new Intent(this, ResumeActivity.class);
        startActivity(intent);
        finish();
    }

    private void showEditBioActivity() {
        Intent intent = new Intent(this, EditBioActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void  onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            imagePath = data.getData();
            getImageInImageView();
        }
    }

    private void getImageInImageView() {
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imagePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        userImage.setImageBitmap(bitmap);
        uploadImage();
    }

    private void uploadImage() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading...");
        progressDialog.show();
        String fileName = currentUser.getUid().toString();

        storageRef = FirebaseStorage.getInstance().getReference("profileImages/"+fileName);

        storageRef.putFile(imagePath)
                .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if (task.isSuccessful()) {
                        task.getResult().getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                if (task.isSuccessful()) {
                                    updateProfilePicture(task.getResult().toString());
                                }
                            }
                        });
                       Toast.makeText(ProfileActivity.this, "Profile picture updated.", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(ProfileActivity.this, "Unable to upload picture.", Toast.LENGTH_SHORT).show();
                    }
                progressDialog.dismiss();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                        double progress = 100 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount();
                        progressDialog.setMessage("Upload "+(int) progress + "%");
                    }
                });
    }

    private void updateProfilePicture(String url) {
        currentUserProfile.setProfilePicture(url);
        FirebaseDatabase.getInstance().getReference("Profiles")
                .child(currentUser.getUid()).setValue(currentUserProfile);
    }


    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap bmp = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                bmp = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return bmp;
        }
        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}