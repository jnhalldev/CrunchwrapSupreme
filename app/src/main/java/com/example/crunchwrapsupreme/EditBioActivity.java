package com.example.crunchwrapsupreme;

import static com.example.crunchwrapsupreme.ProfileActivity.currentUserProfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;


public class EditBioActivity extends AppCompatActivity {

    //private String bio;

    private EditText bioText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_bio);

        bioText = findViewById(R.id.editTextBioEditBio);

        bioText.setText(currentUserProfile.getBio());

        Button btnBack = findViewById(R.id.buttonEditBioBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProfileActivity();
            }
        });

        Button btnSave = findViewById(R.id.buttonBioSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBioToUser(bioText.getText().toString());
            }
        });
    }

    private void showProfileActivity() {
        Intent intent = new Intent(this,ProfileActivity.class);
        startActivity(intent);
        finish();
    }

    public void saveBioToUser(String string) {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        currentUserProfile.setBio(string);
        FirebaseDatabase.getInstance().getReference("Profiles")
                .child(currentUser.getUid()).setValue(currentUserProfile);
        Toast.makeText(EditBioActivity.this, "Bio saved successfully.", Toast.LENGTH_SHORT).show();
    }
}