package com.example.crunchwrapsupreme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Map;

public class Map_Page extends AppCompatActivity {

    Button homeButton8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_page);

        homeButton8 = findViewById(R.id.homeButton8);

        homeButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Map_Page.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}