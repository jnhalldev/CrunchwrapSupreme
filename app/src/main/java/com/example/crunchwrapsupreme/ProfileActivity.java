package com.example.crunchwrapsupreme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    ImageButton userImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        userImage = (ImageButton)findViewById(R.id.imageButtonUserPicture);

        userImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(ProfileActivity.this, "Test", Toast.LENGTH_SHORT).show();
            }
        });

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        //FirebaseUser user = mAuth.getCurrentUser();

        Button btnBack = findViewById(R.id.buttonProfileBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMainActivity();
            }
        });

        Button btnLogout = findViewById(R.id.buttonProfileLogout);
        btnLogout.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUserOut();
                showMainActivity();
            }
        }));


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        /*DatabaseReference reference = database.getReference("Profiles").child(user.getUid());
        reference.addValueEventListener((new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserProfile user = snapshot.getValue(UserProfile.class);
                if (user != null) {
                    tvName.setText(user.getFirstName() + " " + user.getLastName());
                    tvPhone.setText(user.getPhone());
                    tvEmailAddress.setText(user.getEmail());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        }));*/
    }

    private void showMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void signUserOut() {
        FirebaseAuth.getInstance().signOut();
        finish();
    }
}