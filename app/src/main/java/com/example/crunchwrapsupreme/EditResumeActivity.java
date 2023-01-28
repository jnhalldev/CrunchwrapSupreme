package com.example.crunchwrapsupreme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EditResumeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_resume);

        Button btnBack = findViewById(R.id.buttonEditResumeBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showResumeActivity();
            }
        });
    }

    private void showResumeActivity() {
        Intent intent = new Intent(this, ResumeActivity.class);
        startActivity(intent);
        finish();
    }
}

