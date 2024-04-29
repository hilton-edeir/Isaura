package com.isaura.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.isaura.R;
import com.isaura.activity.fragment.MeFragment;
import com.isaura.model.Activity;

import java.util.Objects;

public class Settings extends AppCompatActivity {
    CardView btn_save_changes, btn_upload_photo;
    ImageView img_avatar;
    ProgressBar progress_bar_settings;
    Uri profile_image;
    DatabaseReference reference_member;
    StorageReference storage_ref_member;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        Objects.requireNonNull(getSupportActionBar()).hide();

        btn_save_changes = findViewById(R.id.btn_save_changes);
        btn_upload_photo = findViewById(R.id.btn_upload_profile_image);
        img_avatar = findViewById(R.id.img_avatar);
        progress_bar_settings = findViewById(R.id.progress_bar_settings);

        reference_member = FirebaseDatabase.getInstance().getReference("member");
        storage_ref_member = FirebaseStorage.getInstance().getReference("member");
        firebaseAuth = FirebaseAuth.getInstance();

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                o -> {
                    if(o.getResultCode() == RESULT_OK) {
                        Intent data = o.getData();
                        profile_image = data.getData();
                        img_avatar.setImageURI(profile_image);
                    }
                    else {
                        Toast.makeText(Settings.this, "No image selected", Toast.LENGTH_SHORT).show();
                    }
                }
        );


        btn_upload_photo.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            activityResultLauncher.launch(intent);
        });

        btn_save_changes.setOnClickListener(v -> {
            if(profile_image != null){
                StorageReference image_reference = storage_ref_member.child(System.currentTimeMillis() + "." + getFileExtension(profile_image));
                image_reference.putFile(profile_image).addOnSuccessListener(taskSnapshot -> {
                    image_reference.getDownloadUrl().addOnSuccessListener(uri -> {
                        String key = firebaseAuth.getCurrentUser().getUid();
                        reference_member.child(key).child("url_image").setValue(uri).addOnCompleteListener(task -> {
                            progress_bar_settings.setVisibility(View.GONE);
                            Toast.makeText(Settings.this, "Alterações guardadas", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Settings.this, Home.class);
                            startActivity(intent);
                            finish();
                        });
                    });
                }).addOnProgressListener(snapshot -> {
                    progress_bar_settings.setVisibility(View.VISIBLE);
                }).addOnFailureListener(e -> Toast.makeText(Settings.this, "Falha ao guardar a imagem", Toast.LENGTH_SHORT).show());
            }
            else  {
                Toast.makeText(Settings.this, "No image selected", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getFileExtension(Uri profileImage) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(contentResolver.getType(profileImage));
    }
}