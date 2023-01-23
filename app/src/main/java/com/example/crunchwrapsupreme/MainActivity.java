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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        Button buttonSignOut = findViewById(R.id.buttonHomePageSignOut);
        buttonSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUserOut();
            }
        });
    }

    private void openCreateAccountActivity() {
        Intent intent = new Intent(this, CreateProfileActivity.class);
        startActivity(intent);
        finish();
    }

    private void signUserOut() {
        FirebaseAuth.getInstance().signOut();
        finish();
        startActivity(getIntent());
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

