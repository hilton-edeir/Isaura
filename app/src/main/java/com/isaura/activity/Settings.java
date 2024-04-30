package com.isaura.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.isaura.R;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class Settings extends AppCompatActivity {
    CardView btn_save_changes, btn_upload_photo;
    ImageView img_user_profile_setting1, img_user_profile_setting2;
    TextView txt_username_setting, txt_email_setting;
    ProgressBar progress_bar_settings;
    DatabaseReference reference_member;
    StorageReference storage_ref_member;
    FirebaseUser user;
    Uri profile_image_selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        Objects.requireNonNull(getSupportActionBar()).hide();

        btn_save_changes = findViewById(R.id.btn_save_changes);
        btn_upload_photo = findViewById(R.id.btn_upload_profile_image);
        img_user_profile_setting1 = findViewById(R.id.img_user_profile_setting1);
        img_user_profile_setting2 = findViewById(R.id.img_user_profile_setting2);
        txt_username_setting = findViewById(R.id.txt_username_setting);
        txt_email_setting = findViewById(R.id.txt_email_setting);
        progress_bar_settings = findViewById(R.id.progress_bar_settings);

        reference_member = FirebaseDatabase.getInstance().getReference("member");
        storage_ref_member = FirebaseStorage.getInstance().getReference("member");
        user = FirebaseAuth.getInstance().getCurrentUser();

        if(user.getPhotoUrl()!=null) {
            Picasso.with(getApplicationContext()).load(user.getPhotoUrl()).into(img_user_profile_setting1);
            Picasso.with(getApplicationContext()).load(user.getPhotoUrl()).into(img_user_profile_setting2);
        }

        txt_username_setting.setText(user.getDisplayName());
        txt_email_setting.setText(user.getEmail());

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                o -> {
                    if(o.getResultCode() == RESULT_OK) {
                        Intent data = o.getData();
                        profile_image_selected = data.getData();
                        img_user_profile_setting1.setImageURI(profile_image_selected);
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
            if(profile_image_selected != null){
                String member_uid = user.getUid();
                StorageReference image_reference = storage_ref_member.child(member_uid + ".png");
                image_reference.putFile(profile_image_selected).addOnSuccessListener(taskSnapshot -> {
                    image_reference.getDownloadUrl().addOnSuccessListener(uri -> {
                        reference_member.child(member_uid).child("url_image").setValue(uri.toString()).addOnCompleteListener(task -> {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setPhotoUri(Uri.parse(uri.toString()))
                                    .build();
                            user.updateProfile(profileUpdates)
                                    .addOnCompleteListener(task1 -> {
                                        if (task1.isSuccessful()) {
                                            progress_bar_settings.setVisibility(View.GONE);
                                            Toast.makeText(Settings.this, "Alterações guardadas", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(Settings.this, Home.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    });
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

    /*
    private String getFileExtension(Uri profileImage) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(contentResolver.getType(profileImage));
    } */
}