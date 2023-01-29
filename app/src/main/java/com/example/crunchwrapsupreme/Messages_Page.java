package com.example.crunchwrapsupreme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Messages_Page extends AppCompatActivity {

    Button homeButton6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages_page);

        homeButton6 = findViewById(R.id.homeButton6);

        homeButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Messages_Page.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}