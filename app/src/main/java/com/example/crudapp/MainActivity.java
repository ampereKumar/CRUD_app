package com.example.crudapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.ActivityNotFoundException;
import android.os.Bundle;
import android.view.View;
import android.net.Uri;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

//     EditText etSource,etDestination;
//     Button btTrack;
    private TextInputEditText Name, Vnumber, Cnumber, location, inTime, outTime;
   // private TextView location;
    private Button next;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseRefernce, databaseReference2;
    private String user;
    private String parkingid;
    private parkingModel parkingModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Name= findViewById(R.id.Name);
        Cnumber = findViewById(R.id.Cnumber);
        Vnumber = findViewById(R.id.Vnumber);
        inTime = findViewById(R.id.intime);
        outTime = findViewById(R.id.outtime);
        location = findViewById(R.id.location);
        next = (Button)findViewById(R.id.next);
        firebaseDatabase = firebaseDatabase.getInstance();
        databaseRefernce= firebaseDatabase.getReference("User");

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name= Name.getText().toString();
                String contact= Cnumber.getText().toString();
                String vnumber= Vnumber.getText().toString();
                String iTime = inTime.getText().toString();
                String oTime = outTime.getText().toString();
                parkingModel = getIntent().getParcelableExtra("Locations");
                if(parkingModel!=null) {
                    location.setText(parkingModel.getPname());
//                    avail.setText(parkingModel.getAvail());
                    parkingid = parkingModel.getParkingid();
                }
                String parkingl= location.getText().toString();
                databaseReference2 = firebaseDatabase.getReference("Locations").child(parkingid);

                user= name;
                Model Model= new Model(name, contact, vnumber, iTime, oTime, parkingl);

                databaseRefernce.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseRefernce.child(user).setValue(Model);
                        Toast.makeText(MainActivity.this, "Details Saved", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, Parking.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(MainActivity.this, "Error while adding the data", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });





        //  Lout = findViewById(R.id.L_out);

       // Lout.setOnClickListener(new View.OnClickListener() {
       //     @Override
        //    public void onClick(View view) {
          //      Intent i = new Intent(MainActivity.this,LoginActivity.class);
            //    startActivity(i);
          //  }
       // });

        //Assign variable
//        etSource = findViewById(R.id.et_source);
//        etDestination = findViewById(R.id.et_destination);
//        btTrack = findViewById(R.id.bt_track);

//        btTrack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //Get value from edit text
//                String sSource = etSource.getText().toString().trim();
//                String sDestination = etDestination.getText().toString().trim();
//
//                //Check condition
//                if (sSource.equals("") && sDestination.equals("")) {
//                    //When both value blank
//                    Toast.makeText(getApplicationContext(),"Enter both location",Toast.LENGTH_SHORT).show();
//                } else {
//                    //When both value filled
//                    //Display track
//                    DisplayTrack(sSource,sDestination);
//                }
//            }
//        });
    }

//        private void DisplayTrack(String sSource, String sDestination) {
//            //If the device does not have any map installed, then redirect it to play store
//            try {
//                //When google map is installed
//                //Initialise uri
//                Uri uri = Uri.parse("https://www.google.co.in/maps/dir/" + sSource +"/" + sDestination);
//                //Initialise intent with action view
//                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
//                //Set package
//                intent.setPackage("com.google.android.apps.maps");
//                //Set flag
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                //Start activity
//                startActivity(intent);
//            } catch (ActivityNotFoundException e) {
//                //When google map is not installed
//                //Initialise uri
//                Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
//                //Initialise intent with action view
//                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                //Set flag
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                //Start activity
//                startActivity(intent);
//            }
//        }
}