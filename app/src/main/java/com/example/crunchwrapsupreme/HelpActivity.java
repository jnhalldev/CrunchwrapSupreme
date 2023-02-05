package com.example.crunchwrapsupreme;

import static com.example.crunchwrapsupreme.WorkSearchActivity.JobPostingsList;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class HelpActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__help);

        Button Submit = findViewById(R.id.Complete);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                JobPostingsList.add(classJobPosting());
                ShowMainActivity();
            }
        });

        Button ButoonBack = findViewById(R.id.JobBack);
        ButoonBack.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                ShowMainActivity();
            }
        });

    }
    public JobPosting classJobPosting() {
        EditText NameOfCompanyText = findViewById(R.id.Nameofcompany);
        EditText PostionText = findViewById(R.id.Postion);
        EditText LocationText = findViewById(R.id.Location);
        EditText ShiftText = findViewById(R.id.Shift);
        EditText AddressText = findViewById(R.id.Address);
        EditText ReqirmentsText = findViewById(R.id.Requiredexperience);

        JobPosting jobPosting = new JobPosting(NameOfCompanyText.getText().toString(), PostionText.getText().toString(), LocationText.getText().toString(),ShiftText.getText().toString(), AddressText.getText().toString(), ReqirmentsText.getText().toString());
        Toast.makeText(this, "Job posting created successfully.", Toast.LENGTH_LONG).show();
        return jobPosting;
    };

    private void ShowMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}