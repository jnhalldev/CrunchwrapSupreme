package com.example.crunchwrapsupreme;

import static com.example.crunchwrapsupreme.ProfileActivity.currentUserProfile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class WorkSearchActivity extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_search);

        layout = findViewById(R.id.containerHelpWanted);

        databaseReference = FirebaseDatabase.getInstance().getReference("Job Postings");

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
                    orgLocationView.setText(postingInfo.getLocationText()+", ");
                    orgCompensationView.setText((postingInfo.getCompensationAmount() + " / "));
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

        Button ButoonBack = findViewById(R.id.button3);
        ButoonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowMainActivity();

            }
        });
        Button NewPosting = findViewById(R.id.Work_Owner_Posting);
        NewPosting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View veiwing ) {
                    classJobPosting();
        }
        });
    }
    public void ShowMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }




    public void classJobPosting(){
        Intent intent = new Intent(this, HelpActivity.class);
        startActivity(intent);
        finish();
    }

}
