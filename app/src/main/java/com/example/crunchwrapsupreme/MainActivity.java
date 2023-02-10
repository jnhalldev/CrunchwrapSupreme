package com.example.crunchwrapsupreme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
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
    Button settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdView mAdView = findViewById(R.id.mAdView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //FirebaseAuth.getInstance().signOut();

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser == null) {
            openCreateAccountActivity();
            return;
        }

        helpButton = findViewById(R.id.helpButton);
        workButton = findViewById(R.id.workButton);
        servicesButton = findViewById(R.id.servicesButton);
        messagesButton = findViewById(R.id.messagesButton);
        contactButton = findViewById(R.id.contactButton);
        mapButton = findViewById(R.id.mapButton);
        settingsButton = findViewById(R.id.settingsButton);

        Button buttonProfile = findViewById(R.id.profileButton);
        buttonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProfileActivity();
            }
        });

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewMyHelpPostingsActivity.class);
                startActivity(intent);
            }
        });

        workButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowWorkSearchActivity();
            }
        });
        servicesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Services_Page.class);
                startActivity(intent);
            }
        });
        messagesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Messages_Page.class);
                startActivity(intent);
            }
        });
        contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Contact_Page.class);
                startActivity(intent);
            }
        });
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Map_Page.class);
                startActivity(intent);
            }
        });
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Settings_Page.class);
                startActivity(intent);
                finish();
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
    
    public void ShowWorkSearchActivity() {
        Intent intent = new Intent(this, WorkSearchActivity.class);
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


