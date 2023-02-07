package com.example.crunchwrapsupreme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class HelpActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__help);

        Button btnSubmit = findViewById(R.id.Complete);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                createJobPosting();
                showMainActivity();
            }
        });

        Button btnBack = findViewById(R.id.JobBack);
        btnBack.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                showMainActivity();
            }
        });

    }
    public void createJobPosting() {
        EditText NameOfCompanyText = findViewById(R.id.Nameofcompany);
        EditText positionText = findViewById(R.id.Postion);
        EditText LocationText = findViewById(R.id.Location);
        EditText ShiftText = findViewById(R.id.Shift);
        EditText AddressText = findViewById(R.id.Address);
        EditText requirementsText = findViewById(R.id.Requiredexperience);
        EditText compensationAmount = findViewById(R.id.editTextNumberCompensation);
        Spinner compensationUnit = findViewById(R.id.spinner);

        boolean companyFilled = false;
        boolean positionFilled = false;
        boolean shiftFilled = false;
        boolean locationFilled = false;
        boolean addressFilled = false;
        boolean compensationFilled = false;

        if (NameOfCompanyText.getText().toString() != "") {companyFilled = true;}
        if (positionText.getText().toString() != "") {positionFilled = true;}
        if (LocationText.getText().toString() != "") {locationFilled = true;}
        if (ShiftText.getText().toString() != "") {shiftFilled = true;}
        if (AddressText.getText().toString() != "") {addressFilled = true;}
        if (compensationAmount.getText().toString() != "") {compensationFilled = true;}


        if (companyFilled && positionFilled && shiftFilled && locationFilled && addressFilled && compensationFilled) {
            JobPosting jobPosting = new JobPosting(NameOfCompanyText.getText().toString(), positionText.getText().toString(),
                    LocationText.getText().toString(),ShiftText.getText().toString(), AddressText.getText().toString(),
                    requirementsText.getText().toString());
            SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            FirebaseDatabase.getInstance().getReference("Job Postings")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid() + dateTimeFormatter.format(date))
                    .setValue(jobPosting).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(HelpActivity.this, "Job posting created successfully.", Toast.LENGTH_LONG).show();
                            showMainActivity();
                        }
                    });
            //JobPostingsList.add(jobPosting);
        }
        else {
            Toast.makeText(HelpActivity.this, "Check fields", Toast.LENGTH_LONG).show();
        }
    };

    private void showMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}