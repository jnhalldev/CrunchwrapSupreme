package com.example.crunchwrapsupreme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.regex.*;


public class MainActivity extends AppCompatActivity {

    Button helpButton;
    Button workButton;
    Button servicesButton;
    Button messagesButton;
    Button mapButton;
    Button contactButton;
    Button profileButton;
    Button settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //FirebaseAuth.getInstance().signOut();

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser == null) {
            openCreateAccountActivity();
            return;
        }

        TextView userSignedIn = findViewById(R.id.textViewUserSignedIn);
        if (checkIfSignedIn()) {
            userSignedIn.setText("User Is signed in.");
        }
        else {
            userSignedIn.setText("User Is signed out.");
        }

        Button buttonProfile = findViewById(R.id.buttonHomePageEditProfile);
        buttonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProfileActivity();

        helpButton = findViewById(R.id.helpButton);
        workButton = findViewById(R.id.workButton);
        servicesButton = findViewById(R.id.servicesButton);
        messagesButton = findViewById(R.id.messagesButton);
        contactButton = findViewById(R.id.contactButton);
        mapButton = findViewById(R.id.mapButton);
        profileButton = findViewById(R.id.profileButton);
        settingsButton = findViewById(R.id.settingsButton);

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Help_Page.class);
                startActivity(intent);
            }
        });

        workButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Work_Page.class);
                startActivity(intent);

            }
        });
    }

    private void openCreateAccountActivity() {
        Intent intent = new Intent(this, CreateProfileActivity.class);
        startActivity(intent);
        finish();
    }

    private void showProfileActivity() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean checkIfSignedIn() {
        boolean signedIn = false;
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            signedIn = true;
        }
        return signedIn;
    }
}

