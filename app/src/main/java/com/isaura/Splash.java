package com.isaura;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.act_splash);
        getSupportActionBar().hide();

        new Handler().postDelayed(() -> startActivity(new Intent(Splash.this, Home.class)), 3000);
    }
}