package com.example.crunchwrapsupreme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.window.SplashScreenView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button ButoonBack = findViewById(R.id.ButtonWorkSearch);
        ButoonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowWorkSearchActivity();

            }
        });

    }

    public void ShowWorkSearchActivity() {
        Intent intent = new Intent(this, WorkSearchActivity.class);
        startActivity(intent);
        finish();
    }

}