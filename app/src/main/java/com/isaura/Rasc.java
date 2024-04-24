package com.isaura;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Rasc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_rasc);
        getSupportActionBar().hide();
    }
}