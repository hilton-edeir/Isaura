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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class MeFragment extends Fragment implements SelectNotificationListener{
    MaterialCardView btn_update_profile, btn_sign_out;
    ProgressBar progressBar;
    TextView txt_profile_display_name, txt_notification_empty;
    RecyclerView recyclerView;
    NotificationAdapter notificationAdapter;
    FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private List<Notification> notificationList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.me_fragment, container, false);
        inicializeComponents(root);

        firebaseAuth = FirebaseAuth.getInstance();
        SelectNotificationListener selectNotificationListener = this;

        txt_profile_display_name.setText(Objects.requireNonNull(firebaseAuth.getCurrentUser()).getDisplayName());

        recyclerView = root.findViewById(R.id.recyclerview_notification);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        notificationList.clear();
        progressBar.setVisibility(View.VISIBLE);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    for (DataSnapshot notification: snapshot.child("notification").getChildren()) {
                        Notification notification1 = notification.getValue(Notification.class);
                        if(!notification1.isDone()) {
                            notificationList.add(notification1);
                        }
                    }
                    if(notificationList.isEmpty()) {
                        recyclerView.setVisibility(View.GONE);
                        progressBar.setVisibility(View.GONE);
                        txt_notification_empty.setVisibility(View.VISIBLE);
                    }
                    else {
                        notificationAdapter = new NotificationAdapter(getContext(), notificationList, selectNotificationListener);
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setAdapter(notificationAdapter);
                        notificationAdapter.notifyDataSetChanged();

                    }
                }
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
                getActivity().finish();
            }
        });

        return root;
    }

    public void inicializeComponents(View root) {
        btn_update_profile = root.findViewById(R.id.btn_update_profile);
        btn_sign_out = root.findViewById(R.id.btn_sign_out);
        txt_profile_display_name = root.findViewById(R.id.txt_profile_display_name);
        progressBar = root.findViewById(R.id.progress_bar_notification);
        txt_notification_empty = root.findViewById(R.id.txt_notification_empty);
    }

    @Override
    public void onItemClicked(Notification notification) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String date_now = simpleDateFormat.format(calendar.getTime());

        if(notification.getType() == 1) {
            databaseReference.child("notification").child(notification.getId()).child("done").setValue(true);
            databaseReference.child("notification").child(notification.getId()).child("date_done").setValue(date_now);
        }
        else if(notification.getType() == 2){
            Toast.makeText(getContext(), "Brevemente", Toast.LENGTH_SHORT).show();
        }


    }
}