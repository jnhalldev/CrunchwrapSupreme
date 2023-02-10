package com.example.crunchwrapsupreme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Services_Page extends AppCompatActivity {

    Button homeButton3;
    ImageView imageView13;
    TextView Create_AD_Text;
    TextView manage_AD_Text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_page);

        homeButton3 = findViewById(R.id.homeButton3);
        imageView13 = findViewById(R.id.imageView13);
        Create_AD_Text = findViewById(R.id.Create_AD_Text);
        manage_AD_Text = findViewById(R.id.create_AD_Text);

        homeButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Services_Page.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Create_AD_Text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Services_Page.this, CreateAdActivity.class);
                startActivity(intent);
            }
        });

        manage_AD_Text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Services_Page.this, ShowAdsActivity.class);
                startActivity(intent);
            }
        });

        imageView13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Services_Page.this, CreateAdActivity.class);
                startActivity(intent);
            }
        });

    }
}