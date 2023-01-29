package com.example.crunchwrapsupreme;

import static com.example.crunchwrapsupreme.ProfileActivity.currentUserProfile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ResumeActivity extends AppCompatActivity {

    private boolean editEngaged = false;

    private Button btnAddToResume;
    private AlertDialog dialog;
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume);

        btnAddToResume = findViewById(R.id.buttonAddToResume);
        layout = findViewById(R.id.container);

        buildDialog();
        generateWorkExperienceCards();

        btnAddToResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        if (editEngaged) {

        }

        Button btnBack = findViewById(R.id.buttonResumeBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProfileActivity();
            }
        });
    }

    private void showProfileActivity() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
        finish();
    }

    private void toggleEdit() {
        if (editEngaged) {
            editEngaged = false;
        }
        else {
            editEngaged = true;
        }
    }

    private void buildDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialogaddworkexperience, null);

        EditText organizationNameInput = view.findViewById(R.id.editTextUserInputOrganization);
        EditText organizationURLInput = view.findViewById(R.id.editTextUserInputOrganizationURL);
        EditText organizationCityInput = view.findViewById(R.id.editTextUserInputOrganizationCity);
        EditText organizationStateInput = view.findViewById(R.id.editTextUserInputOrganizationState);
        EditText organizationPositionInput = view.findViewById(R.id.editTextUserInputOrganizationTitle);
        EditText organizationBeginDateInput = view.findViewById(R.id.editTextUserInputOrganizationBeginDate);
        EditText organizationEndDateInput = view.findViewById(R.id.editTextUserInputOrganizationEndDate);

        builder.setView(view);
        builder.setTitle("Add Work Experience")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        WorkExperience tempWorkExperience = new WorkExperience(organizationNameInput.getText().toString(), organizationURLInput.getText().toString(), organizationCityInput.getText().toString(), organizationStateInput.getText().toString(),
                                organizationPositionInput.getText().toString(), organizationBeginDateInput.getText().toString(), organizationEndDateInput.getText().toString());
                        currentUserProfile.addToResume(tempWorkExperience);
                        addCard(organizationNameInput.getText().toString(), organizationURLInput.getText().toString(), organizationCityInput.getText().toString(), organizationStateInput.getText().toString(),
                                organizationPositionInput.getText().toString(), organizationBeginDateInput.getText().toString(), organizationEndDateInput.getText().toString(), tempWorkExperience);
                        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                        FirebaseDatabase.getInstance().getReference("Profiles")
                                .child(currentUser.getUid()).setValue(currentUserProfile);
                        Toast.makeText(ResumeActivity.this, "Work history added.", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        dialog = builder.create();
    }

    private void generateWorkExperienceCards() {
        //layout.removeViewsInLayout(0,layout.getChildCount());
        List<WorkExperience> currentWorkExperienceList = currentUserProfile.getResume().getExperienceList();
        for (WorkExperience workExperience : currentWorkExperienceList) {
            View view = getLayoutInflater().inflate(R.layout.workcards, null);

            TextView orgNameView = view.findViewById(R.id.textViewOrgNameFromUser);
            TextView orgURLView = view.findViewById(R.id.textViewOrgURLFromUser);
            TextView orgCityView = view.findViewById(R.id.textViewOrgLocationCityFromUser);
            TextView orgStateView = view.findViewById(R.id.textViewOrgLocationStateFromUser);
            TextView orgPositionView = view.findViewById(R.id.textViewOrgPositionFromUser);
            TextView orgStartDateView = view.findViewById(R.id.textViewOrgLocationBeginDateFromUser);
            TextView orgEndDateView = view.findViewById(R.id.textViewOrgLocationEndDateFromUser);
            Button btnDelete = view.findViewById(R.id.buttonDeleteWorkHistory);

            orgNameView.setText(workExperience.getOrganizationName());
            orgURLView.setText(workExperience.getOrganizationURL());
            orgCityView.setText(workExperience.getCity()+", ");
            orgStateView.setText((workExperience.getState()));
            orgPositionView.setText(workExperience.getPositionTitle());
            orgStartDateView.setText(workExperience.getBeginDate());
            orgEndDateView.setText(workExperience.getEndDate());

            if (editEngaged) {
                btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        currentUserProfile.removeWorkExperience(workExperience);
                        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                        FirebaseDatabase.getInstance().getReference("Profiles")
                                .child(currentUser.getUid()).setValue(currentUserProfile);
                        Toast.makeText(ResumeActivity.this, "Work history removed.", Toast.LENGTH_SHORT).show();
                        layout.removeView(view);
                    }
                });
            }
            else {
                btnDelete.setEnabled(false);
                btnDelete.setVisibility(View.INVISIBLE);
            }

            layout.addView(view);
        }
    }

    private void addCard(String orgName, String orgURL, String city, String state, String position, String startDate, String endDate,WorkExperience tempWorkExperience) {
        View view = getLayoutInflater().inflate(R.layout.workcards, null);

        TextView orgNameView = view.findViewById(R.id.textViewOrgNameFromUser);
        TextView orgURLView = view.findViewById(R.id.textViewOrgURLFromUser);
        TextView orgCityView = view.findViewById(R.id.textViewOrgLocationCityFromUser);
        TextView orgStateView = view.findViewById(R.id.textViewOrgLocationStateFromUser);
        TextView orgPositionView = view.findViewById(R.id.textViewOrgPositionFromUser);
        TextView orgStartDateView = view.findViewById(R.id.textViewOrgLocationBeginDateFromUser);
        TextView orgEndDateView = view.findViewById(R.id.textViewOrgLocationEndDateFromUser);
        Button btnDelete = view.findViewById(R.id.buttonDeleteWorkHistory);

        orgNameView.setText(orgName);
        orgURLView.setText(orgURL);
        orgCityView.setText(city+", ");
        orgStateView.setText(state);
        orgPositionView.setText(position);
        orgStartDateView.setText(startDate);
        orgEndDateView.setText(endDate);

        if (editEngaged) {
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentUserProfile.removeWorkExperience(tempWorkExperience);
                    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                    FirebaseDatabase.getInstance().getReference("Profiles")
                            .child(currentUser.getUid()).setValue(currentUserProfile);
                    Toast.makeText(ResumeActivity.this, "Work history removed.", Toast.LENGTH_SHORT).show();
                    layout.removeView(view);
                }
            });
        }
        else {
            btnDelete.setEnabled(false);
            btnDelete.setVisibility(View.INVISIBLE);
        }

        layout.addView(view);
    }
}