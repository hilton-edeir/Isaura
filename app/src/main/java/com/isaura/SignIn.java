package com.isaura;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class SignIn extends AppCompatActivity {

    TextInputLayout lyt_email, lyt_code;
    TextInputEditText fld_email, fld_code;
    Button btn_sign_in;
    ProgressBar progressBar;
    public FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_sign_in);
        getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();

        lyt_email = findViewById(R.id.lyt_email);
        lyt_code = findViewById(R.id.lyt_code);
        fld_email = findViewById(R.id.fld_email);
        fld_code = findViewById(R.id.fld_code);
        btn_sign_in = findViewById(R.id.btn_sign_in);
        progressBar = findViewById(R.id.progress_bar);

        btn_sign_in.setOnClickListener(v -> {
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

                    mAuth.signInWithEmailAndPassword(email, code)
                            .addOnCompleteListener(this, task -> {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    startActivity(new Intent(SignIn.this, Home.class));
                                    finish();
                                } else {
                                    Toast.makeText(SignIn.this, "Autenticação Falhada",Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }

        });
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }

    public void sign_up(View view) {
        startActivity(new Intent(SignIn.this, SignUp.class));
    }
}