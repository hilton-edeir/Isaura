package com.isaura;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.window.SplashScreen;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_splash);
        Objects.requireNonNull(getSupportActionBar()).hide();


        new Handler().postDelayed(() -> {
            startActivity(new Intent(Splash.this, Login.class));
            finish();
        }, 3000);
    }
}