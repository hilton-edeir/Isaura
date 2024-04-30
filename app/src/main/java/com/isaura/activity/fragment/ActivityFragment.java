package com.isaura.activity.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.isaura.R;
import com.isaura.model.Activity;
import com.isaura.activity.adapter.AllActivitiesAdapter;
import com.isaura.model.Member;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ActivityFragment extends Fragment {

    private AllActivitiesAdapter allActivitiesAdapter;
    private final List<Activity> allActivitiesList = new ArrayList<>();
    RecyclerView recyclerview_all_activities;
    ImageView img_user_profile_all_activity;
    TextView txt_greeting_all_activity, txt_name_profile_all_activity, txt_activity_empty;
    ProgressBar progress_bar_all_activities;
    DatabaseReference reference_activity, reference_member;
    FirebaseUser user;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.all_activities_fragment, container, false);
        inicializeComponents(view);
        reference_activity = FirebaseDatabase.getInstance().getReference("activity");
        reference_member = FirebaseDatabase.getInstance().getReference("member");
        user = FirebaseAuth.getInstance().getCurrentUser();

        if(user.getPhotoUrl()!=null) {
            Picasso.with(getContext()).load(user.getPhotoUrl()).into(img_user_profile_all_activity);
        }

        recyclerview_all_activities.setLayoutManager(new LinearLayoutManager(getContext()));

        reference_activity.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                allActivitiesList.clear();
                progress_bar_all_activities.setVisibility(View.VISIBLE);
                for(DataSnapshot notification: snapshot.getChildren()) {
                    Activity activity1 = notification.getValue(Activity.class);
                    if(activity1.isDone()) {
                        allActivitiesList.add(activity1);
                    }
                }
                if(allActivitiesList.isEmpty()) {
                    recyclerview_all_activities.setVisibility(View.GONE);
                    progress_bar_all_activities.setVisibility(View.GONE);
                    txt_activity_empty.setVisibility(View.VISIBLE);
                }
                else{
                    progress_bar_all_activities.setVisibility(View.GONE);
                    allActivitiesAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println(error);
            }
        });

        allActivitiesAdapter = new AllActivitiesAdapter(getContext(), allActivitiesList);
        recyclerview_all_activities.setAdapter(allActivitiesAdapter);

        return view;
    }

    private void inicializeComponents(View view) {
        img_user_profile_all_activity = view.findViewById(R.id.img_user_profile_all_activity);
        txt_greeting_all_activity = view.findViewById(R.id.txt_greeting_all_activity);
        txt_name_profile_all_activity =view.findViewById(R.id.txt_name_profile_all_activity);
        recyclerview_all_activities = view.findViewById(R.id.recyclerview_all_activities);
        txt_activity_empty = view.findViewById(R.id.txt_all_activity_empty);
        progress_bar_all_activities = view.findViewById(R.id.progress_bar_all_activities);

        txt_name_profile_all_activity.setText(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());

        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hour = cal.get(Calendar.HOUR_OF_DAY);

        if(hour>= 12 && hour < 17){
            txt_greeting_all_activity.setText("Boa tarde");
        }
        else if(hour >= 17 && hour < 21){
            txt_greeting_all_activity.setText("Boa noite");
        }
        else if(hour >= 21 && hour < 24){
            txt_greeting_all_activity.setText("Boa noite");
        }
        else {
            txt_greeting_all_activity.setText("Bom dia");
        }
    }
}