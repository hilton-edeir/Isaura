package com.isaura.activity.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.isaura.R;
import com.isaura.model.AllActivities;
import com.isaura.model.Cleaning;
import com.isaura.model.Member;
import com.isaura.model.Place;
import com.isaura.activity.adapter.AllActivitiesAdapter;

import java.util.ArrayList;

public class AllActivitiesFragment extends Fragment {

    private AllActivitiesAdapter allActivitiesAdapter;
    private ArrayList<AllActivities> allActivitiesArrayList;
    RecyclerView recyclerview_all_activities;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.all_activities_fragment, container, false);

        recyclerview_all_activities = view.findViewById(R.id.recyclerview_all_activities);
        recyclerview_all_activities.setLayoutManager(new LinearLayoutManager(getContext()));
        allActivitiesArrayList = new ArrayList<>();
        createListData();
        allActivitiesAdapter = new AllActivitiesAdapter(getContext(), allActivitiesArrayList);
        recyclerview_all_activities.setAdapter(allActivitiesAdapter);

        return view;
    }


    private void createListData() {
        Member member = new Member("Júlia", "julia@gmail.com", "123456", "fjhffohifrhoi");
        Place place = new Place("Quarto", "tgrhyyhyht");
        Cleaning cleaning = new Cleaning("1", "25/04/2024", place, member);
        AllActivities allActivities = new AllActivities("1", cleaning);
        allActivitiesArrayList.add(allActivities);
    }
}