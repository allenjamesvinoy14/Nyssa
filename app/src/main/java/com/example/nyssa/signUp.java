package com.example.nyssa;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

import spencerstudios.com.bungeelib.Bungee;

public class signUp extends AppCompatActivity {
    Button SignUp;
    EditText Email,Pass,CnfPass,Name;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Email = (EditText) findViewById(R.id.email);
        Pass = (EditText) findViewById(R.id.pass);
        Name = (EditText) findViewById(R.id.name);
        CnfPass = (EditText) findViewById(R.id.cnfpass);
        SignUp = (Button) findViewById(R.id.signup);
        SignUp.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        createUser();
                    }
                }
        );


    }
    public void createUser(){

        mAuth = FirebaseAuth.getInstance();
        String EmailText = Email.getText().toString().trim();
        String PassText = Pass.getText().toString().trim();
        String CnfPassText = CnfPass.getText().toString().trim();
        String NameText = Name.getText().toString().trim();


        if (EmailText.isEmpty()) {
            Toast.makeText(getApplicationContext(),"Please enter your Email ID", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(EmailText).matches()) {
            Toast.makeText(getApplicationContext(),"Enter a valid email address", Toast.LENGTH_SHORT).show();
            return;
        }

        if (NameText.isEmpty()) {
            if (PassText.isEmpty()) {
                Toast.makeText(getApplicationContext(),"Please enter your Name", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        if (PassText.isEmpty()) {
            Toast.makeText(getApplicationContext(),"Please enter your Password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (CnfPassText.isEmpty()) {
            Toast.makeText(getApplicationContext(),"Please confirm your password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!PassText.equals(CnfPassText)) {
            Toast.makeText(getApplicationContext(),"Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }
        SignUp.setText("Signing Up...");
        mAuth.createUserWithEmailAndPassword(EmailText, PassText)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        SignUp.setText("Sign up");
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(getApplicationContext(),"Successfully Registered!", Toast.LENGTH_SHORT).show();
                            Intent myintent = new Intent(signUp.this,signIn.class);
                            startActivity(myintent);
                            Bungee.zoom(signUp.this);
                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException){
                                Toast.makeText(getApplicationContext(),"An account already exists with this email ID", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"An unexpected error has occured", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                });



    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Bungee.fade(signUp.this); //fire the slide left animation
    }
}
