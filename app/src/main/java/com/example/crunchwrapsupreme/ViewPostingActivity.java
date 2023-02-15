package com.example.crunchwrapsupreme;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.crunchwrapsupreme.WorkSearchActivity.viewJobPosting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewPostingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_posting);

        Button btnBack = findViewById(R.id.buttonViewPostingBack);

        TextView textViewCompanyName = findViewById(R.id.textViewViewPostingCompanyName);
        TextView textViewCompanyAddress = findViewById(R.id.textViewViewPostingCompanyAddress);
        TextView textViewCompanyLocation = findViewById(R.id.textViewViewPostingCompanyLocation);
        TextView textViewCompensation = findViewById(R.id.textViewViewPostingCompensation);
        TextView textViewCompanyShift = findViewById(R.id.textViewViewPostingShift);
        TextView textViewCompanyDescription = findViewById(R.id.textViewViewPostingDescription);

        textViewCompanyName.setText(viewJobPosting.getNameOfCompanyText());
        textViewCompanyAddress.setText(viewJobPosting.getAddressText());
        textViewCompanyLocation.setText(viewJobPosting.getLocationText());
        textViewCompensation.setText(viewJobPosting.getCompensationAmount() + " / " + viewJobPosting.getCompensationUnit());
        textViewCompanyShift.setText(viewJobPosting.getShiftText());
        textViewCompanyDescription.setText(viewJobPosting.getDescription());

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewJobPosting = null;
                showWorkSearchActivity();
            }
        });

    }

    private void showWorkSearchActivity() {
        Intent intent = new Intent(this, WorkSearchActivity.class);
        startActivity(intent);
        finish();
    }
}