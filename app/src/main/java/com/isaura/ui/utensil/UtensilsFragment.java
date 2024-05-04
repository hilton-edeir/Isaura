package com.isaura.ui.utensil;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.isaura.R;
import com.isaura.model.Member;
import com.isaura.model.Activity;
import com.isaura.model.Utensil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class UtensilsFragment extends Fragment {

    MaterialCardView btn_napkin, btn_matches, btn_dish_soap, btn_sponge, btn_scrub, btn_salt,
            btn_trashbag, btn_aluminium_foil, btn_plastic_foil, btn_bath_detergent, btn_liquid_soap,
            card_napkin, card_matches, card_dish_soap, card_sponge, card_scrub, card_salt, card_trashbag,
            card_aluminium_foil, card_plastic_foil, card_bath_detergent, card_liquid_soap;
    FirebaseDatabase database;
    DatabaseReference reference_activity;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    List<Activity> activityList = new ArrayList<>();
    int ID_NOTIFICATION = 0;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.utensils_fragment, container, false);
        inicializeComponents(root);

        database = FirebaseDatabase.getInstance();
        reference_activity = database.getReference("activity");

        reference_activity.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    activityList.clear();
                    ID_NOTIFICATION = (int) snapshot.getChildrenCount() + 1;
                    for(DataSnapshot notification: snapshot.getChildren()) {
                        Activity notific = notification.getValue(Activity.class);
                        assert notific != null;
                        if(!notific.isDone()) {
                            activityList.add(notific);
                        }
                    }
                }
                else {
                    ID_NOTIFICATION = 1;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btn_napkin.setOnClickListener(v -> {
            calendar = Calendar.getInstance();
            simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            String date_now = simpleDateFormat.format(calendar.getTime());

            Member member = new Member("Ruténia", "marina@gmail.com", "123456", "uidchsdchehefh", 4);
            Utensil utensil = new Utensil("guardanapos", "https//:firebase-guadanapos.jpg", true);

            Activity activity = new Activity(ID_NOTIFICATION, date_now, "null", false, 1, member, utensil);
            boolean exists = false;

            if(!(activityList.isEmpty())){
                for(Activity notification: activityList) {
                    if(notification.getType() == 1) {
                        if(notification.getUtensil().getName().equals(utensil.getName())) {
                            exists = true;
                        }
                    }
                }
            }
            if(exists) {
                Toast.makeText(getContext(), "Já foi pedido", Toast.LENGTH_SHORT).show();
            }
            else {
                activityList.clear();
                reference_activity.child(String.valueOf(ID_NOTIFICATION)).setValue(activity).addOnCompleteListener(task1 -> Snackbar.make(Objects.requireNonNull(root.getRootView()), "Notificação Enviada", Snackbar.LENGTH_SHORT).show());
            }
        });

        btn_matches.setOnClickListener(v -> {
            calendar = Calendar.getInstance();
            simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            String date_now = simpleDateFormat.format(calendar.getTime());

            Member member = new Member("Carline", "carline@gmail.com", "123456", "uidchsdchehefh", 2);
            Utensil utensil = new Utensil("fosforos", "https//:firebase-fosforos.jpg", true);

            Activity activity = new Activity(ID_NOTIFICATION, date_now, "null", false, 1, member, utensil);
            boolean exists = false;

            if(!(activityList.isEmpty())){
                for(Activity notification: activityList) {
                    if(notification.getType() == 1) {
                        if(notification.getUtensil().getName().equals(utensil.getName())) {
                            exists = true;
                        }
                    }
                }
            }
            if(exists) {
                Toast.makeText(getContext(), "Já foi pedido", Toast.LENGTH_SHORT).show();
            }
            else {
                reference_activity.child(String.valueOf(ID_NOTIFICATION)).setValue(activity).addOnCompleteListener(task1 -> Toast.makeText(root.getContext(), "Notificação enviada", Toast.LENGTH_SHORT).show());
            }
        });

        btn_dish_soap.setOnClickListener(v -> {
            calendar = Calendar.getInstance();
            simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            String date_now = simpleDateFormat.format(calendar.getTime());

            Member member = new Member("Hilton", "hilton@gmail.com", "123456", "uidchsdchehefh", 5);
            Utensil utensil = new Utensil("detergente-loica", "https//:firebase-fosforos.jpg", true);

            Activity activity = new Activity(ID_NOTIFICATION, date_now, "null", false, 1, member, utensil);
            boolean exists = false;

            if(!(activityList.isEmpty())){
                for(Activity notification: activityList) {
                    if(notification.getType() == 1) {
                        if(notification.getUtensil().getName().equals(utensil.getName())) {
                            exists = true;
                        }
                    }
                }
            }
            if(exists) {
                Toast.makeText(getContext(), "Já foi pedido", Toast.LENGTH_SHORT).show();
            }
            else {
                reference_activity.child(String.valueOf(ID_NOTIFICATION)).setValue(activity).addOnCompleteListener(task1 -> Toast.makeText(root.getContext(), "Notificação enviada", Toast.LENGTH_SHORT).show());
            }
        });

        btn_sponge.setOnClickListener(v -> {
            calendar = Calendar.getInstance();
            simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            String date_now = simpleDateFormat.format(calendar.getTime());

            Member member = new Member("Marina", "marina@gmail.com", "123456", "uidchsdchehefh", 1);
            Utensil utensil = new Utensil("esponja", "https//:firebase-fosforos.jpg", true);
            Activity activity = new Activity(ID_NOTIFICATION, date_now, "null", false, 1, member, utensil);
            boolean exists = false;

            if(!(activityList.isEmpty())){
                for(Activity notification: activityList) {
                    if(notification.getType() == 1) {
                        if(notification.getUtensil().getName().equals(utensil.getName())) {
                            exists = true;
                        }
                    }
                }
            }
            if(exists) {
                Toast.makeText(getContext(), "Já foi pedido", Toast.LENGTH_SHORT).show();
            }
            else {
                reference_activity.child(String.valueOf(ID_NOTIFICATION)).setValue(activity).addOnCompleteListener(task1 -> Toast.makeText(root.getContext(), "Notificação enviada", Toast.LENGTH_SHORT).show());
            }
        });

        btn_scrub.setOnClickListener(v -> {
            calendar = Calendar.getInstance();
            simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            String date_now = simpleDateFormat.format(calendar.getTime());

            Member member = new Member("Marina", "marina@gmail.com", "123456", "uidchsdchehefh", 1);
            Utensil utensil = new Utensil("fergao", "https//:firebase-fosforos.jpg", true);

            Activity activity = new Activity(ID_NOTIFICATION, date_now, "null", false, 1, member, utensil);
            boolean exists = false;

            if(!(activityList.isEmpty())){
                for(Activity notification: activityList) {
                    if(notification.getType() == 1) {
                        if(notification.getUtensil().getName().equals(utensil.getName())) {
                            exists = true;
                        }
                    }
                }
            }
            if(exists) {
                Toast.makeText(getContext(), "Já foi pedido", Toast.LENGTH_SHORT).show();
            }
            else {
                reference_activity.child(String.valueOf(ID_NOTIFICATION)).setValue(activity).addOnCompleteListener(task1 -> Toast.makeText(root.getContext(), "Notificação enviada", Toast.LENGTH_SHORT).show());
            }
        });

        btn_salt.setOnClickListener(v -> {
            calendar = Calendar.getInstance();
            simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            String date_now = simpleDateFormat.format(calendar.getTime());

            Member member = new Member("Maria", "maria@gmail.com", "123456", "uidchsdchehefh", 3);
            Utensil utensil = new Utensil("sal", "https//:firebase-fosforos.jpg", true);

            Activity activity = new Activity(ID_NOTIFICATION, date_now, "null", false, 1, member, utensil);
            boolean exists = false;

            if(!(activityList.isEmpty())){
                for(Activity notification: activityList) {
                    if(notification.getType() == 1) {
                        if(notification.getUtensil().getName().equals(utensil.getName())) {
                            exists = true;
                        }
                    }
                }
            }
            if(exists) {
                Toast.makeText(getContext(), "Já foi pedido", Toast.LENGTH_SHORT).show();
            }
            else {
                reference_activity.child(String.valueOf(ID_NOTIFICATION)).setValue(activity).addOnCompleteListener(task1 -> Toast.makeText(root.getContext(), "Notificação enviada", Toast.LENGTH_SHORT).show());
            }
        });

        btn_trashbag.setOnClickListener(v -> {
            calendar = Calendar.getInstance();
            simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            String date_now = simpleDateFormat.format(calendar.getTime());

            Member member = new Member("Hilton", "hilton@gmail.com", "123456", "uidchsdchehefh", 5);
            Utensil utensil = new Utensil("saco-lixo", "https//:firebase-fosforos.jpg", true);

            Activity activity = new Activity(ID_NOTIFICATION, date_now, "null", false, 1, member, utensil);
            boolean exists = false;

            if(!(activityList.isEmpty())){
                for(Activity notification: activityList) {
                    if(notification.getType() == 1) {
                        if(notification.getUtensil().getName().equals(utensil.getName())) {
                            exists = true;
                        }
                    }
                }
            }
            if(exists) {
                Toast.makeText(getContext(), "Já foi pedido", Toast.LENGTH_SHORT).show();
            }
            else {
                reference_activity.child(String.valueOf(ID_NOTIFICATION)).setValue(activity).addOnCompleteListener(task1 -> Toast.makeText(root.getContext(), "Notificação enviada", Toast.LENGTH_SHORT).show());
            }
        });

        btn_aluminium_foil.setOnClickListener(v -> {
            calendar = Calendar.getInstance();
            simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            String date_now = simpleDateFormat.format(calendar.getTime());

            Member member = new Member("Hilton", "hilton@gmail.com", "123456", "uidchsdchehefh", 5);
            Utensil utensil = new Utensil("rolo-aluminio", "https//:firebase-fosforos.jpg", true);

            Activity activity = new Activity(ID_NOTIFICATION, date_now, "null", false, 1, member, utensil);
            boolean exists = false;

            if(!(activityList.isEmpty())){
                for(Activity notification: activityList) {
                    if(notification.getType() == 1) {
                        if(notification.getUtensil().getName().equals(utensil.getName())) {
                            exists = true;
                        }
                    }
                }
            }
            if(exists) {
                Toast.makeText(getContext(), "Já foi pedido", Toast.LENGTH_SHORT).show();
            }
            else {
                reference_activity.child(String.valueOf(ID_NOTIFICATION)).setValue(activity).addOnCompleteListener(task1 -> Toast.makeText(root.getContext(), "Notificação enviada", Toast.LENGTH_SHORT).show());
            }
        });

        btn_plastic_foil.setOnClickListener(v -> {
            calendar = Calendar.getInstance();
            simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            String date_now = simpleDateFormat.format(calendar.getTime());

            Member member = new Member("Hilton", "hilton@gmail.com", "123456", "uidchsdchehefh", 5);
            Utensil utensil = new Utensil("plastico-aderente", "https//:firebase-fosforos.jpg", true);

            Activity activity = new Activity(ID_NOTIFICATION, date_now, "null", false, 1, member, utensil);
            boolean exists = false;

            if(!(activityList.isEmpty())){
                for(Activity notification: activityList) {
                    if(notification.getType() == 1) {
                        if(notification.getUtensil().getName().equals(utensil.getName())) {
                            exists = true;
                        }
                    }
                }
            }
            if(exists) {
                Toast.makeText(getContext(), "Já foi pedido", Toast.LENGTH_SHORT).show();
            }
            else {
                reference_activity.child(String.valueOf(ID_NOTIFICATION)).setValue(activity).addOnCompleteListener(task1 -> Toast.makeText(root.getContext(), "Notificação enviada", Toast.LENGTH_SHORT).show());
            }
        });

        btn_bath_detergent.setOnClickListener(v -> {
            calendar = Calendar.getInstance();
            simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            String date_now = simpleDateFormat.format(calendar.getTime());

            Member member = new Member("Marina", "marina@gmail.com", "123456", "uidchsdchehefh", 1);
            Utensil utensil = new Utensil("detergente-limpeza", "https//:firebase-fosforos.jpg", true);

            Activity activity = new Activity(ID_NOTIFICATION, date_now, "null", false, 1, member, utensil);
            boolean exists = false;

            if(!(activityList.isEmpty())){
                for(Activity notification: activityList) {
                    if(notification.getType() == 1) {
                        if(notification.getUtensil().getName().equals(utensil.getName())) {
                            exists = true;
                        }
                    }
                }
            }
            if(exists) {
                Toast.makeText(getContext(), "Já foi pedido", Toast.LENGTH_SHORT).show();
            }
            else {
                reference_activity.child(String.valueOf(ID_NOTIFICATION)).setValue(activity).addOnCompleteListener(task1 -> Toast.makeText(root.getContext(), "Notificação enviada", Toast.LENGTH_SHORT).show());
            }
        });

        btn_liquid_soap.setOnClickListener(v -> {
            calendar = Calendar.getInstance();
            simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            String date_now = simpleDateFormat.format(calendar.getTime());

            Member member = new Member("Marina", "marina@gmail.com", "123456", "uidchsdchehefh", 1);
            Utensil utensil = new Utensil("sabao-liquido", "https//:firebase-fosforos.jpg", true);

            Activity activity = new Activity(ID_NOTIFICATION, date_now, "null", false, 1, member, utensil);
            boolean exists = false;

            if(!(activityList.isEmpty())){
                for(Activity notification: activityList) {
                    if(notification.getType() == 1) {
                        if(notification.getUtensil().getName().equals(utensil.getName())) {
                            exists = true;
                        }
                    }
                }
            }
            if(exists) {
                Toast.makeText(getContext(), "Já foi pedido", Toast.LENGTH_SHORT).show();
            }
            else {
                reference_activity.child(String.valueOf(ID_NOTIFICATION)).setValue(activity).addOnCompleteListener(task1 -> Toast.makeText(root.getContext(), "Notificação enviada", Toast.LENGTH_SHORT).show());
            }
        });

        return root;
    }

    public void inicializeComponents(View root) {
        btn_napkin = root.findViewById(R.id.btn_request_napkin);
        btn_matches = root.findViewById(R.id.btn_request_matches);
        btn_dish_soap = root.findViewById(R.id.btn_request_dish_soap);
        btn_sponge = root.findViewById(R.id.btn_request_sponge);
        btn_scrub =  root.findViewById(R.id.btn_request_scrub);
        btn_salt=  root.findViewById(R.id.btn_request_salt);
        btn_trashbag =  root.findViewById(R.id.btn_request_trashbag);
        btn_aluminium_foil =  root.findViewById(R.id.btn_request_aluminiun_foil);
        btn_plastic_foil =  root.findViewById(R.id.btn_request_plastic_foil);
        btn_bath_detergent =  root.findViewById(R.id.btn_request_bathroom_detergent);
        btn_liquid_soap = root.findViewById(R.id.btn_request_liquid_soap);

        card_napkin = root.findViewById(R.id.card_napkin);
        card_matches = root.findViewById(R.id.card_matches);
        card_dish_soap = root.findViewById(R.id.card_dish_soap);
        card_sponge = root.findViewById(R.id.card_sponge);
        card_scrub =  root.findViewById(R.id.card_scrub);
        card_salt=  root.findViewById(R.id.card_salt);
        card_trashbag =  root.findViewById(R.id.card_trashbag);
        card_aluminium_foil =  root.findViewById(R.id.card_aluminium_foil);
        card_plastic_foil =  root.findViewById(R.id.card_plastic_foil);
        card_bath_detergent =  root.findViewById(R.id.card_bath_detergent);
        card_liquid_soap = root.findViewById(R.id.card_liquid_soap);

        int nightModeFlags = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if(nightModeFlags == Configuration.UI_MODE_NIGHT_YES) {

            card_napkin.setCardBackgroundColor(0);
            card_matches.setCardBackgroundColor(0);
            card_dish_soap.setCardBackgroundColor(0);
            card_sponge.setCardBackgroundColor(0);
            card_scrub.setCardBackgroundColor(0);
            card_salt.setCardBackgroundColor(0);
            card_trashbag.setCardBackgroundColor(0);
            card_aluminium_foil.setCardBackgroundColor(0);
            card_plastic_foil.setCardBackgroundColor(0);
            card_bath_detergent.setCardBackgroundColor(0);
            card_liquid_soap.setCardBackgroundColor(0);
        }
    }

}