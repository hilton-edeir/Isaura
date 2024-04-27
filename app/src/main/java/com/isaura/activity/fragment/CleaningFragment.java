package com.isaura.activity.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.isaura.R;
import com.isaura.model.CleaningTable;
import com.isaura.model.Member;
import com.isaura.model.Place;
import com.isaura.model.LinkedList;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class CleaningFragment extends Fragment {

    MaterialCardView btn_see_schedule, card_task_living_room, card_task_kitchen, card_task_bathroom, card_task_trash, card_task_free;
    TextView txt_date_selected;
    FirebaseDatabase database;
    DatabaseReference reference_cleaning;
    FirebaseAuth mAuth;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.cleaning_fragment, container, false);

        inicializeComponents(root);
        database = FirebaseDatabase.getInstance();
        reference_cleaning = database.getReference("cronograma-limpeza");

        btn_see_schedule.setOnClickListener(v -> {
            String id_cleaning_Table = reference_cleaning.push().getKey();
            assert id_cleaning_Table != null;

            calendar = Calendar.getInstance();
            simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            String date_now = simpleDateFormat.format(calendar.getTime());

            Member member1 = new Member("Marina", "marina@gmail.com", "123456", "uidchsdchehefh");
            Member member2 = new Member("Carline", "carline@gmail.com", "123456", "uidchsdchehefh");
            Member member3 = new Member("Maria", "maria@gmail.com", "123456", "uidchsdchehefh");
            Member member4 = new Member("Ruténia", "rutenia@gmail.com", "123456", "uidchsdchehefh");
            Member member5 = new Member("Hilton", "hilton@gmail.com", "123456", "uidchsdchehefh");
            ArrayList<Member> memberList = new ArrayList<>();
            memberList.add(member1);
            memberList.add(member2);
            memberList.add(member3);
            memberList.add(member4);
            memberList.add(member5);
            Place place = new Place("cozinha", "drfgyeudhjh");

            CleaningTable cleaningTable = new CleaningTable(place, LinkedList.schecule(memberList));

            reference_cleaning.child(id_cleaning_Table).setValue(cleaningTable).addOnCompleteListener(task1 -> Toast.makeText(root.getContext(), "Notificação enviada", Toast.LENGTH_SHORT).show());

        });

        return root;
    }

    public void inicializeComponents(View root) {
        btn_see_schedule= root.findViewById(R.id.btn_see_schedule);
        txt_date_selected = root.findViewById(R.id.txt_date_selected);
        card_task_kitchen = root.findViewById(R.id.card_task_kitchen);
        card_task_living_room = root.findViewById(R.id.card_task_living_room);
        card_task_bathroom = root.findViewById(R.id.card_task_bathroom);
        card_task_trash = root.findViewById(R.id.card_task_trash);
        card_task_free = root.findViewById(R.id.card_task_free);


        int nightModeFlags = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if(nightModeFlags == Configuration.UI_MODE_NIGHT_YES) {
            card_task_kitchen.setCardBackgroundColor(0);
            card_task_living_room.setCardBackgroundColor(0);
            card_task_bathroom.setCardBackgroundColor(0);
            card_task_trash.setCardBackgroundColor(0);
            card_task_free.setCardBackgroundColor(0);
        }
    }

}