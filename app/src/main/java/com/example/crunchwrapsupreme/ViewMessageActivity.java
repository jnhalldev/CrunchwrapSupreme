package com.example.crunchwrapsupreme;

import static com.example.crunchwrapsupreme.MainActivity.currentUserProfile;
import static com.example.crunchwrapsupreme.Messages_Page.viewMessage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewMessageActivity extends AppCompatActivity {

    public static Boolean isGuest = false;
    public static String visitID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_message);

        TextView messageSubject = findViewById(R.id.textViewMessageViewActivitySubjectFromMessage);
        TextView messageFrom = findViewById(R.id.textViewMessageViewActivityFromFromMessage);
        TextView messageDate = findViewById(R.id.textViewMessageViewActivityReceivedDateFromMessage);
        TextView messageBody = findViewById(R.id.textViewMessageBodyFromUser);

        messageSubject.setText(viewMessage.getMessageSubject());
        messageFrom.setText(viewMessage.getSentUserName());
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
                isGuest = true;
                visitID = viewMessage.getSentUserID();
                Intent intent = new Intent(ViewMessageActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish();

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