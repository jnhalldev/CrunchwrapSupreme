package com.example.crunchwrapsupreme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import static com.example.crunchwrapsupreme.MainActivity.currentUserProfile;
import static com.example.crunchwrapsupreme.WorkSearchActivity.viewJobPosting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class ViewPostingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_posting);

        Button btnBack = findViewById(R.id.buttonViewPostingBack);

        Button btnSendResume = findViewById(R.id.buttonViewPostingSendResume);

        TextView textViewCompanyName = findViewById(R.id.textViewViewPostingCompanyName);
        TextView textViewCompanyAddress = findViewById(R.id.textViewViewPostingCompanyAddress);
        TextView textViewCompanyLocation = findViewById(R.id.textViewViewPostingCompanyLocation);
        TextView textViewCompensation = findViewById(R.id.textViewViewPostingCompensation);
        TextView textViewCompanyShift = findViewById(R.id.textViewViewPostingShift);
        TextView textViewCompanyDescription = findViewById(R.id.textViewViewPostingDescription);

        textViewCompanyName.setText(viewJobPosting.getNameOfCompanyText());
        textViewCompanyAddress.setText(viewJobPosting.getAddressText());
        textViewCompanyLocation.setText(viewJobPosting.getLocationText());
        textViewCompensation.setText("$"+viewJobPosting.getCompensationAmount() + " / " + viewJobPosting.getCompensationUnit());
        textViewCompanyShift.setText(viewJobPosting.getShiftText());
        textViewCompanyDescription.setText(viewJobPosting.getDescription());

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewJobPosting = null;
                showWorkSearchActivity();
            }
        });

        btnSendResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Profiles").child(viewJobPosting.getUserID());

                databaseReference.addListenerForSingleValueEvent((new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        UserProfile tempProfile = snapshot.getValue(UserProfile.class);
                        String firstName = currentUserProfile.getFirstName();
                        String lastName = currentUserProfile.getLastName();

                        Message tempMessage = new Message(viewJobPosting.userID,
                                firstName + " " + lastName,
                                FirebaseAuth.getInstance().getCurrentUser().getUid().toString(),
                                viewJobPosting.getNameOfCompanyText(),
                                firstName + " " + lastName + " applied to your posting!",
                                firstName + " " + lastName + " has sent you their resume to check out for this job posting.");

                        if (tempProfile.getMessagesReceived() == null) {
                            tempProfile.setMessagesReceived(new ArrayList<Message>());
                        }
                        tempProfile.addMessageToInbox(tempMessage);

                        if (currentUserProfile.getSentBox() == null) {
                            currentUserProfile.setSentBox(new ArrayList<Message>());
                        }
                        currentUserProfile.addMessageToSent(tempMessage);
                        FirebaseDatabase.getInstance().getReference("Profiles")
                                .child(viewJobPosting.getUserID()).setValue(tempProfile);


                        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                        FirebaseDatabase.getInstance().getReference("Profiles")
                                .child(currentUser.getUid()).setValue(currentUserProfile);

                        Toast.makeText(ViewPostingActivity.this, "Your resume has been sent!", Toast.LENGTH_SHORT).show();
                        showWorkSearchActivity();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                }));
            }
        });
    }

    private void showWorkSearchActivity() {
        Intent intent = new Intent(this, WorkSearchActivity.class);
        startActivity(intent);
        finish();
    }
}