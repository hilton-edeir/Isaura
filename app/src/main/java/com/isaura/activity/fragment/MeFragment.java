package com.isaura.activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.isaura.R;
import com.isaura.activity.SignIn;
import com.isaura.activity.adapter.NotificationAdapter;
import com.isaura.model.Notification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MeFragment extends Fragment {
    MaterialCardView btn_update_profile, btn_sign_out;
    ProgressBar progressBar;
    TextView txt_profile_display_name;
    RecyclerView recyclerView;
    NotificationAdapter notificationAdapter;
    FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private List<Notification> notificationList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.me_fragment, container, false);
        inicializeComponents(root);

        firebaseAuth = FirebaseAuth.getInstance();

        txt_profile_display_name.setText(Objects.requireNonNull(firebaseAuth.getCurrentUser()).getDisplayName());

        recyclerView = root.findViewById(R.id.recyclerview_notification);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        notificationList.clear();
        progressBar.setVisibility(View.VISIBLE);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot notification: snapshot.child("notification").getChildren()) {
                    Notification notification1 = notification.getValue(Notification.class);
                    notificationList.add(notification1);
                }
                notificationAdapter = new NotificationAdapter(getContext(), notificationList);
                recyclerView.setAdapter(notificationAdapter);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println(error);
            }
        });


        btn_update_profile.setOnClickListener(v -> Toast.makeText(root.getContext(),"Brevemente" , Toast.LENGTH_SHORT).show());

        btn_sign_out.setOnClickListener(v -> {
            if(firebaseAuth != null) {
                firebaseAuth.signOut();
                startActivity(new Intent(root.getContext(), SignIn.class));
                requireActivity().finish();
            }
        });

        return root;
    }

    public void inicializeComponents(View root) {
        btn_update_profile = root.findViewById(R.id.btn_update_profile);
        btn_sign_out = root.findViewById(R.id.btn_sign_out);
        txt_profile_display_name = root.findViewById(R.id.txt_profile_display_name);
        progressBar = root.findViewById(R.id.progress_bar_notification);
    }

}