package com.example.crunchwrapsupreme;

import static com.example.crunchwrapsupreme.MainActivity.currentUserProfile;
import static com.example.crunchwrapsupreme.Messages_Page.viewMessage;
import static com.example.crunchwrapsupreme.ViewMessageActivity.tempUserProfile;
import static com.example.crunchwrapsupreme.WorkSearchActivity.viewJobPosting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.ArrayList;

public class VisitOtherProfileActivity extends AppCompatActivity {

    private ImageButton userImage;

    private TextView userName;
    private TextView userEmail;
    private TextView userPhone;
    private TextView userBio;

    private Uri imagePath;
    private StorageReference storageRef;

    private AlertDialog dialogSendMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_other_profile);

        userImage = findViewById(R.id.imageButtonVisitOtherUserPicture);
        userName = findViewById(R.id.textViewVisitOtherProfileUserName);
        userEmail = findViewById(R.id.textViewVisitOtherProfileUserEmail);
        userPhone = findViewById(R.id.textViewVisitOtherProfileUserPhone);
        userBio = findViewById(R.id.textViewVisitOtherProfileBio);


        buildSendMessageDialog();

        Button btnBack = findViewById(R.id.buttonVisitOtherProfileBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLastActivity();
            }
        });

        Button btnSendMessage = findViewById(R.id.buttonVisitOtherProfileMessage);
        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogSendMessage.show();
            }
        });

        Button btnResume = findViewById(R.id.buttonVisitOtherProfileViewResume);
        btnResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // call activity to view user's resume
                showResumeActivity();
            }
        });

        userName.setText(tempUserProfile.getFirstName() + " " + tempUserProfile.getLastName());
        userPhone.setText(tempUserProfile.getPhone());
        userEmail.setText(tempUserProfile.getEmail());
        userBio.setText(tempUserProfile.getBio());
        loadImageWithResize();
    }

    private void buildSendMessageDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_send_message, null);

        EditText messageSubject = view.findViewById(R.id.editTextMessageSubject);
        EditText messageBody = view.findViewById(R.id.editTextMessageBody);

        builder.setView(view);
        builder.setTitle("Compose Message")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Message tempMessage = new Message(FirebaseAuth.getInstance().getCurrentUser().getUid(), currentUserProfile.getFirstName() + " " + currentUserProfile.getLastName(),
                                viewMessage.getReceivedUserID(), tempUserProfile.getFirstName() + " " + tempUserProfile.getLastName(), messageSubject.getText().toString(), messageBody.getText().toString());
                        if (tempUserProfile.getMessagesReceived() == null) {tempUserProfile.setMessagesReceived(new ArrayList<Message>());}
                        tempUserProfile.addMessageToInbox(tempMessage);
                        if (currentUserProfile.getSentBox() == null) {currentUserProfile.setSentBox(new ArrayList<Message>());}
                        currentUserProfile.addMessageToSent(tempMessage);
                        FirebaseDatabase.getInstance().getReference("Profiles")
                                .child(viewMessage.getReceivedUserID()).setValue(tempUserProfile);


                        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                        FirebaseDatabase.getInstance().getReference("Profiles")
                                .child(currentUser.getUid()).setValue(currentUserProfile);

                        Toast.makeText(VisitOtherProfileActivity.this, "Your message has been sent!", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        dialogSendMessage = builder.create();
    }

    private void loadImageWithResize() {

        userImage.getViewTreeObserver()
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        userImage.getViewTreeObserver()
                                .removeOnGlobalLayoutListener(this);

                        Picasso
                                .get()
                                .load(tempUserProfile.getProfilePicture().toString())
                                .resize(userImage.getWidth(), userImage.getHeight()) // resizes the image to these dimensions (in pixel). does not respect aspect ratio
                                .into(userImage);
                    }
                });
    }

    private void showLastActivity() {
        finish();
    }

    private void showResumeActivity() {
        Intent intent = new Intent(this, VisitOtherResumeActivity.class);
        startActivity(intent);
    }

    @Override
    protected void  onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            imagePath = data.getData();
            getImageInImageView();
        }
    }

    private void getImageInImageView() {
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imagePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        userImage.setImageBitmap(bitmap);
    }
}