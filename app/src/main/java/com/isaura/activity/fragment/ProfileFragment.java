package com.isaura.activity.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.isaura.R;

public class ProfileFragment extends Fragment {

    FirebaseAuth mAuth;
    TextView txt_profile_display_name;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        txt_profile_display_name = root.findViewById(R.id.txt_profile_display_name);
        txt_profile_display_name.setText(mAuth.getCurrentUser().getDisplayName());

        return root;
    }

}