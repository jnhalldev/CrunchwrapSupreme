package com.example.crunchwrapsupreme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class WorkSearchActivity extends AppCompatActivity {

    public static List<JobPosting> JobPostingsList = new ArrayList<JobPosting>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_search);

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

       List<JobPosting> temp = JobPostingsList;
    }
    public void ShowMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }




    public void classJobPosting(){
        Intent intent = new Intent(this, classJobPosting.class);
        startActivity(intent);
        finish();
    }

}
