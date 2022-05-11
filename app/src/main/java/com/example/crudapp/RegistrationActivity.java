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

public class RegistrationActivity extends AppCompatActivity {

    private TextInputEditText email, pwd, cpwd;
    private TextView LoginTV;
    private Button registerBtn;
    private FirebaseAuth mAuth;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration2);
        email = findViewById(R.id.email);
        pwd = findViewById(R.id.password);
        cpwd = findViewById(R.id.crnpassword);
        LoginTV = findViewById(R.id.TVLogin);
        registerBtn = (Button)findViewById(R.id.register);
        mAuth = FirebaseAuth.getInstance();
        LoginTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idemail = email.getText().toString();
                String password = pwd.getText().toString();
                String cpassword = cpwd.getText().toString();
                if(!password.equals(cpassword)) {
                    Toast.makeText(RegistrationActivity.this, "Please check both the password", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(idemail) && TextUtils.isEmpty(cpassword)) {
                    Toast.makeText(RegistrationActivity.this, "Please add your credentials", Toast.LENGTH_SHORT).show();
                }
                else {
                    mAuth.createUserWithEmailAndPassword(idemail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(RegistrationActivity.this, "User Registered", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(RegistrationActivity.this, LoginActivity.class);
                                startActivity(i);
                                finish();
                            }
                            else {
                                Toast.makeText(RegistrationActivity.this, "Fail to register", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}