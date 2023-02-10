package com.example.crunchwrapsupreme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.crunchwrapsupreme.databinding.ActivityCreateAdBinding;
import com.example.crunchwrapsupreme.databinding.ActivityShowAdsBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowAdsActivity extends AppCompatActivity implements AdsAdapter.OnItemClickListener {
    private ActivityShowAdsBinding binding;
    private DatabaseReference adsRef;
    private FirebaseAuth mAuth;
    private ArrayList<Ads> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowAdsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();

        adsRef = FirebaseDatabase.getInstance().getReference("ads").child(mAuth.getUid());

        getUserAds();

    }

    private void getUserAds() {
        arrayList.clear();
        adsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot s : snapshot.getChildren()) {
                        Ads ads = s.getValue(Ads.class);
                        arrayList.add(ads);
                        Log.e("image", ads.getImage());
                    }

                    setAdsAdapter();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(ShowAdsActivity.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setAdsAdapter() {
        AdsAdapter adsAdapter = new AdsAdapter(ShowAdsActivity.this, arrayList);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(ShowAdsActivity.this));
        binding.recyclerView.setAdapter(adsAdapter);
        adsAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onDeleteClick(Ads ads, int position) {
        Ads ads1 = arrayList.get(position);

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Delete Ad")
                .setMessage("Are you sure you want to delete ad?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        adsRef.child(ads1.getAdId())
                                .removeValue();
                        Intent intent = new Intent(ShowAdsActivity.this, ShowAdsActivity.class);
                        startActivity(intent);
                        dialogInterface.dismiss();
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what should happen when negative button is clicked
                        dialogInterface.dismiss();
                    }
                })
                .show();


    }
}