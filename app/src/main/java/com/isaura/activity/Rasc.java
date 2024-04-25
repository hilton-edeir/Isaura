package com.isaura.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.isaura.R;

import java.util.Objects;

public class Rasc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_rasc);
        Objects.requireNonNull(getSupportActionBar()).hide();
    }
}