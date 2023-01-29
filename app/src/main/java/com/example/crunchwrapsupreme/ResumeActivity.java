package com.example.crunchwrapsupreme;

import static com.example.crunchwrapsupreme.ProfileActivity.currentUserProfile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
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

    private Button btnAddToExperience;
    private Button btnAddToEducation;
    private AlertDialog dialogWorkHistory;
    private AlertDialog dialogEducation;
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume);

        btnAddToExperience = findViewById(R.id.buttonAddToExperience);
        btnAddToEducation = findViewById(R.id.buttonAddToEducation);
        layout = findViewById(R.id.containerWorkHistory);

        buildWorkHistoryDialog();
        buildEducationDialog();
        toggleEdit();
        generateCards();

        btnAddToExperience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogWorkHistory.show();
            }
        });

        btnAddToEducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogEducation.show();
            }
        });

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

    private void buildWorkHistoryDialog() {
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
                        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                        FirebaseDatabase.getInstance().getReference("Profiles")
                                .child(currentUser.getUid()).setValue(currentUserProfile);
                        generateCards();
                        Toast.makeText(ResumeActivity.this, "Work history added.", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        dialogWorkHistory = builder.create();
    }

    private void buildEducationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialogaddeducation, null);

        EditText institutionNameInput = view.findViewById(R.id.editTextUserInputInstitution);
        EditText institutionLevelOfEducationInput = view.findViewById(R.id.editTextUserInputLevelOfEducation);
        EditText institutionGradYearInput = view.findViewById(R.id.editTextUserInputGraduationYear);
        EditText institutionCityInput = view.findViewById(R.id.editTextUserInputInstitutionCity);
        EditText institutionStateInput = view.findViewById(R.id.editTextUserInputInstitutionState);

        builder.setView(view);
        builder.setTitle("Add Education")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Education tempEducation = new Education(institutionNameInput.getText().toString(), institutionLevelOfEducationInput.getText().toString(), institutionGradYearInput.getText().toString(),
                                institutionCityInput.getText().toString(), institutionStateInput.getText().toString());
                        currentUserProfile.addToEducation(tempEducation);
                        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                        FirebaseDatabase.getInstance().getReference("Profiles")
                                .child(currentUser.getUid()).setValue(currentUserProfile);
                        generateCards();
                        Toast.makeText(ResumeActivity.this, "Education added.", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        dialogEducation = builder.create();
    }

    private void generateCards() {
        layout.removeViewsInLayout(0,layout.getChildCount());
        Typeface typeface = ResourcesCompat.getFont(this,R.font.vertigoflf);

        TextView textViewWorkExperience = new TextView(this);
        textViewWorkExperience.setText("Work Experience:");
        textViewWorkExperience.setTextSize(25);
        textViewWorkExperience.setTypeface(typeface);
        layout.addView(textViewWorkExperience);
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
            Button btnDeleteWorkExperience = view.findViewById(R.id.buttonDeleteWorkHistory);

            orgNameView.setText(workExperience.getOrganizationName());
            orgURLView.setText(workExperience.getOrganizationURL());
            orgCityView.setText(workExperience.getCity()+", ");
            orgStateView.setText((workExperience.getState()));
            orgPositionView.setText(workExperience.getPositionTitle());
            orgStartDateView.setText(workExperience.getBeginDate());
            orgEndDateView.setText(workExperience.getEndDate());

            if (editEngaged) {
                btnDeleteWorkExperience.setOnClickListener(new View.OnClickListener() {
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
                btnDeleteWorkExperience.setEnabled(false);
                btnDeleteWorkExperience.setVisibility(View.INVISIBLE);
            }

            layout.addView(view);
        }

        TextView textViewEducation = new TextView(this);
        textViewEducation.setText("Education:");
        textViewEducation.setTextSize(25);
        textViewEducation.setTypeface(typeface);
        layout.addView(textViewEducation);

        List<Education> currentEducationList = currentUserProfile.getResume().getEducationList();
        for (Education education : currentEducationList) {
            View view = getLayoutInflater().inflate(R.layout.educationcards, null);

            TextView instNameView = view.findViewById(R.id.textViewInstitutionFromUser);
            TextView instEducationLevelView = view.findViewById(R.id.textViewEducationLevelEarnedFromUser);
            TextView instGradYearView = view.findViewById(R.id.textViewEducationYearGraduatedFromUser);
            TextView instCityView = view.findViewById(R.id.textViewEducationCityFromUser);
            TextView instStateView = view.findViewById(R.id.textViewEducationStateFromUser);

            Button btnDeleteEducation = view.findViewById(R.id.buttonDeleteEducation);

            instNameView.setText(education.getInstitution());
            instEducationLevelView.setText(education.getEarned());
            instGradYearView.setText((education.getCity()));
            instStateView.setText(education.getState());
            instCityView.setText(education.getCity()+", ");

            if (editEngaged) {
                btnDeleteEducation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        currentUserProfile.removeEducation(education);
                        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                        FirebaseDatabase.getInstance().getReference("Profiles")
                                .child(currentUser.getUid()).setValue(currentUserProfile);
                        Toast.makeText(ResumeActivity.this, "Work history removed.", Toast.LENGTH_SHORT).show();
                        layout.removeView(view);
                    }
                });
            }
            else {
                btnDeleteEducation.setEnabled(false);
                btnDeleteEducation.setVisibility(View.INVISIBLE);
            }
            layout.addView(view);
        }



        TextView textViewSkills = new TextView(this);
        textViewSkills.setText("Skills:");
        textViewSkills.setTextSize(25);
        textViewSkills.setTypeface(typeface);
        layout.addView(textViewSkills);

        // add in skill cards


        TextView textViewReferences = new TextView(this);
        textViewReferences.setText("References:");
        textViewReferences.setTextSize(25);
        textViewReferences.setTypeface(typeface);
        layout.addView(textViewReferences);
    }

    private boolean checkIfUsersProfile() {
        boolean isUser = true;
        //
        // Once able to find other profiles, will implement check of current user profile against profile being displayed
        //
        return isUser;
    }
}