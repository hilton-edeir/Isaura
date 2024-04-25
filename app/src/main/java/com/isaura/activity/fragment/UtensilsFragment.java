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

import com.google.android.material.card.MaterialCardView;
import com.isaura.R;

public class UtensilsFragment extends Fragment {

    MaterialCardView btn_napkin, btn_dish_soap, btn_sponge;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.utensils_fragment, container, false);

        btn_napkin = root.findViewById(R.id.btn_napkin);
        btn_dish_soap = root.findViewById(R.id.btn_dish_soap);
        btn_sponge = root.findViewById(R.id.btn_sponge);

        /*
        btn_napkin.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                btn_napkin.setCardBackgroundColor(R.color.md_theme_primary);
                return true;
            }
        }); */

        btn_napkin.setOnClickListener(v -> {
            Toast.makeText(root.getContext(), "Brevemente", Toast.LENGTH_SHORT).show();
            /*Fragment cleaningScheduleFragment = new CleaningScheduleFragment();
            FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
            fm.replace(R.id.nav_host_fragment_act_home, cleaningScheduleFragment).commit();*/
        });

        btn_dish_soap.setOnClickListener(v -> {
            Toast.makeText(root.getContext(), "Brevemente", Toast.LENGTH_SHORT).show();
        });

        btn_sponge.setOnClickListener(v -> {
            Toast.makeText(root.getContext(), "Brevemente", Toast.LENGTH_SHORT).show();
        });


        return root;
    }
}