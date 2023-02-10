package com.example.crunchwrapsupreme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Pattern;

public class WorkSearchActivity extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private LinearLayout layout;
    private EditText searchValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_search);

        layout = findViewById(R.id.containerHelpWanted);

        searchValue = findViewById(R.id.editTextSearchValue);

        databaseReference = FirebaseDatabase.getInstance().getReference("Job Postings");

        generateCards();

        ImageButton btnSearch = findViewById(R.id.imageButtonSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateCards();
            }
        });

        Button btnBack = findViewById(R.id.button3);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowMainActivity();
            }
        });
    }
    public void ShowMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void generateCards() {
        if (!searchValue.getText().toString().matches("")) {
            layout.removeViewsInLayout(0,layout.getChildCount());

            databaseReference.addValueEventListener((new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    layout.removeViewsInLayout(0,layout.getChildCount());
                    for (DataSnapshot posting : dataSnapshot.getChildren()) {
                        JobPosting postingInfo = posting.getValue((JobPosting.class));
                        View view = getLayoutInflater().inflate(R.layout.helpwantedcard, null);

                        boolean companyNameContains = Pattern.compile(Pattern.quote(searchValue.getText().toString()), Pattern.CASE_INSENSITIVE).matcher(postingInfo.getNameOfCompanyText()).find();
                        boolean jobTitleContains = Pattern.compile(Pattern.quote(searchValue.getText().toString()), Pattern.CASE_INSENSITIVE).matcher(postingInfo.getPositionText()).find();
                        boolean jobDescriptionContains = Pattern.compile(Pattern.quote(searchValue.getText().toString()), Pattern.CASE_INSENSITIVE).matcher(postingInfo.getDescription()).find();

                        if (companyNameContains || jobTitleContains || jobDescriptionContains) {
                            TextView orgNameView = view.findViewById(R.id.textViewHelpWantedOrgFromPoster);
                            TextView orgPositionView = view.findViewById(R.id.textViewHelpWantedPositionFromPoster);
                            TextView orgLocationView = view.findViewById(R.id.textViewHelpWantedLocationFromPoster);
                            TextView orgCompensationView = view.findViewById(R.id.textViewHelpWantedCompensationFromPoster);
                            TextView orgCompensationUnitView = view.findViewById(R.id.textViewHelpWantedCompensationUnitFromPoster);

                            Button btnMoreInfo = view.findViewById(R.id.buttonHelpWantedMoreInfo);

                            orgNameView.setText(postingInfo.getNameOfCompanyText());
                            orgPositionView.setText(postingInfo.getPositionText());
                            orgLocationView.setText(postingInfo.getLocationText());
                            orgCompensationView.setText(("$" + postingInfo.getCompensationAmount() + " / "));
                            orgCompensationUnitView.setText(postingInfo.getCompensationUnit());

                            btnMoreInfo.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    // Got to View Posting Activity loaded with info
                                }
                            });
                            layout.addView(view);
                        }
                        //}
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            }));
        }

        else {
            databaseReference.addValueEventListener((new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    layout.removeViewsInLayout(0,layout.getChildCount());
                    for (DataSnapshot posting : dataSnapshot.getChildren()) {
                        JobPosting postingInfo = posting.getValue((JobPosting.class));
                        View view = getLayoutInflater().inflate(R.layout.helpwantedcard, null);

                        TextView orgNameView = view.findViewById(R.id.textViewHelpWantedOrgFromPoster);
                        TextView orgPositionView = view.findViewById(R.id.textViewHelpWantedPositionFromPoster);
                        TextView orgLocationView = view.findViewById(R.id.textViewHelpWantedLocationFromPoster);
                        TextView orgCompensationView = view.findViewById(R.id.textViewHelpWantedCompensationFromPoster);
                        TextView orgCompensationUnitView = view.findViewById(R.id.textViewHelpWantedCompensationUnitFromPoster);

                        Button btnMoreInfo = view.findViewById(R.id.buttonHelpWantedMoreInfo);

                        orgNameView.setText(postingInfo.getNameOfCompanyText());
                        orgPositionView.setText(postingInfo.getPositionText());
                        orgLocationView.setText(postingInfo.getLocationText());
                        orgCompensationView.setText(("$" + postingInfo.getCompensationAmount() + " / "));
                        orgCompensationUnitView.setText(postingInfo.getCompensationUnit());

                        btnMoreInfo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // Got to View Posting Activity loaded with info
                            }
                        });
                        layout.addView(view);
                    }
                }



                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            }));
        }

    }


    public void classJobPosting(){
        Intent intent = new Intent(this, HelpActivity.class);
        startActivity(intent);
        finish();
    }

}
