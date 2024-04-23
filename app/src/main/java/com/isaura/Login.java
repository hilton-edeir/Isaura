package com.isaura;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.act_login);
        getSupportActionBar().hide();
        Button btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(v -> {
            startActivity(new Intent(Login.this, Home.class));
        });
    }
}