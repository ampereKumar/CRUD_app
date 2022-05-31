package com.example.crudapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ParkingList extends AppCompatActivity implements parkinglistadapter.parkingclickinterface{

    private RecyclerView lists;
    private FloatingActionButton addFab;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ArrayList<parkingModel> parkingModelArrayList;
    private RelativeLayout bottomsheetRL;
    private parkinglistadapter parkinglistadapter;
    private FirebaseAuth mauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_list);
       lists = findViewById(R.id.List);
//        addFab= findViewById(R.id.addFab);
        firebaseDatabase= firebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Locations");
        parkingModelArrayList = new ArrayList<>();
        bottomsheetRL = findViewById(R.id.idRlBSheet);
        mauth= FirebaseAuth.getInstance();
        parkinglistadapter = new parkinglistadapter(parkingModelArrayList, this,this);
        lists.setLayoutManager(new LinearLayoutManager(this));
        lists.setAdapter(parkinglistadapter);
//        addFab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this,AddParking.class));
//            }
//        });

        getallparking();
    }

    private void getallparking() {
        parkingModelArrayList.clear();
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                parkingModelArrayList.add(snapshot.getValue(parkingModel.class));
                parkinglistadapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                parkinglistadapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                parkinglistadapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                parkinglistadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onParkingClick(int position) {
        displayBottomSheet(parkingModelArrayList.get(position));
    }
    private void displayBottomSheet(parkingModel parkingModel) {
        final BottomSheetDialog bottomSheetDialog= new BottomSheetDialog(this);
        View layout = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_dialog,bottomsheetRL);
        bottomSheetDialog.setContentView(layout);
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        bottomSheetDialog.show();

        TextView placename = layout.findViewById(R.id.placename);
        TextView availibility = layout.findViewById(R.id.avalibility);
//        ImageView img = layout.findViewById(R.id.idimg);
        TextView contact = layout.findViewById(R.id.bcontact);
        Button bookp = layout.findViewById(R.id.bookpar);
        //Button delete = layout.findViewById(R.id.deletepa);

        placename.setText("Location: "+parkingModel.getPname());
        availibility.setText("Availibility: "+parkingModel.getAvail());
        contact.setText("Contact: "+parkingModel.getContact());

        bookp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ParkingList.this,MainActivity.class);
                i.putExtra("Locations", parkingModel);
                startActivity(i);
            }
        });

//        editp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(ParkingList.this,bookParking.class);
//                i.putExtra("Locations", parkingModel);
//                startActivity(i);
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch(id) {
            case R.id.Logout:
                Toast.makeText(this, "Log out successfull", Toast.LENGTH_SHORT).show();
                mauth.signOut();
                Intent i = new Intent(ParkingList.this,LoginActivity.class);
                startActivity(i);
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}