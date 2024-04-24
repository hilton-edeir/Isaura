package com.isaura;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class SignUp extends AppCompatActivity {

    TextInputLayout lyt_email, lyt_code;
    TextInputEditText fld_email, fld_code;
    ProgressBar progressBar;
    Button btn_sign_up;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.act_sign_up);
        getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();

        lyt_email = findViewById(R.id.lyt_email);
        lyt_code = findViewById(R.id.lyt_code);
        fld_email = findViewById(R.id.fld_email);
        fld_code = findViewById(R.id.fld_code);
        btn_sign_up = findViewById(R.id.btn_sign_up);
        progressBar = findViewById(R.id.progress_bar);

        btn_sign_up.setOnClickListener(v -> {
            String email = Objects.requireNonNull(fld_email.getText()).toString();
            String code = Objects.requireNonNull(fld_code.getText()).toString();

            if (email.isEmpty() && code.isEmpty()) {
                lyt_email.setHelperText("Insira o email");
                lyt_code.setHelperText("Insira o código");
            }
            if (email.isEmpty()) {
                lyt_email.setHelperText("Insira o email");
                lyt_code.setHelperText(null);
            }
            else {
                if(code.isEmpty()){
                    lyt_email.setHelperText(null);
                    lyt_code.setHelperText("Insira o código");
                }
                else {
                    lyt_email.setHelperText(null);
                    lyt_code.setHelperText(null);
                    progressBar.setVisibility(View.VISIBLE);
                    mAuth.createUserWithEmailAndPassword(email, code)
                            .addOnCompleteListener(this, task -> {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(SignUp.this, "Conta criada com sucesso", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(SignUp.this, SignIn.class));
                                    finish();
                                }
                                else {
                                    Toast.makeText(SignUp.this, "Falha ao criar a conta", Toast.LENGTH_LONG).show();
                                }
                            });
                }
            }

        });

    }

    public void sign_in(View view) {
        startActivity(new Intent(SignUp.this, SignIn.class));
    }
}