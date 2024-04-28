package com.isaura.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.isaura.R;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class SignIn extends AppCompatActivity {

    TextInputLayout lyt_email, lyt_code;
    TextInputEditText fld_email, fld_code;
    Button btn_sign_in;
    ProgressBar progressBar;
    FirebaseAuth mAuth;
    FirebaseStorage storage;
    StorageReference storageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_sign_in);
        Objects.requireNonNull(getSupportActionBar()).hide();

        mAuth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();

        inicializeComponents();

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

    /*
    private void savetofirebase() {
        Resources resources = getResources();
        Uri img = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                resources.getResourcePackageName(R.drawable.ic_bathroom) + '/' +
                resources.getResourceTypeName(R.drawable.ic_bathroom) + '/' +
                resources.getResourceEntryName(R.drawable.ic_bathroom) );

        storageRef = storage.getReference("utensils").child(System.currentTimeMillis() + "pn");
        storageRef.putFile(img).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Toast.makeText(SignIn.this, uri.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
*/
    private void inicializeComponents() {
        lyt_email = findViewById(R.id.lyt_email);
        lyt_code = findViewById(R.id.lyt_code);
        fld_email = findViewById(R.id.fld_email);
        fld_code = findViewById(R.id.fld_code);
        btn_sign_in = findViewById(R.id.btn_sign_in);
        progressBar = findViewById(R.id.progress_bar);
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