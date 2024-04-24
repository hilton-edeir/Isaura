package com.isaura.activity.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.isaura.R;

public class UtensilsFragment extends Fragment {

    CardView btn_cleaning_schedule, btn_utensils_stock, btn_bills;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.utensils_fragment, container, false);

        btn_cleaning_schedule = root.findViewById(R.id.btn_cleaning_schedule);
        btn_utensils_stock = root.findViewById(R.id.btn_utensils_stock);
        btn_bills = root.findViewById(R.id.btn_bills);

        btn_cleaning_schedule.setOnClickListener(v -> {
            Toast.makeText(root.getContext(), "Brevemente", Toast.LENGTH_SHORT).show();
            /*Fragment cleaningScheduleFragment = new CleaningScheduleFragment();
            FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
            fm.replace(R.id.nav_host_fragment_act_home, cleaningScheduleFragment).commit();*/
        });

        btn_utensils_stock.setOnClickListener(v -> {
            Toast.makeText(root.getContext(), "Brevemente", Toast.LENGTH_SHORT).show();
        });

        btn_bills.setOnClickListener(v -> {
            Toast.makeText(root.getContext(), "Brevemente", Toast.LENGTH_SHORT).show();
        });

        return root;
    }
}