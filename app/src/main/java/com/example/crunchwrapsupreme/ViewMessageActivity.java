package com.example.crunchwrapsupreme;

import static com.example.crunchwrapsupreme.MainActivity.currentUserProfile;
import static com.example.crunchwrapsupreme.Messages_Page.viewMessage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewMessageActivity extends AppCompatActivity {

    public static Boolean isGuest = false;
    public static String visitID;

    static UserProfile tempUserProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_message);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Profiles").child(viewMessage.getReceivedUserID());

        databaseReference.addListenerForSingleValueEvent((new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tempUserProfile = snapshot.getValue(UserProfile.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        }));

        TextView messageSubject = findViewById(R.id.textViewMessageViewActivitySubjectFromMessage);
        TextView messageFrom = findViewById(R.id.textViewMessageViewActivityFromFromMessage);
        TextView messageDate = findViewById(R.id.textViewMessageViewActivityReceivedDateFromMessage);
        TextView messageBody = findViewById(R.id.textViewMessageBodyFromUser);

        messageSubject.setText(viewMessage.getMessageSubject());
        messageFrom.setText(viewMessage.getSentUserName());
        messageFrom.setTextColor(Color.rgb(0,0,200));
        messageDate.setText(viewMessage.getReceivedDateTime());
        messageBody.setText(viewMessage.getMessageBody());

        Button btnBack = findViewById(R.id.buttonViewMessageBack);
        Button btnDelete = findViewById(R.id.buttonViewMessageDelete);
        Button btnReply = findViewById(R.id.buttonViewMessageReply);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewMessage = null;
                showMessagesPageActivity();
            }
        });

        messageFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                visitID = viewMessage.getSentUserID();
                Intent intent = new Intent(ViewMessageActivity.this, VisitOtherProfileActivity.class);
                startActivity(intent);

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentUserProfile.removeMessageFromInbox(viewMessage));
                else { currentUserProfile.removeMessageFromSent(viewMessage);}
                viewMessage = null;
                showMessagesPageActivity();
            }
        });
    }

    private void showMessagesPageActivity() {
        Intent intent = new Intent(ViewMessageActivity.this, Messages_Page.class);
        startActivity(intent);
        finish();
    }
}