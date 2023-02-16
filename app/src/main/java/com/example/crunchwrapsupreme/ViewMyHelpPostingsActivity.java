package com.example.crunchwrapsupreme;

import static com.example.crunchwrapsupreme.MainActivity.currentUserProfile;

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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Pattern;

public class ViewMyHelpPostingsActivity extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private LinearLayout layout;
    private EditText searchValue;

    public static boolean editEngaged = false;
    public static JobPosting editJobPosting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_my_help_postings);
        layout = findViewById(R.id.containerMyHelpPostings);

        searchValue = findViewById(R.id.editTextFindMyPostingSearchValue);

        databaseReference = FirebaseDatabase.getInstance().getReference("Job Postings");

        generateCards();

        Button btnBack = findViewById(R.id.buttonMyHelpPostingsBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMainActivity();
            }
        });

        Button btnNewHelpPosting = findViewById(R.id.buttonNewHelpPosting);

        btnNewHelpPosting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCreateHelpPosting();
            }
        });

        ImageButton searchMyPostings = findViewById(R.id.imageButtonSearchMyPostings);
        searchMyPostings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateCards();
            }
        });
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

                        if (postingInfo.getUserID() == FirebaseAuth.getInstance().getCurrentUser().getUid()) {
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
                                        editJobPosting = postingInfo;
                                        editEngaged = true;
                                        showCreateHelpPosting();
                                    }
                                });
                                layout.addView(view);
                            }
                        }
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

                        if (postingInfo.getUserID().toString().matches(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())) {
                            View view = getLayoutInflater().inflate(R.layout.helpwantedcard, null);

                            TextView orgNameView = view.findViewById(R.id.textViewHelpWantedOrgFromPoster);
                            TextView orgPositionView = view.findViewById(R.id.textViewHelpWantedPositionFromPoster);
                            TextView orgLocationView = view.findViewById(R.id.textViewHelpWantedLocationFromPoster);
                            TextView orgCompensationView = view.findViewById(R.id.textViewHelpWantedCompensationFromPoster);
                            TextView orgCompensationUnitView = view.findViewById(R.id.textViewHelpWantedCompensationUnitFromPoster);

                            Button btnEditPosting = view.findViewById(R.id.buttonHelpWantedMoreInfo);
                            btnEditPosting.setText("Edit");

                            orgNameView.setText(postingInfo.getNameOfCompanyText());
                            orgPositionView.setText(postingInfo.getPositionText());
                            orgLocationView.setText(postingInfo.getLocationText());
                            orgCompensationView.setText(("$" + postingInfo.getCompensationAmount() + " / "));
                            orgCompensationUnitView.setText(postingInfo.getCompensationUnit());

                            btnEditPosting.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    // Got to View Posting Activity loaded with info
                                    editJobPosting = postingInfo;
                                    editEngaged = true;
                                    showCreateHelpPosting();
                                }
                            });
                            layout.addView(view);
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            }));
        }
        editJobPosting = null;
    }

    public void showMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void showCreateHelpPosting() {
        Intent intent = new Intent(this, HelpActivity.class);
        startActivity(intent);
        finish();
    }
}