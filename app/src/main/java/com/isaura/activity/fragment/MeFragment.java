package com.isaura.activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.isaura.R;
import com.isaura.activity.SignIn;

public class MeFragment extends Fragment {

    FirebaseAuth mAuth;
    CardView btn_update_profile, btn_sign_out;
    TextView txt_profile_display_name;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();
        View root = inflater.inflate(R.layout.me_fragment, container, false);

        btn_update_profile = root.findViewById(R.id.btn_update_profile);
        btn_sign_out = root.findViewById(R.id.btn_sign_out);
        txt_profile_display_name = root.findViewById(R.id.txt_profile_display_name);
        txt_profile_display_name.setText(mAuth.getCurrentUser().getDisplayName());



        btn_update_profile.setOnClickListener(v -> {
            Toast.makeText(root.getContext(),"Brevemente" , Toast.LENGTH_SHORT).show();
        });

        btn_sign_out.setOnClickListener(v -> {
            if(mAuth != null) {
                mAuth.signOut();
                startActivity(new Intent(root.getContext(), SignIn.class));
                getActivity().finish();
            }
        });


        return root;
    }

}