package com.example.crunchwrapsupreme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Services_Page extends AppCompatActivity {

    Button homeButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_page);

        homeButton3 = findViewById(R.id.homeButton3);

        homeButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Services_Page.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}