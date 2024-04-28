package com.isaura.activity.fragment;

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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.isaura.R;
import com.isaura.model.Activity;
import com.isaura.activity.adapter.AllActivitiesAdapter;

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
    DatabaseReference reference_notification;
    FirebaseAuth firebaseAuth;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.all_activities_fragment, container, false);

        inicializeComponents(view);

        recyclerview_all_activities.setLayoutManager(new LinearLayoutManager(getContext()));
        allActivitiesList.clear();

        progress_bar_all_activities.setVisibility(View.VISIBLE);
        reference_notification = FirebaseDatabase.getInstance().getReference();
        reference_notification.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot notification: snapshot.child("notification").getChildren()) {
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
                    allActivitiesAdapter = new AllActivitiesAdapter(getContext(), allActivitiesList);
                    progress_bar_all_activities.setVisibility(View.GONE);
                    recyclerview_all_activities.setAdapter(allActivitiesAdapter);
                    allActivitiesAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println(error);
            }
        });

        return view;
    }

    private void inicializeComponents(View view) {
        img_user_profile_all_activity = view.findViewById(R.id.img_user_profile_all_activity);
        txt_greeting_all_activity = view.findViewById(R.id.txt_greeting_all_activity);
        txt_name_profile_all_activity =view.findViewById(R.id.txt_name_profile_all_activity);
        recyclerview_all_activities = view.findViewById(R.id.recyclerview_all_activities);
        txt_activity_empty = view.findViewById(R.id.txt_all_activity_empty);
        progress_bar_all_activities = view.findViewById(R.id.progress_bar_all_activities);

        firebaseAuth = FirebaseAuth.getInstance();
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hour = cal.get(Calendar.HOUR_OF_DAY);

        if(hour>= 12 && hour < 17){
            txt_greeting_all_activity.setText("Boa tarde");
        } else if(hour >= 17 && hour < 21){
            txt_greeting_all_activity.setText("Boa noite");
        } else if(hour >= 21 && hour < 24){
            txt_greeting_all_activity.setText("Boa noite");
        } else {
            txt_greeting_all_activity.setText("Bom dia");
        }

        txt_name_profile_all_activity.setText(Objects.requireNonNull(firebaseAuth.getCurrentUser()).getDisplayName());
    }

}