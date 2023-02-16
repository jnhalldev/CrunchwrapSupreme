package com.example.crunchwrapsupreme;

import static com.example.crunchwrapsupreme.MainActivity.currentUserProfile;
import static com.example.crunchwrapsupreme.ViewMyHelpPostingsActivity.editEngaged;
import static com.example.crunchwrapsupreme.ViewMyHelpPostingsActivity.editJobPosting;

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

    EditText NameOfCompanyText;
    EditText positionText;
    EditText LocationText;
    EditText ShiftText;
    EditText AddressText;
    EditText requirementsText;
    EditText compensationAmount;
    Spinner compensationUnit;

    boolean companyFilled = true;
    boolean positionFilled = true;
    boolean shiftFilled = true;
    boolean locationFilled = true;
    boolean addressFilled = true;
    boolean compensationFilled = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__help);

        NameOfCompanyText = findViewById(R.id.Nameofcompany);
        positionText = findViewById(R.id.Postion);
        LocationText = findViewById(R.id.Location);
        ShiftText = findViewById(R.id.Shift);
        AddressText = findViewById(R.id.Address);
        requirementsText = findViewById(R.id.Requiredexperience);
        compensationAmount = findViewById(R.id.editTextNumberCompensation);
        compensationUnit = findViewById(R.id.spinner);

        if (editEngaged) {
            NameOfCompanyText.setText(editJobPosting.getNameOfCompanyText());
            positionText.setText(editJobPosting.getPositionText());
            LocationText.setText(editJobPosting.getLocationText());
            ShiftText.setText(editJobPosting.getShiftText());
            AddressText.setText(editJobPosting.getAddressText());
            requirementsText.setText(editJobPosting.getDescription());
            compensationAmount.setText(editJobPosting.getCompensationAmount());
            if (editJobPosting.getCompensationUnit().toString().matches(compensationUnit.getItemAtPosition(0).toString())) {compensationUnit.setSelection(0);}
            else if (editJobPosting.getCompensationUnit().toString().matches(compensationUnit.getItemAtPosition(1).toString())) {compensationUnit.setSelection(1);}
            else if (editJobPosting.getCompensationUnit().toString().matches(compensationUnit.getItemAtPosition(2).toString())) {compensationUnit.setSelection(2);}
            else if (editJobPosting.getCompensationUnit().toString().matches(compensationUnit.getItemAtPosition(3).toString())) {compensationUnit.setSelection(3);}
        }

        Button btnSubmit = findViewById(R.id.Complete);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editJobPosting = null;
                if (editEngaged) {editEngaged = false;}
                createJobPosting();
            }
        });

        Button btnBack = findViewById(R.id.JobBack);
        btnBack.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                showMyHelpPostingsActivity();
            }
        });

    }
    public void createJobPosting() {


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
                            editJobPosting = null;
                            if (editEngaged) {editEngaged = false;}
                            showMyHelpPostingsActivity();
                        }
                    });
        }
        else {
            Toast.makeText(HelpActivity.this, "Check fields", Toast.LENGTH_LONG).show();
        }
    };

    private void showMyHelpPostingsActivity() {
        if (editEngaged) {editEngaged = false;}
        Intent intent = new Intent(this, ViewMyHelpPostingsActivity.class);
        startActivity(intent);
        finish();
    }

}