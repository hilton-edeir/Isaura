package com.isaura.activity.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.isaura.R;
import com.isaura.model.Member;
import com.isaura.model.Notification;
import com.isaura.model.Utensil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
public class UtensilsFragment extends Fragment {

    MaterialCardView btn_napkin, btn_matches, btn_dish_soap, btn_sponge, btn_scrub, btn_salt,
            btn_trashbag, btn_aluminium_foil, btn_plastic_foil, btn_bath_detergent, btn_liquid_soap;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.utensils_fragment, container, false);
        iniciatizeComponents(root);

        btn_napkin.setOnClickListener(v -> {
            calendar = Calendar.getInstance();
            simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String date_now = simpleDateFormat.format(calendar.getTime());

            Member member = new Member("Ruténia", "marina@gmail.com", 123456, "uidchsdchehefh");
            Utensil utensil = new Utensil("Guardanapos", "https//:firebase-guadanapos.jpg", true);
            Notification notification = new Notification(utensil.getName(), date_now, false, member.getName());

            database = FirebaseDatabase.getInstance();
            databaseReference = database.getReference("notification");

            databaseReference.child(utensil.getName()).get().addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    if(task.getResult().exists()) {
                        Toast.makeText(root.getContext(), "Já foi pedido uma vez", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        databaseReference.child(utensil.getName()).setValue(notification).addOnCompleteListener(task1 -> Toast.makeText(root.getContext(), "Notificação enviada", Toast.LENGTH_SHORT).show());
                    }
                }
            });

        });

        btn_matches.setOnClickListener(v -> {
            calendar = Calendar.getInstance();
            simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String date_now = simpleDateFormat.format(calendar.getTime());

            Member member = new Member("Carline", "carline@gmail.com", 123456, "uidchsdchehefh");
            Utensil utensil = new Utensil("Fósforos", "https//:firebase-fosforos.jpg", true);
            Notification notification = new Notification(utensil.getName(), date_now, false, member.getName());

            database = FirebaseDatabase.getInstance();
            databaseReference = database.getReference("notification");

            databaseReference.child(utensil.getName()).get().addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    if(task.getResult().exists()) {
                        Toast.makeText(root.getContext(), "Já foi pedido uma vez", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        databaseReference.child(utensil.getName()).setValue(notification).addOnCompleteListener(task1 -> Toast.makeText(root.getContext(), "Notificação enviada", Toast.LENGTH_SHORT).show());
                    }
                }
            });
        });

        btn_dish_soap.setOnClickListener(v -> {
            calendar = Calendar.getInstance();
            simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String date_now = simpleDateFormat.format(calendar.getTime());

            Member member = new Member("Hilton", "hilton@gmail.com", 123456, "uidchsdchehefh");
            Utensil utensil = new Utensil("Detergente Loiça", "https//:firebase-fosforos.jpg", true);
            Notification notification = new Notification(utensil.getName(), date_now, false, member.getName());

            database = FirebaseDatabase.getInstance();
            databaseReference = database.getReference("notification");

            databaseReference.child(utensil.getName()).get().addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    if(task.getResult().exists()) {
                        Toast.makeText(root.getContext(), "Já foi pedido uma vez", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        databaseReference.child(utensil.getName()).setValue(notification).addOnCompleteListener(task1 -> Toast.makeText(root.getContext(), "Notificação enviada", Toast.LENGTH_SHORT).show());
                    }
                }
            });

        });

        btn_sponge.setOnClickListener(v -> {
            calendar = Calendar.getInstance();
            simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String date_now = simpleDateFormat.format(calendar.getTime());

            Member member = new Member("Marina", "marina@gmail.com", 123456, "uidchsdchehefh");
            Utensil utensil = new Utensil("Esponja", "https//:firebase-fosforos.jpg", true);
            Notification notification = new Notification(utensil.getName(), date_now, false, member.getName());

            database = FirebaseDatabase.getInstance();
            databaseReference = database.getReference("notification");

            databaseReference.child(utensil.getName()).get().addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    if(task.getResult().exists()) {
                        Toast.makeText(root.getContext(), "Já foi pedido uma vez", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        databaseReference.child(utensil.getName()).setValue(notification).addOnCompleteListener(task1 -> Toast.makeText(root.getContext(), "Notificação enviada", Toast.LENGTH_SHORT).show());
                    }
                }
            });
        });

        btn_scrub.setOnClickListener(v -> {
            calendar = Calendar.getInstance();
            simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String date_now = simpleDateFormat.format(calendar.getTime());

            Member member = new Member("Marina", "marina@gmail.com", 123456, "uidchsdchehefh");
            Utensil utensil = new Utensil("Fergão", "https//:firebase-fosforos.jpg", true);
            Notification notification = new Notification(utensil.getName(), date_now, false, member.getName());

            database = FirebaseDatabase.getInstance();
            databaseReference = database.getReference("notification");

            databaseReference.child(utensil.getName()).get().addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    if(task.getResult().exists()) {
                        Toast.makeText(root.getContext(), "Já foi pedido uma vez", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        databaseReference.child(utensil.getName()).setValue(notification).addOnCompleteListener(task1 -> Toast.makeText(root.getContext(), "Notificação enviada", Toast.LENGTH_SHORT).show());
                    }
                }
            });
        });

        btn_salt.setOnClickListener(v -> {
            calendar = Calendar.getInstance();
            simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String date_now = simpleDateFormat.format(calendar.getTime());

            Member member = new Member("Maria", "maria@gmail.com", 123456, "uidchsdchehefh");
            Utensil utensil = new Utensil("Sal", "https//:firebase-fosforos.jpg", true);
            Notification notification = new Notification(utensil.getName(), date_now, false, member.getName());

            database = FirebaseDatabase.getInstance();
            databaseReference = database.getReference("notification");

            databaseReference.child(utensil.getName()).get().addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    if(task.getResult().exists()) {
                        Toast.makeText(root.getContext(), "Já foi pedido uma vez", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        databaseReference.child(utensil.getName()).setValue(notification).addOnCompleteListener(task1 -> Toast.makeText(root.getContext(), "Notificação enviada", Toast.LENGTH_SHORT).show());
                    }
                }
            });
        });

        btn_trashbag.setOnClickListener(v -> {
            calendar = Calendar.getInstance();
            simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String date_now = simpleDateFormat.format(calendar.getTime());

            Member member = new Member("Hilton", "hilton@gmail.com", 123456, "uidchsdchehefh");
            Utensil utensil = new Utensil("Saco de lixo", "https//:firebase-fosforos.jpg", true);
            Notification notification = new Notification(utensil.getName(), date_now, false, member.getName());

            database = FirebaseDatabase.getInstance();
            databaseReference = database.getReference("notification");

            databaseReference.child(utensil.getName()).get().addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    if(task.getResult().exists()) {
                        Toast.makeText(root.getContext(), "Já foi pedido uma vez", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        databaseReference.child(utensil.getName()).setValue(notification).addOnCompleteListener(task1 -> Toast.makeText(root.getContext(), "Notificação enviada", Toast.LENGTH_SHORT).show());
                    }
                }
            });

        });

        btn_aluminium_foil.setOnClickListener(v -> {
            calendar = Calendar.getInstance();
            simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String date_now = simpleDateFormat.format(calendar.getTime());

            Member member = new Member("Hilton", "hilton@gmail.com", 123456, "uidchsdchehefh");
            Utensil utensil = new Utensil("Rolo de aluminio", "https//:firebase-fosforos.jpg", true);
            Notification notification = new Notification(utensil.getName(), date_now, false, member.getName());

            database = FirebaseDatabase.getInstance();
            databaseReference = database.getReference("notification");

            databaseReference.child(utensil.getName()).get().addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    if(task.getResult().exists()) {
                        Toast.makeText(root.getContext(), "Já foi pedido uma vez", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        databaseReference.child(utensil.getName()).setValue(notification).addOnCompleteListener(task1 -> Toast.makeText(root.getContext(), "Notificação enviada", Toast.LENGTH_SHORT).show());
                    }
                }
            });
        });

        btn_plastic_foil.setOnClickListener(v -> {
            calendar = Calendar.getInstance();
            simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String date_now = simpleDateFormat.format(calendar.getTime());

            Member member = new Member("Hilton", "hilton@gmail.com", 123456, "uidchsdchehefh");
            Utensil utensil = new Utensil("Plástico aderente", "https//:firebase-fosforos.jpg", true);
            Notification notification = new Notification(utensil.getName(), date_now, false, member.getName());

            database = FirebaseDatabase.getInstance();
            databaseReference = database.getReference("notification");

            databaseReference.child(utensil.getName()).get().addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    if(task.getResult().exists()) {
                        Toast.makeText(root.getContext(), "Já foi pedido uma vez", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        databaseReference.child(utensil.getName()).setValue(notification).addOnCompleteListener(task1 -> Toast.makeText(root.getContext(), "Notificação enviada", Toast.LENGTH_SHORT).show());
                    }
                }
            });
        });

        btn_bath_detergent.setOnClickListener(v -> {
            calendar = Calendar.getInstance();
            simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String date_now = simpleDateFormat.format(calendar.getTime());

            Member member = new Member("Marina", "marina@gmail.com", 123456, "uidchsdchehefh");
            Utensil utensil = new Utensil("Detergente de limpeza", "https//:firebase-fosforos.jpg", true);
            Notification notification = new Notification(utensil.getName(), date_now, false, member.getName());

            database = FirebaseDatabase.getInstance();
            databaseReference = database.getReference("notification");

            databaseReference.child(utensil.getName()).get().addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    if(task.getResult().exists()) {
                        Toast.makeText(root.getContext(), "Já foi pedido uma vez", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        databaseReference.child(utensil.getName()).setValue(notification).addOnCompleteListener(task1 -> Toast.makeText(root.getContext(), "Notificação enviada", Toast.LENGTH_SHORT).show());
                    }
                }
            });
        });

        btn_liquid_soap.setOnClickListener(v -> {
            calendar = Calendar.getInstance();
            simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String date_now = simpleDateFormat.format(calendar.getTime());

            Member member = new Member("Marina", "marina@gmail.com", 123456, "uidchsdchehefh");
            Utensil utensil = new Utensil("Sabão Líquido", "https//:firebase-fosforos.jpg", true);
            Notification notification = new Notification(utensil.getName(), date_now, false, member.getName());

            database = FirebaseDatabase.getInstance();
            databaseReference = database.getReference("notification");

            databaseReference.child(utensil.getName()).get().addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    if(task.getResult().exists()) {
                        Toast.makeText(root.getContext(), "Já foi pedido uma vez", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        databaseReference.child(utensil.getName()).setValue(notification).addOnCompleteListener(task1 -> Toast.makeText(root.getContext(), "Notificação enviada", Toast.LENGTH_SHORT).show());
                    }
                }
            });
        });

        return root;
    }

    public void iniciatizeComponents(View root) {
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
    }

     /*Fragment cleaningScheduleFragment = new CleaningScheduleFragment();
       FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
       fm.replace(R.id.nav_host_fragment_act_home, cleaningScheduleFragment).commit();
     */
}