package com.example.nyssa;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import spencerstudios.com.bungeelib.Bungee;

public class signIn extends AppCompatActivity {
    TextView SignUp;
    Button SignIn;
    EditText Email,Pass;
    private  FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        SignUp = (TextView) findViewById(R.id.signup);
        Email = (EditText) findViewById(R.id.email);
        Pass = (EditText) findViewById(R.id.pass);
        SignIn = (Button) findViewById(R.id.login);

        SignIn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        signUserIn();
                    }
                }
        );
        SignUp.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        openSignUp();
                    }
                }
        );
    }

    public void signUserIn() {
        mAuth = FirebaseAuth.getInstance();
        String EmailText = Email.getText().toString().trim();
        String PassText = Pass.getText().toString().trim();
        if (EmailText.isEmpty()) {

            Toast.makeText(getApplicationContext(), "Please enter your Email ID", Toast.LENGTH_SHORT).show();
            return;
        }
        if (PassText.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter your password", Toast.LENGTH_SHORT).show();
            return;
        }

        SignIn.setText("Logging in...");
        mAuth.signInWithEmailAndPassword(EmailText, PassText).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                SignIn.setText("Login");
                if (task.isSuccessful()) {
                    //Sign In
                    Intent mainIntent = new Intent(signIn.this, clickImg.class);
                    startActivity(mainIntent);
                    Bungee.inAndOut(signIn.this);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Please check your credentials and try again", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void openSignUp(){
        Intent newIntent = new Intent(signIn.this,signUp.class);
        startActivity(newIntent);
        Bungee.fade(signIn.this);
    }
}
