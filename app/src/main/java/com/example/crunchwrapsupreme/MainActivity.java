package com.example.crunchwrapsupreme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
}