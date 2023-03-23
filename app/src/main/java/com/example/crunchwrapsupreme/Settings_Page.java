package com.example.crunchwrapsupreme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.PermissionRequest;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;

import static com.example.crunchwrapsupreme.Messages_Page.inboxMessages;

import com.google.android.material.slider.Slider;

import org.w3c.dom.Text;

import java.security.Permission;


public class Settings_Page extends AppCompatActivity {

    public static final String CHANNEL_ID = "MY Notifications";

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
    Button Tester;

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
        Tester = findViewById(R.id.Tester);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("NotificationButton", "Important Notify", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }



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
                if (darkMode.isChecked()) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });
        Tester.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.d("NotificationButton", "Notification test");
                if (ActivityCompat.checkSelfPermission(Settings_Page.this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Settings_Page.this , new String[]{Manifest.permission.ACCESS_NOTIFICATION_POLICY}, PackageManager.PERMISSION_GRANTED);
                    return;
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    String name = "My Notifications";
                    String discription = "Will show Messages in inbox to Notifications";
                    int important = NotificationManager.IMPORTANCE_DEFAULT;
                    NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name , important);
                    channel.setDescription(discription);
                    NotificationManager manager = getSystemService(NotificationManager.class);
                    manager.createNotificationChannel(channel);
                }

                NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext(), CHANNEL_ID);
                builder.setContentTitle("CrunchWrapSupreme");
                builder.setContentText("Notification test");
                builder.setSmallIcon(R.drawable.ic_launcher_background);
                builder.setAutoCancel(true);


                if (inboxMessages == null) {
                    builder.setContentText("currentUserProfile.setMessagesReceived(inboxMessages)");
                    builder.setSmallIcon(R.drawable.ic_launcher_foreground);
                    builder.setAutoCancel(true);
                } else {
                    builder.setContentText("Notifications Messages are empty");
                    builder.setSmallIcon(R.drawable.ic_launcher_foreground);
                    builder.setAutoCancel(true);
                }
                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getBaseContext());
                managerCompat.notify(1, builder.build());
            }
        });
        }
}