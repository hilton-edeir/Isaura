package com.isaura.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.isaura.R;

import java.util.Objects;

public class SignIn extends AppCompatActivity {

    TextInputLayout lyt_email, lyt_code;
    TextInputEditText fld_email, fld_code;
    Button btn_sign_in, btn_sign_up;
    ProgressBar progress_bar_sign_in;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_sign_in);
        Objects.requireNonNull(getSupportActionBar()).hide();

        inicializeComponents();
        mAuth = FirebaseAuth.getInstance();

        btn_sign_in.setOnClickListener(v -> {
            String email = Objects.requireNonNull(fld_email.getText()).toString().trim();
            String code = Objects.requireNonNull(fld_code.getText()).toString().trim();

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

                    progress_bar_sign_in.setVisibility(View.VISIBLE);

                    mAuth.signInWithEmailAndPassword(email, code)
                            .addOnCompleteListener(this, task -> {
                                progress_bar_sign_in.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    startActivity(new Intent(SignIn.this, Home.class));
                                    finish();
                                } else {
                                    Snackbar.make(Objects.requireNonNull(this.getCurrentFocus()), "Falha na autenticação", Snackbar.LENGTH_SHORT).show();
                                }
                            });
                }
            }

        });

        btn_sign_up.setOnClickListener(v -> {
            Intent intent = new Intent(SignIn.this, SignUp.class);
            startActivity(intent);
        });
    }

    private void inicializeComponents() {
        btn_sign_up = findViewById(R.id.btn_sign_up);
        lyt_email = findViewById(R.id.lyt_email);
        lyt_code = findViewById(R.id.lyt_code);
        fld_email = findViewById(R.id.fld_email);
        fld_code = findViewById(R.id.fld_code);
        btn_sign_in = findViewById(R.id.btn_sign_in);
        progress_bar_sign_in = findViewById(R.id.progress_bar_sign_in);
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }
}