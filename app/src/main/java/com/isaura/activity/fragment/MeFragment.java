package com.isaura.activity.fragment;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;
import com.isaura.R;
import com.isaura.activity.SignIn;

import java.util.Objects;

public class MeFragment extends Fragment {

    FirebaseAuth mAuth;
    MaterialCardView btn_update_profile, btn_sign_out, card_notification1, card_notification2, card_notification3, card_notification4;
    TextView txt_profile_display_name;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();
        View root = inflater.inflate(R.layout.me_fragment, container, false);

        btn_update_profile = root.findViewById(R.id.btn_update_profile);
        btn_sign_out = root.findViewById(R.id.btn_sign_out);
        txt_profile_display_name = root.findViewById(R.id.txt_profile_display_name);
        card_notification1 = root.findViewById(R.id.card_notification1);
        card_notification2 = root.findViewById(R.id.card_notification2);
        card_notification3 = root.findViewById(R.id.card_notification3);
        card_notification4 = root.findViewById(R.id.card_notification4);

        txt_profile_display_name.setText(Objects.requireNonNull(mAuth.getCurrentUser()).getDisplayName());

        btn_update_profile.setOnClickListener(v -> Toast.makeText(root.getContext(),"Brevemente" , Toast.LENGTH_SHORT).show());

        btn_sign_out.setOnClickListener(v -> {
            if(mAuth != null) {
                mAuth.signOut();
                startActivity(new Intent(root.getContext(), SignIn.class));
                requireActivity().finish();
            }
        });

        int nightModeFlags = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if(nightModeFlags == Configuration.UI_MODE_NIGHT_YES) {
            card_notification1.setCardBackgroundColor(0);
            card_notification2.setCardBackgroundColor(0);
            card_notification3.setCardBackgroundColor(0);
            card_notification4.setCardBackgroundColor(0);
        }

        return root;
    }

}