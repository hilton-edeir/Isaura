package com.isaura.ui.me;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.isaura.R;
import com.isaura.ui.SignIn;
import com.isaura.model.Activity;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MeFragment extends Fragment implements SelectNotificationListener {
    MaterialCardView btn_update_profile, btn_sign_out;
    ProgressBar progressBar;
    ImageView img_user_profile_me;
    TextView txt_profile_display_name, txt_notification_empty;
    RecyclerView recyclerview_notification;
    NotificationAdapter notificationAdapter;
    FirebaseAuth firebaseAuth;
    DatabaseReference reference_activity, reference_member;
    FirebaseUser user;
    List<Activity> activityList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.me_fragment, container, false);
        inicializeComponents(root);
        SelectNotificationListener selectNotificationListener = this;
        reference_activity = FirebaseDatabase.getInstance().getReference("activity");
        reference_member = FirebaseDatabase.getInstance().getReference("member");
        firebaseAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();

        txt_profile_display_name.setText(user.getDisplayName());

        if(user.getPhotoUrl()!=null) {
            Picasso.with(getContext()).load(user.getPhotoUrl()).into(img_user_profile_me);
        }

        recyclerview_notification.setLayoutManager(new LinearLayoutManager(getContext()));
        reference_activity.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                activityList.clear();
                progressBar.setVisibility(View.VISIBLE);
                for(DataSnapshot notification: snapshot.getChildren()) {
                    Activity activity1 = notification.getValue(Activity.class);
                    assert activity1 != null;
                    if(!activity1.isDone()) {
                        activityList.add(activity1);
                    }
                }
                if(activityList.isEmpty()) {
                    recyclerview_notification.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);
                    txt_notification_empty.setVisibility(View.VISIBLE);
                }
                else {
                    progressBar.setVisibility(View.GONE);
                    notificationAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println(error);
            }
        });
        notificationAdapter = new NotificationAdapter(getContext(), activityList, selectNotificationListener);
        recyclerview_notification.setAdapter(notificationAdapter);

        btn_update_profile.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), Act_EditProfile.class);
            startActivity(intent);
        });

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
        img_user_profile_me = root.findViewById(R.id.img_user_profile_me);
        progressBar = root.findViewById(R.id.progress_bar_notification);
        txt_notification_empty = root.findViewById(R.id.txt_notification_empty);
        recyclerview_notification = root.findViewById(R.id.recyclerview_notification);
    }

    @Override
    public void onItemClicked(Activity activity) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String date_now = simpleDateFormat.format(calendar.getTime());

        if(activity.getType() == 1) {
            reference_activity.child(String.valueOf(activity.getId())).child("done").setValue(true);
            reference_activity.child(String.valueOf(activity.getId())).child("date_done").setValue(date_now);
        }
        else if(activity.getType() == 2){
            reference_activity.child(String.valueOf(activity.getId())).child("done").setValue(true);
        }
    }
}