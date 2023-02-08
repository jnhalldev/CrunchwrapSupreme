package com.example.crunchwrapsupreme;

import static com.example.crunchwrapsupreme.ProfileActivity.currentUserProfile;

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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;


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

        boolean companyFilled = true;
        boolean positionFilled = true;
        boolean shiftFilled = true;
        boolean locationFilled = true;
        boolean addressFilled = true;
        boolean compensationFilled = true;

        if (NameOfCompanyText.getText().toString().matches("")) {companyFilled = false;}
        else if (positionText.getText().toString().matches("")) {positionFilled = false;}
        else if (LocationText.getText().toString().matches("")) {locationFilled = false;}
        else if (ShiftText.getText().toString().matches("")) {shiftFilled = false;}
        else if (AddressText.getText().toString().matches("")) {addressFilled = false;}
        else if (compensationAmount.getText().toString().matches("")) {compensationFilled = false;}


        if (companyFilled && positionFilled && shiftFilled && locationFilled && addressFilled && compensationFilled) {
            String postID = UUID.randomUUID().toString();
            JobPosting jobPosting = new JobPosting(postID, FirebaseAuth.getInstance().getCurrentUser().getUid().toString(), NameOfCompanyText.getText().toString(),
                    positionText.getText().toString(), LocationText.getText().toString(),ShiftText.getText().toString(), AddressText.getText().toString(),
                    requirementsText.getText().toString(), compensationAmount.getText().toString(), compensationUnit.getSelectedItem().toString());
            FirebaseDatabase.getInstance().getReference("Job Postings")
                    .child(postID)
                    .setValue(jobPosting).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(HelpActivity.this, "Job posting created successfully.", Toast.LENGTH_LONG).show();
                            showMainActivity();
                        }
                    });
            currentUserProfile.addHelpPostingToList(jobPosting);

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