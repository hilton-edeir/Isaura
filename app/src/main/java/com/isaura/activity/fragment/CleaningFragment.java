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
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.isaura.R;
import com.isaura.model.Cleaning;
import com.isaura.model.CleaningTable;
import com.isaura.model.Member;
import com.isaura.model.Notification;
import com.isaura.model.Place;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CleaningFragment extends Fragment {

    MaterialCardView btn_see_schedule, card_task_living_room, card_task_kitchen, card_task_bathroom, card_task_trash, card_task_free;
    TextView txt_date_selected;
    FirebaseDatabase database;
    DatabaseReference reference_notification;
    FirebaseAuth mAuth;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.cleaning_fragment, container, false);

        inicializeComponents(root);
        database = FirebaseDatabase.getInstance();
        reference_notification = database.getReference("notification");

        btn_see_schedule.setOnClickListener(v -> {
            String id_notification = reference_notification.push().getKey();
            assert id_notification != null;
            String id_cleaning = reference_notification.push().getKey();
            assert id_cleaning != null;

            calendar = Calendar.getInstance();
            simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            String date_now = simpleDateFormat.format(calendar.getTime());

            Member member = new Member("Ruténia", "marina@gmail.com", "123456", "uidchsdchehefh");
            Place place = new Place("Quarto", "rotgjiorijg");
            Cleaning cleaning = new Cleaning(id_cleaning, date_now, place, member);
            Notification notification = new Notification(date_now, "null", false, 2, cleaning);

            reference_notification.child(id_notification).setValue(notification).addOnCompleteListener(task1 -> Toast.makeText(root.getContext(), "Notificação enviada", Toast.LENGTH_SHORT).show());
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