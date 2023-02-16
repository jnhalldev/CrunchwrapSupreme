package com.example.crunchwrapsupreme;

import static com.example.crunchwrapsupreme.MainActivity.currentUserProfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Messages_Page extends AppCompatActivity {

    Button homeButton6;
    private LinearLayout layout;
    private DatabaseReference databaseReference;
    private List<Message> inboxMessages;
    private List<Message> sentMessages;
    private Spinner spinnerBox;

    public static Message viewMessage;
    //pri

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages_page);

        layout = findViewById(R.id.containerMessages);

        spinnerBox = findViewById(R.id.spinnerMessageBoxSelection);

        inboxMessages = currentUserProfile.getMessagesReceived();
        sentMessages = currentUserProfile.getSentBox();

        generateCards();

        homeButton6 = findViewById(R.id.homeButton6);

        homeButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Messages_Page.this, MainActivity.class);
                startActivity(intent);
            }
        });

        spinnerBox.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                generateCards();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }

    public void generateCards() {
        layout.removeViewsInLayout(0, layout.getChildCount());
        if (spinnerBox.getSelectedItem().toString().matches("Inbox")) {
            if (inboxMessages == null) {
                inboxMessages = new ArrayList<Message>();
                currentUserProfile.setMessagesReceived(inboxMessages);
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                FirebaseDatabase.getInstance().getReference("Profiles")
                        .child(currentUser.getUid()).setValue(currentUserProfile);
            }
            else {
                for (Message message : inboxMessages) {
                    View view = getLayoutInflater().inflate(R.layout.message_card, null);

                    TextView messageSubject = view.findViewById(R.id.textViewMessageSubjectFromUser);
                    TextView messageFromUser = view.findViewById(R.id.textViewMessageFromUser);
                    TextView messageDateUser = view.findViewById(R.id.textViewMessageReceivedDateFromUser);

                    Button buttonReadMessage = view.findViewById(R.id.buttonMessageRead);

                    messageSubject.setText(message.getMessageSubject());
                    messageFromUser.setText(message.getSentUserName());
                    messageDateUser.setText(message.getReceivedDateTime());

                    buttonReadMessage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // if button is clicked open activity to view full message and reply
                        }
                    });

                    layout.addView(view);
                }
            }

        }
        else if (spinnerBox.getSelectedItem().toString().matches("Sent")) {
            if (sentMessages == null) {
                sentMessages = new ArrayList<Message>();
                currentUserProfile.setMessagesReceived(sentMessages);
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                FirebaseDatabase.getInstance().getReference("Profiles")
                        .child(currentUser.getUid()).setValue(currentUserProfile);
            }
            else {
                for (Message message : sentMessages) {
                    View view = getLayoutInflater().inflate(R.layout.message_card, null);

                    TextView messageSubject = view.findViewById(R.id.textViewMessageSubjectFromUser);
                    TextView messageFromUser = view.findViewById(R.id.textViewMessageFromUser);
                    TextView messageDateUser = view.findViewById(R.id.textViewMessageReceivedDateFromUser);

                    Button buttonReadMessage = view.findViewById(R.id.buttonMessageRead);

                    TextView messageTo = view.findViewById(R.id.textViewMessageFromUser);
                    messageTo.setText("To: ");

                    messageSubject.setText(message.getMessageSubject());
                    messageFromUser.setText(message.getSentUserName());
                    messageDateUser.setText(message.getReceivedDateTime());

                    buttonReadMessage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // if button is clicked open activity to view full message and reply
                        }
                    });

                    layout.addView(view);
                }
            }

        }
    }
}