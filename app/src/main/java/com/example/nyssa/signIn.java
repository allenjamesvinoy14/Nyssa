package com.example.nyssa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import spencerstudios.com.bungeelib.Bungee;

public class signIn extends AppCompatActivity {
    TextView SignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        SignUp = (TextView) findViewById(R.id.signup);
        SignUp.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        openSignUp();
                    }
                }
        );
    }

    public void openSignUp(){
        Intent newIntent = new Intent(signIn.this,signUp.class);
        startActivity(newIntent);
        Bungee.fade(signIn.this);
    }
}
