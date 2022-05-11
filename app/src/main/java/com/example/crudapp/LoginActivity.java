package com.example.crudapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText uemail, upass;
    private Button loginbtn;
    private TextView registerTV;
    private FirebaseAuth mAuth;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        uemail = (TextInputEditText) findViewById(R.id.LEmail);
        upass = (TextInputEditText) findViewById(R.id.LPass);
        loginbtn = findViewById(R.id.Lbutton);
        registerTV = findViewById(R.id.LRegister);
        mAuth = FirebaseAuth.getInstance();

        registerTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(i);
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = uemail.getText().toString();
                String pass = upass.getText().toString();
                if(TextUtils.isEmpty(email) && TextUtils.isEmpty(pass)) {
                    Toast.makeText(LoginActivity.this, "Please enter your credentials", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                               Toast.makeText(LoginActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                               Intent i = new Intent(LoginActivity.this, ParkingList.class);
                               startActivity(i);
                               finish();
                            }
                            else {
                                Toast.makeText(LoginActivity.this, "Failed to login", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    //@Override
    //protected void onStart() {
    //    super.onStart();
    //    FirebaseUser user = mAuth.getCurrentUser();
   //     if (user!= null) {
    //        Intent i = new Intent(LoginActivity.this, MainActivity.class);
    //        startActivity(i);
    //        this.finish();
     //   }
   // }
}