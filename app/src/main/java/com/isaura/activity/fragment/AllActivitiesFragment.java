package com.isaura.activity.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.isaura.R;
import com.isaura.activity.adapter.NotificationAdapter;
import com.isaura.model.AllActivities;
import com.isaura.model.Cleaning;
import com.isaura.model.Member;
import com.isaura.model.Notification;
import com.isaura.model.Place;
import com.isaura.activity.adapter.AllActivitiesAdapter;

import java.util.ArrayList;
import java.util.List;

public class AllActivitiesFragment extends Fragment {

    private AllActivitiesAdapter allActivitiesAdapter;
    private final List<Notification> allActivitiesList = new ArrayList<>();
    RecyclerView recyclerview_all_activities;
    private DatabaseReference reference_notification;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.all_activities_fragment, container, false);

        recyclerview_all_activities = view.findViewById(R.id.recyclerview_all_activities);
        recyclerview_all_activities.setLayoutManager(new LinearLayoutManager(getContext()));
        allActivitiesList.clear();

        reference_notification = FirebaseDatabase.getInstance().getReference();
        reference_notification.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot notification: snapshot.child("notification").getChildren()) {
                    Notification notification1 = notification.getValue(Notification.class);
                    if(notification1.isDone()) {
                        allActivitiesList.add(notification1);
                    }
                }
                allActivitiesAdapter = new AllActivitiesAdapter(getContext(), allActivitiesList);
                recyclerview_all_activities.setAdapter(allActivitiesAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println(error);
            }
        });

        return view;
    }

}