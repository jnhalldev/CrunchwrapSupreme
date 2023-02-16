package com.example.crunchwrapsupreme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import static com.example.crunchwrapsupreme.MainActivity.currentUserProfile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.*;

public class CreateProfileActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null) {
            finish();
            return;
        }

        Button btnCreateAccount = findViewById(R.id.buttonCreateAccount);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateUserProfile();
            }
        });

        Button btnSwitchToLogin = findViewById(R.id.buttonSignIn);
        btnSwitchToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSignInActivity();
            }
        });
    }

    public void CreateUserProfile() {
        EditText firstNameText = findViewById(R.id.editTextFirstName);
        EditText lastNameText = findViewById(R.id.editTextLastName);
        EditText emailText = findViewById(R.id.editTextEmail);
        EditText confirmEmailText = findViewById(R.id.editTextConfirmEmail);
        EditText phoneText = findViewById(R.id.editTextPhone);
        EditText passwordText = findViewById(R.id.editTextPassword);
        EditText confirmPasswordText = findViewById(R.id.editTextConfirmPassword);

        TextView firstNameIssue = findViewById(R.id.textViewFirstNameIssue);
        TextView lastNameIssue = findViewById(R.id.textViewLastNameIssue);
        TextView emailIssue = findViewById(R.id.textViewEmailIssue);
        TextView confirmEmailIssue = findViewById(R.id.textViewConfirmEmailIssue);
        TextView phoneIssue = findViewById(R.id.textViewPhoneIssue);
        TextView passwordIssue = findViewById(R.id.textViewPasswordIssue);
        TextView confirmPasswordIssue = findViewById(R.id.textViewConfirmPasswordIssue);

        boolean firstNameValid = false;
        boolean lastNameValid = false;
        boolean emailValid = false;
        boolean confirmEmailValid = false;
        boolean phoneValid = false;
        boolean passwordValid = false;
        boolean confirmPasswordValid = false;

        String firstNameString = firstNameText.getText().toString();
        String lastNameString = lastNameText.getText().toString();
        String emailString = emailText.getText().toString();
        String phoneString = phoneText.getText().toString();
        String passwordString = passwordText.getText().toString();

        // checking first name for input validation
        if (firstNameString.matches("")) {
            firstNameIssue.setVisibility(View.VISIBLE);
        }
        else {
            firstNameIssue.setVisibility(View.GONE);
            firstNameValid = true;
        }

        // checking last name for input validation
        if (lastNameString.matches("")) {
            lastNameIssue.setVisibility(View.VISIBLE);
        }
        else {
            lastNameIssue.setVisibility(View.INVISIBLE);
            lastNameValid = true;
        }

        //checking email for input validation
        if (emailString.matches("")) {
            emailIssue.setText("Email is empty");
            emailIssue.setVisibility(View.VISIBLE);
        }
        else if (!isValidEmailAddress(emailString)) {
            emailIssue.setText("Email is invalid");
            emailIssue.setVisibility(View.VISIBLE);
        }
        else {
            emailIssue.setVisibility(View.INVISIBLE);
            emailValid = true;
        }

        //checking email confirmation is the same
        if (!emailString.matches(confirmEmailText.getText().toString())) {
            confirmEmailIssue.setVisibility(View.VISIBLE);
        }
        else {
            confirmEmailIssue.setVisibility(View.INVISIBLE);
            confirmEmailValid = true;
        }

        //checking phone validation
        if (phoneString.matches("")) {
            phoneIssue.setText("Phone number is Empty");
            phoneIssue.setVisibility(View.VISIBLE);
        }
        else if (!isValidPhoneNumber(phoneText.getText().toString())) {
            phoneIssue.setText("Phone number is invalid");
            phoneIssue.setVisibility(View.VISIBLE);
        }
        else {
            phoneIssue.setVisibility(View.INVISIBLE);
            phoneValid = true;
        }

        //checking password validation
        if (!isPasswordValid(passwordString)) {
            passwordIssue.setText("Password must be 12 characters with 1: Upper/Lowercase, Number, and Symbol");
            passwordIssue.setVisibility(View.VISIBLE);
        }
        else {
            passwordIssue.setVisibility(View.INVISIBLE);
            passwordValid = true;
        }

        //checking password confirm validation
        if (!passwordString.matches(confirmPasswordText.getText().toString())) {
            confirmPasswordIssue.setText("Password does not match");
            confirmPasswordIssue.setVisibility(View.VISIBLE);
        }
        else {
            confirmPasswordIssue.setVisibility(View.INVISIBLE);
            confirmPasswordValid = true;
        }

        // create UserProfile
        if (firstNameValid && lastNameValid && emailValid && confirmEmailValid && phoneValid && passwordValid && confirmPasswordValid) {

            if (mAuth.getCurrentUser() != null) {
                mAuth.signOut();
            }
            mAuth.createUserWithEmailAndPassword(emailString, passwordString)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                currentUserProfile = new UserProfile(firstNameString, lastNameString, emailString, phoneString, passwordString);
                                FirebaseDatabase.getInstance().getReference("Profiles")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(currentUserProfile).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                showMainActivity();
                                            }
                                        });
                            } else {
                                Toast.makeText(CreateProfileActivity.this, "An error occurred. Check fields.", Toast.LENGTH_LONG).show();
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

    private void showSignInActivity(){
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
        finish();
    }

    public boolean isValidEmailAddress(String email) {
        String regex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        Pattern emailPattern = Pattern.compile(regex);
        Matcher matcher = emailPattern.matcher(email);

        return matcher.matches();
    }

    public boolean isValidPhoneNumber(String number) {
        String regex = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$";
        Pattern phonePattern = Pattern.compile(regex);
        Matcher matcher = phonePattern.matcher(number);

        return matcher.matches();
    }

    public boolean isPasswordValid(String password) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
        Pattern passwordPattern = Pattern.compile(regex);
        Matcher matcher = passwordPattern.matcher(password);

        return matcher.matches();
    }
}