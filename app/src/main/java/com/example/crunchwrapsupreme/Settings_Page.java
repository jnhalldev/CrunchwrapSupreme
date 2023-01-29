package com.example.crunchwrapsupreme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.slider.Slider;

import org.w3c.dom.Text;


public class Settings_Page extends AppCompatActivity {


    TextView mapView;
    TextView contactView;
    TextView messagesView;
    TextView profileView;
    TextView servicesView;
    TextView settingsView;
    TextView workView;

    Button homeButton4;
    Switch darkMode;
    SeekBar fontSlider;

    public void setActivityBackgroundColor(int color) {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(color);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);

        homeButton4 = findViewById(R.id.homeButton4);
        darkMode = findViewById(R.id.DarkModeSwitch);
        Boolean switchState = darkMode.isChecked();

        homeButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings_Page.this, MainActivity.class);
                startActivity(intent);
            }
        });

        darkMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(darkMode.isChecked()) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });
    }
}