package com.example.crunchwrapsupreme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ResumeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume);

        Button btnBack = findViewById(R.id.buttonResumeBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProfileActivity();
            }
        });

        Button btnEditResume = findViewById(R.id.buttonEditResume);
        btnEditResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditResumeActivity();
            }
        });
    }

    private void showProfileActivity() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
        finish();
    }

    private void showEditResumeActivity() {
        Intent intent = new Intent(this, EditResumeActivity.class);
        startActivity(intent);
        finish();
    }
}