package com.isaura.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.isaura.R;
import com.isaura.activity.fragment.MeFragment;
import com.isaura.model.Activity;
import com.isaura.model.Member;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

public class Settings extends AppCompatActivity {
    CardView btn_save_changes, btn_upload_photo;
    ImageView img_avatar;
    TextView txt_username_setting, txt_email_setting;
    ProgressBar progress_bar_settings;
    Uri profile_image;
    DatabaseReference reference_member;
    StorageReference storage_ref_member;
    FirebaseAuth firebaseAuth;
    String member_url_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        Objects.requireNonNull(getSupportActionBar()).hide();

        btn_save_changes = findViewById(R.id.btn_save_changes);
        btn_upload_photo = findViewById(R.id.btn_upload_profile_image);
        img_avatar = findViewById(R.id.img_avatar);
        txt_username_setting = findViewById(R.id.txt_username_setting);
        txt_email_setting = findViewById(R.id.txt_email_setting);
        progress_bar_settings = findViewById(R.id.progress_bar_settings);

        reference_member = FirebaseDatabase.getInstance().getReference("member");
        storage_ref_member = FirebaseStorage.getInstance().getReference("member");
        firebaseAuth = FirebaseAuth.getInstance();

        txt_username_setting.setText(firebaseAuth.getCurrentUser().getDisplayName());
        txt_email_setting.setText(firebaseAuth.getCurrentUser().getEmail());

        reference_member.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot member: snapshot.getChildren()) {
                    Member member1 = member.getValue(Member.class);
                    if(member1.getEmail().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){

                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println(error);
            }
        });



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
                String member_uid = firebaseAuth.getCurrentUser().getUid();
                StorageReference image_reference = storage_ref_member.child(member_uid + ".png");
                image_reference.putFile(profile_image).addOnSuccessListener(taskSnapshot -> {
                    image_reference.getDownloadUrl().addOnSuccessListener(uri -> {
                        reference_member.child(member_uid).child("url_image").setValue(uri.toString()).addOnCompleteListener(task -> {
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