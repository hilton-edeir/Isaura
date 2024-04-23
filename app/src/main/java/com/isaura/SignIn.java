package com.isaura;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class SignIn extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.act_sign_in);
        getSupportActionBar().hide();
        Button btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(v -> {
            startActivity(new Intent(SignIn.this, Home.class));
        });
    }

    public void sign_up(View view) {
        startActivity(new Intent(SignIn.this, SignUp.class));
    }
}