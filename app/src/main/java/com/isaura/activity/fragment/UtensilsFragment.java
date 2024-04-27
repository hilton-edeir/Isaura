package com.isaura.activity.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.isaura.R;
import com.isaura.activity.adapter.NotificationAdapter;
import com.isaura.model.Member;
import com.isaura.model.Notification;
import com.isaura.model.RequestUtensil;
import com.isaura.model.Utensil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
public class UtensilsFragment extends Fragment {

    MaterialCardView btn_napkin, btn_matches, btn_dish_soap, btn_sponge, btn_scrub, btn_salt,
            btn_trashbag, btn_aluminium_foil, btn_plastic_foil, btn_bath_detergent, btn_liquid_soap,
            card_napkin, card_matches, card_dish_soap, card_sponge, card_scrub, card_salt, card_trashbag,
            card_aluminium_foil, card_plastic_foil, card_bath_detergent, card_liquid_soap;
    FirebaseDatabase database;
    DatabaseReference reference_notification, databaseReference;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.utensils_fragment, container, false);
        inicializeComponents(root);

        database = FirebaseDatabase.getInstance();
        reference_notification = database.getReference("notification");
        databaseReference = database.getReference();

        btn_napkin.setOnClickListener(v -> {
            calendar = Calendar.getInstance();
            simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            String date_now = simpleDateFormat.format(calendar.getTime());
            String id_notification = reference_notification.push().getKey();
            assert id_notification != null;

            Member member = new Member("Ruténia", "marina@gmail.com", "123456", "uidchsdchehefh");
            Utensil utensil = new Utensil("guardanapos", "https//:firebase-guadanapos.jpg", true);
            RequestUtensil requestUtensil = new RequestUtensil(date_now, utensil, member);
            Notification notification = new Notification(date_now, "null", false, 1, requestUtensil);

            reference_notification.child(utensil.getName()).get().addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    if(task.getResult().exists()) {
                        Toast.makeText(root.getContext(), "Já foi pedido uma vez", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        reference_notification.child(utensil.getName()).setValue(notification).addOnCompleteListener(task1 -> Toast.makeText(root.getContext(), "Notificação enviada", Toast.LENGTH_SHORT).show());
                    }
                }
            });

        });

        btn_matches.setOnClickListener(v -> {
            calendar = Calendar.getInstance();
            simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            String date_now = simpleDateFormat.format(calendar.getTime());

            Member member = new Member("Carline", "carline@gmail.com", "123456", "uidchsdchehefh");
            Utensil utensil = new Utensil("fosforos", "https//:firebase-fosforos.jpg", true);
            RequestUtensil requestUtensil = new RequestUtensil(date_now, utensil, member);
            Notification notification = new Notification(date_now, "null", false, 1, requestUtensil);

            reference_notification.child(utensil.getName()).get().addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    if(task.getResult().exists()) {
                        Toast.makeText(root.getContext(), "Já foi pedido uma vez", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        reference_notification.child(utensil.getName()).setValue(notification).addOnCompleteListener(task1 -> Toast.makeText(root.getContext(), "Notificação enviada", Toast.LENGTH_SHORT).show());
                    }
                }
            });
        });

        btn_dish_soap.setOnClickListener(v -> {
            calendar = Calendar.getInstance();
            simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            String date_now = simpleDateFormat.format(calendar.getTime());

            Member member = new Member("Hilton", "hilton@gmail.com", "123456", "uidchsdchehefh");
            Utensil utensil = new Utensil("detergente-loica", "https//:firebase-fosforos.jpg", true);
            RequestUtensil requestUtensil = new RequestUtensil(date_now, utensil, member);
            Notification notification = new Notification(date_now, "null", false, 1, requestUtensil);

            reference_notification.child(utensil.getName()).get().addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    if(task.getResult().exists()) {
                        Toast.makeText(root.getContext(), "Já foi pedido uma vez", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        reference_notification.child(utensil.getName()).setValue(notification).addOnCompleteListener(task1 -> Toast.makeText(root.getContext(), "Notificação enviada", Toast.LENGTH_SHORT).show());
                    }
                }
            });

        });

        btn_sponge.setOnClickListener(v -> {
            calendar = Calendar.getInstance();
            simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            String date_now = simpleDateFormat.format(calendar.getTime());

            Member member = new Member("Marina", "marina@gmail.com", "123456", "uidchsdchehefh");
            Utensil utensil = new Utensil("esponja", "https//:firebase-fosforos.jpg", true);
            RequestUtensil requestUtensil = new RequestUtensil(date_now, utensil, member);
            Notification notification = new Notification(date_now, "null", false, 1,requestUtensil);

            reference_notification.child(utensil.getName()).get().addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    if(task.getResult().exists()) {
                        Toast.makeText(root.getContext(), "Já foi pedido uma vez", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        reference_notification.child(utensil.getName()).setValue(notification).addOnCompleteListener(task1 -> Toast.makeText(root.getContext(), "Notificação enviada", Toast.LENGTH_SHORT).show());
                    }
                }
            });
        });

        btn_scrub.setOnClickListener(v -> {
            calendar = Calendar.getInstance();
            simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            String date_now = simpleDateFormat.format(calendar.getTime());

            Member member = new Member("Marina", "marina@gmail.com", "123456", "uidchsdchehefh");
            Utensil utensil = new Utensil("fergao", "https//:firebase-fosforos.jpg", true);
            RequestUtensil requestUtensil = new RequestUtensil(date_now, utensil, member);
            Notification notification = new Notification(date_now, "null", false, 1, requestUtensil);

            reference_notification.child(utensil.getName()).get().addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    if(task.getResult().exists()) {
                        Toast.makeText(root.getContext(), "Já foi pedido uma vez", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        reference_notification.child(utensil.getName()).setValue(notification).addOnCompleteListener(task1 -> Toast.makeText(root.getContext(), "Notificação enviada", Toast.LENGTH_SHORT).show());
                    }
                }
            });
        });

        btn_salt.setOnClickListener(v -> {
            calendar = Calendar.getInstance();
            simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            String date_now = simpleDateFormat.format(calendar.getTime());

            Member member = new Member("Maria", "maria@gmail.com", "123456", "uidchsdchehefh");
            Utensil utensil = new Utensil("sal", "https//:firebase-fosforos.jpg", true);
            RequestUtensil requestUtensil = new RequestUtensil(date_now, utensil, member);
            Notification notification = new Notification(date_now, "null", false, 1, requestUtensil);

            reference_notification.child(utensil.getName()).get().addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    if(task.getResult().exists()) {
                        Toast.makeText(root.getContext(), "Já foi pedido uma vez", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        reference_notification.child(utensil.getName()).setValue(notification).addOnCompleteListener(task1 -> Toast.makeText(root.getContext(), "Notificação enviada", Toast.LENGTH_SHORT).show());
                    }
                }
            });
        });

        btn_trashbag.setOnClickListener(v -> {
            calendar = Calendar.getInstance();
            simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            String date_now = simpleDateFormat.format(calendar.getTime());

            Member member = new Member("Hilton", "hilton@gmail.com", "123456", "uidchsdchehefh");
            Utensil utensil = new Utensil("saco-lixo", "https//:firebase-fosforos.jpg", true);
            RequestUtensil requestUtensil = new RequestUtensil(date_now, utensil, member);
            Notification notification = new Notification(date_now, "null", false, 1, requestUtensil);

            reference_notification.child(utensil.getName()).get().addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    if(task.getResult().exists()) {
                        Toast.makeText(root.getContext(), "Já foi pedido uma vez", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        reference_notification.child(utensil.getName()).setValue(notification).addOnCompleteListener(task1 -> Toast.makeText(root.getContext(), "Notificação enviada", Toast.LENGTH_SHORT).show());
                    }
                }
            });

        });

        btn_aluminium_foil.setOnClickListener(v -> {
            calendar = Calendar.getInstance();
            simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            String date_now = simpleDateFormat.format(calendar.getTime());

            Member member = new Member("Hilton", "hilton@gmail.com", "123456", "uidchsdchehefh");
            Utensil utensil = new Utensil("rolo-aluminio", "https//:firebase-fosforos.jpg", true);
            RequestUtensil requestUtensil = new RequestUtensil(date_now, utensil, member);
            Notification notification = new Notification(date_now, "null", false, 1, requestUtensil);

            reference_notification.child(utensil.getName()).get().addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    if(task.getResult().exists()) {
                        Toast.makeText(root.getContext(), "Já foi pedido uma vez", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        reference_notification.child(utensil.getName()).setValue(notification).addOnCompleteListener(task1 -> Toast.makeText(root.getContext(), "Notificação enviada", Toast.LENGTH_SHORT).show());
                    }
                }
            });
        });

        btn_plastic_foil.setOnClickListener(v -> {
            calendar = Calendar.getInstance();
            simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            String date_now = simpleDateFormat.format(calendar.getTime());

            Member member = new Member("Hilton", "hilton@gmail.com", "123456", "uidchsdchehefh");
            Utensil utensil = new Utensil("plastico-aderente", "https//:firebase-fosforos.jpg", true);
            RequestUtensil requestUtensil = new RequestUtensil(date_now, utensil, member);
            Notification notification = new Notification(date_now, "null", false, 1, requestUtensil);

            reference_notification.child(utensil.getName()).get().addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    if(task.getResult().exists()) {
                        Toast.makeText(root.getContext(), "Já foi pedido uma vez", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        reference_notification.child(utensil.getName()).setValue(notification).addOnCompleteListener(task1 -> Toast.makeText(root.getContext(), "Notificação enviada", Toast.LENGTH_SHORT).show());
                    }
                }
            });
        });

        btn_bath_detergent.setOnClickListener(v -> {
            calendar = Calendar.getInstance();
            simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            String date_now = simpleDateFormat.format(calendar.getTime());

            Member member = new Member("Marina", "marina@gmail.com", "123456", "uidchsdchehefh");
            Utensil utensil = new Utensil("detergente-limpeza", "https//:firebase-fosforos.jpg", true);
            RequestUtensil requestUtensil = new RequestUtensil(date_now, utensil, member);
            Notification notification = new Notification(date_now, "null", false, 1, requestUtensil);

            reference_notification.child(utensil.getName()).get().addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    if(task.getResult().exists()) {
                        Toast.makeText(root.getContext(), "Já foi pedido uma vez", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        reference_notification.child(utensil.getName()).setValue(notification).addOnCompleteListener(task1 -> Toast.makeText(root.getContext(), "Notificação enviada", Toast.LENGTH_SHORT).show());
                    }
                }
            });
        });

        btn_liquid_soap.setOnClickListener(v -> {
            calendar = Calendar.getInstance();
            simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            String date_now = simpleDateFormat.format(calendar.getTime());

            Member member = new Member("Marina", "marina@gmail.com", "123456", "uidchsdchehefh");
            Utensil utensil = new Utensil("sabao-liquido", "https//:firebase-fosforos.jpg", true);
            RequestUtensil requestUtensil = new RequestUtensil(date_now, utensil, member);
            Notification notification = new Notification(date_now, "null", false, 1, requestUtensil);

            reference_notification.child(utensil.getName()).get().addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    if(task.getResult().exists()) {
                        Toast.makeText(root.getContext(), "Já foi pedido uma vez", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        reference_notification.child(utensil.getName()).setValue(notification).addOnCompleteListener(task1 -> Toast.makeText(root.getContext(), "Notificação enviada", Toast.LENGTH_SHORT).show());
                    }
                }
            });
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

     /*Fragment cleaningScheduleFragment = new CleaningFragment();
       FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
       fm.replace(R.id.nav_host_fragment_act_home, cleaningScheduleFragment).commit();
     */
}