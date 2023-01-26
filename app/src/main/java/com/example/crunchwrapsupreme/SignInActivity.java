package com.example.crunchwrapsupreme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null) {
            finish();
            return;
        }

        mAuth = FirebaseAuth.getInstance();

        Button btnSignInAuth = findViewById(R.id.buttonSignInAuth);
        btnSignInAuth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUserIn();
            }
        });

        Button btnSignInCreateProfile = findViewById(R.id.buttonSignInCreateProfile);
        btnSignInCreateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCreateProfileActivity();
            }
        });
    }

    public void signUserIn() {
        EditText userEmail = findViewById(R.id.editTextSignInEmailAddress);
        EditText userPassword = findViewById(R.id.editTextTextPassword);

        TextView signInEmailIssue = findViewById(R.id.textSignInEmailIssue);
        TextView signInPasswordIssue = findViewById(R.id.textSignInPasswordIssue);

        String signInEmailString = userEmail.getText().toString();
        String signInPasswordString = userPassword.getText().toString();

        boolean signInEmail = false;
        boolean signInPassword = false;

        if (signInEmailString.matches("")) {
            signInEmailIssue.setText("Email is empty");
            signInEmailIssue.setVisibility(View.VISIBLE);
        }
        else {
            signInEmailIssue.setVisibility(View.INVISIBLE);
            signInEmail = true;
        }

        if (signInPasswordString.matches("")) {
            signInPasswordIssue.setText("Password is empty");
            signInPasswordIssue.setVisibility(View.VISIBLE);
        }
        else {
            signInPasswordIssue.setVisibility(View.INVISIBLE);
            signInPassword = true;
        }

        if (signInEmail && signInPassword) {
            mAuth.signInWithEmailAndPassword(signInEmailString, signInPasswordString)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                showMainActivity();
                            }
                            else {
                                Toast.makeText(SignInActivity.this,"Incorrect username and/or password.",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }

    private void showMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void showCreateProfileActivity(){
        Intent intent = new Intent(this, CreateProfileActivity.class);
        startActivity(intent);
        finish();
    }
}