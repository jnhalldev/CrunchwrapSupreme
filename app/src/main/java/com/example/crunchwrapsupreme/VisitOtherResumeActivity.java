package com.example.crunchwrapsupreme;

import static com.example.crunchwrapsupreme.ViewMessageActivity.tempUserProfile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class VisitOtherResumeActivity extends AppCompatActivity {
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_other_resume);

        layout = findViewById(R.id.containerVisitOtherWorkHistory);
        generateCards();

        Button btnBack = findViewById(R.id.buttonVisitOtherResumeBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void generateCards() {
        layout.removeViewsInLayout(0,layout.getChildCount());
        Typeface typeface = ResourcesCompat.getFont(this,R.font.vertigoflf);

        TextView textViewWorkExperience = new TextView(this);
        textViewWorkExperience.setText("Work Experience:");
        textViewWorkExperience.setTextSize(25);
        textViewWorkExperience.setTypeface(typeface);
        layout.addView(textViewWorkExperience);

        LinearLayout.LayoutParams layoutParamsExperience = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        layoutParamsExperience.topMargin = -95;
        layoutParamsExperience.leftMargin = 285;

        List<WorkExperience> currentWorkExperienceList = tempUserProfile.getResume().getExperienceList();
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

            btnDeleteWorkExperience.setEnabled(false);
            btnDeleteWorkExperience.setVisibility(View.INVISIBLE);

            orgNameView.setText(workExperience.getOrganizationName());
            orgURLView.setText(workExperience.getOrganizationURL());
            orgCityView.setText(workExperience.getCity()+", ");
            orgStateView.setText((workExperience.getState()));
            orgPositionView.setText(workExperience.getPositionTitle());
            orgStartDateView.setText(workExperience.getBeginDate());
            orgEndDateView.setText(workExperience.getEndDate());

            layout.addView(view);
        }



        LinearLayout.LayoutParams layoutParamsEducation = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );


        TextView textViewEducation = new TextView(this);
        textViewEducation.setText("Education:");
        textViewEducation.setTextSize(25);
        textViewEducation.setTypeface(typeface);
        layout.addView(textViewEducation);

        layoutParamsEducation.topMargin = -95;
        layoutParamsEducation.leftMargin = 175;

        List<Education> currentEducationList = tempUserProfile.getResume().getEducationList();
        for (Education education : currentEducationList) {
            View view = getLayoutInflater().inflate(R.layout.educationcards, null);

            TextView instNameView = view.findViewById(R.id.textViewInstitutionFromUser);
            TextView instEducationLevelView = view.findViewById(R.id.textViewEducationLevelEarnedFromUser);
            TextView instGradYearView = view.findViewById(R.id.textViewEducationYearGraduatedFromUser);
            TextView instCityView = view.findViewById(R.id.textViewEducationCityFromUser);
            TextView instStateView = view.findViewById(R.id.textViewEducationStateFromUser);

            Button btnDeleteEducation = view.findViewById(R.id.buttonDeleteEducation);

            btnDeleteEducation.setEnabled(false);
            btnDeleteEducation.setVisibility(View.INVISIBLE);

            instNameView.setText(education.getInstitution());
            instEducationLevelView.setText(education.getEarned());
            instGradYearView.setText((education.getGradYear()));
            instStateView.setText(education.getState());
            instCityView.setText(education.getCity()+", ");
            layout.addView(view);
        }

        TextView textViewSkills = new TextView(this);
        textViewSkills.setText("Skills:");
        textViewSkills.setTextSize(25);
        textViewSkills.setTypeface(typeface);
        layout.addView(textViewSkills);

        LinearLayout.LayoutParams layoutParamsSkill = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        layoutParamsSkill.topMargin = -95;
        layoutParamsSkill.leftMargin = 100;

        List<Skill> currentSkillList = tempUserProfile.getResume().getSkillList();
        for (Skill skill : currentSkillList) {
            View view = getLayoutInflater().inflate(R.layout.skillcards, null);

            TextView skillView = view.findViewById(R.id.textViewSkillFromUser);

            Button btnDeleteSkill = view.findViewById(R.id.buttonDeleteSkill);

            btnDeleteSkill.setEnabled(false);
            btnDeleteSkill.setVisibility(View.INVISIBLE);

            skillView.setText(skill.getSkill());

            layout.addView(view);
        }

        TextView textViewReferences = new TextView(this);
        textViewReferences.setText("References:");
        textViewReferences.setTextSize(25);
        textViewReferences.setTypeface(typeface);
        layout.addView(textViewReferences);

        LinearLayout.LayoutParams layoutParamsReference = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        layoutParamsReference.topMargin = -95;
        layoutParamsReference.leftMargin = 200;

        List<Reference> currentReferenceList = tempUserProfile.getResume().getReferenceList();
        for (Reference reference : currentReferenceList) {
            View view = getLayoutInflater().inflate(R.layout.addreferencecards, null);

            TextView referenceNameInput = view.findViewById(R.id.textViewReferenceNameFromUser);
            TextView referenceRelationshipInput = view.findViewById(R.id.textViewReferenceRelationshipFromUser);
            TextView referencePhoneInput = view.findViewById(R.id.textViewReferencePhoneUserInput);

            Button btnDeleteSkill = view.findViewById(R.id.buttonDeleteReference);

            btnDeleteSkill.setEnabled(false);
            btnDeleteSkill.setVisibility(View.INVISIBLE);

            referenceNameInput.setText(reference.getName());
            referenceRelationshipInput.setText(reference.getRelationship());
            referencePhoneInput.setText(reference.getPhone());

            layout.addView(view);
        }

    }

    private boolean checkIfUsersProfile() {
        boolean isUser = true;
        //
        // Once able to find other profiles, will implement check of current user profile against profile being displayed
        //
        return isUser;
    }

    private void reloadActivity() {
        Intent intent = new Intent(this, VisitOtherResumeActivity.class);
        startActivity(intent);
        finish();
    }
}