package com.example.crudapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Parking extends AppCompatActivity {
    //private TextInputEditText pname, avail;
    private TextView etSource;
    private Button btTrack;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String parkingid;
    private parkingModel parkingModel;
    private String pname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking);
        firebaseDatabase = FirebaseDatabase.getInstance();
        etSource = findViewById(R.id.et_source);
       // etDestination = findViewById(R.id.et_destination);
        btTrack = findViewById(R.id.bt_track);
        //pname = findViewById(R.id.pname);
        parkingModel = getIntent().getParcelableExtra("Locations");
        if(parkingModel!=null) {
            pname = parkingModel.getPname();
            //avail.setText(parkingModel.getAvail());
            parkingid = parkingModel.getParkingid();
        }

        databaseReference = firebaseDatabase.getReference("Locations").child(pname);

        btTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String desc = pname.toString();
                String sSource = etSource.getText().toString().trim();
                DisplayTrack(sSource,desc);
            }
        });
    }

    private void DisplayTrack(String sSource, String sDestination) {
            //If the device does not have any map installed, then redirect it to play store
            try {
                //When google map is installed
                //Initialise uri
                Uri uri = Uri.parse("https://www.google.co.in/maps/dir/" + sSource +"/" + sDestination);
                //Initialise intent with action view
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                //Set package
                intent.setPackage("com.google.android.apps.maps");
                //Set flag
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //Start activity
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                //When google map is not installed
                //Initialise uri
                Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
                //Initialise intent with action view
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                //Set flag
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //Start activity
                startActivity(intent);
            }
        }
}