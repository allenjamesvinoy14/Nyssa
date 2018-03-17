package com.example.nyssa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import spencerstudios.com.bungeelib.Bungee;

public class signUp extends AppCompatActivity {
    Button SignUp;
    TextView Email,Pass,CnfPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Bungee.fade(signUp.this); //fire the slide left animation
    }
}
