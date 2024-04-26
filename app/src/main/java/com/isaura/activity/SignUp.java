package com.isaura.activity;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.isaura.R;
import com.isaura.model.Member;

import java.util.Objects;

public class SignUp extends AppCompatActivity {

    TextInputLayout lyt_name, lyt_email, lyt_code;
    TextInputEditText fld_name, fld_email, fld_code;
    ProgressBar progressBar;
    Button btn_sign_up;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_sign_up);
        Objects.requireNonNull(getSupportActionBar()).hide();

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("users");

        lyt_name = findViewById(R.id.lyt_name);
        lyt_email = findViewById(R.id.lyt_email);
        lyt_code = findViewById(R.id.lyt_code);
        fld_name = findViewById(R.id.fld_name);
        fld_email = findViewById(R.id.fld_email);
        fld_code = findViewById(R.id.fld_code);
        btn_sign_up = findViewById(R.id.btn_sign_up);
        progressBar = findViewById(R.id.progress_bar);

        btn_sign_up.setOnClickListener(v -> {
            String name = Objects.requireNonNull(fld_name.getText()).toString();
            String email = Objects.requireNonNull(fld_email.getText()).toString();
            String code = Objects.requireNonNull(fld_code.getText()).toString();

            if (name.isEmpty()) {
                lyt_name.setHelperText("Insira o nome");
                lyt_email.setHelperText(null);
                lyt_code.setHelperText(null);
            }
            else if (email.isEmpty()) {
                lyt_name.setHelperText(null);
                lyt_email.setHelperText("Insira o email");
                lyt_code.setHelperText(null);
            }
            else if (code.isEmpty()) {
                lyt_name.setHelperText(null);
                lyt_email.setHelperText(null);
                lyt_code.setHelperText("Insira o código");
            }
            else {
                lyt_name.setHelperText(null);
                lyt_email.setHelperText(null);
                lyt_code.setHelperText(null);

                progressBar.setVisibility(View.VISIBLE);

                mAuth.createUserWithEmailAndPassword(email, code)
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {
                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(name)
                                        .build();
                                mAuth.getCurrentUser().updateProfile(profileUpdates)
                                        .addOnCompleteListener(task1 -> {
                                            if (task1.isSuccessful()) {
                                                Member member = new Member(name, email, code, "user.png");
                                                databaseReference.child(mAuth.getCurrentUser().getUid()).setValue(member).addOnCompleteListener(task2 -> {
                                                    progressBar.setVisibility(View.GONE);
                                                    Toast.makeText(SignUp.this, "Conta criada com sucesso", Toast.LENGTH_LONG).show();
                                                    startActivity(new Intent(SignUp.this, SignIn.class));
                                                    finish();
                                                });
                                            }
                                        });
                            }
                            else {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(SignUp.this, "Falha ao criar a conta", Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });

    }

    public void sign_in(View view) {
        startActivity(new Intent(SignUp.this, SignIn.class));
    }
}