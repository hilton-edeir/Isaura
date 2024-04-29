package com.isaura.activity.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.isaura.R;
import com.isaura.model.LinkedList;
import com.isaura.model.Member;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CleaningFragment extends Fragment {
    Button btn_test;
    MaterialCardView btn_see_calendar, card_task_living_room, card_task_kitchen, card_task_bathroom, card_task_trash, card_task_free;
    TextView txt_date_selected, txt_username_kitchen_cleaning, txt_username_bathroom_cleaning, txt_username_livingroom_cleaning, txt_username_trash_cleaning, txt_username_free_cleaning;
    ImageView img_user_kitchen_cleaning, img_user_bathroom_cleaning, img_user_livingroom_cleaning, img_user_trash_cleaning, img_user_free_cleaning;
    FirebaseDatabase database;
    DatabaseReference reference_original_list_order, reference_last_rotated_list_order, reference_cleaning_distribution, reference_member, reference_activity;
    long ID_NOTIFICATION = 0;
    FirebaseAuth mAuth;
    Calendar calendar;
    long NUMBER_OF_SATURDAYS = 0;
    SimpleDateFormat simpleDateFormat;
    List<String> original_list_order = new ArrayList<>();
    List<String> last_rotated_list = new ArrayList<>();
    List<String> rotated_list = new ArrayList<>();
    List<Member> member_list = new ArrayList<>();
    List<String> original_list_order_updated;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.cleaning_fragment, container, false);

        database = FirebaseDatabase.getInstance();
        reference_cleaning_distribution = database.getReference("cleaning-distribution");
        reference_original_list_order = database.getReference("original-list-order-cleaning");
        reference_last_rotated_list_order = database.getReference("last-rotated-list-order-cleaning");
        reference_member = database.getReference("member");
        reference_activity = database.getReference("activity");

        inicialize_original_list_order();
        inicialize_components(root);

        reference_activity.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    ID_NOTIFICATION = snapshot.getChildrenCount();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        reference_original_list_order.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    for(DataSnapshot member: snapshot.getChildren()){
                        String member_name = member.getValue(String.class);
                        original_list_order.add(member_name);
                    }
                }
                else {
                    System.out.println("original list order is empty");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.printf(error.toString());
            }
        });

        reference_last_rotated_list_order.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    for(DataSnapshot member: snapshot.getChildren()){
                        String member_name = member.getValue(String.class);
                        last_rotated_list.add(member_name);
                    }
                }
                else {
                    System.out.println("Last rotated list is empty");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.printf(error.toString());
            }
        });

        LocalDate nextSaturday = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
        txt_date_selected.setText("Data: " + nextSaturday);

        btn_test.setOnClickListener(v -> {

        });

        btn_see_calendar.setOnClickListener(v -> {
            if(last_rotated_list.isEmpty()) {
                rotated_list = LinkedList.schecule(original_list_order, 1);
            }
            else {
                rotated_list = LinkedList.schecule(last_rotated_list, 1);
            }

            reference_last_rotated_list_order.removeValue();
            reference_last_rotated_list_order.setValue(rotated_list).addOnCompleteListener(task -> System.out.println(task));

            txt_username_kitchen_cleaning.setText(rotated_list.get(0));
            txt_username_bathroom_cleaning.setText(rotated_list.get(1));
            txt_username_livingroom_cleaning.setText(rotated_list.get(2));
            txt_username_trash_cleaning.setText(rotated_list.get(3));
            txt_username_free_cleaning.setText(rotated_list.get(4));

            original_list_order.clear();
            last_rotated_list.clear();
            LocalDate next_nextSaturday = LocalDate.from(nextSaturday).with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
            txt_date_selected.setText("Data: " + next_nextSaturday);
            inicialize_original_list_order();

                /*for(Member member: member_list) {
            if(member.getName().startsWith(rotated_list.get(0))) {
                Glide.with(img_user_kitchen_cleaning.getContext()).load(member_list.get(0).getUrl_image()).placeholder(R.drawable.ic_user_hilton).error(R.drawable.ic_launcher_background).into(img_user_kitchen_cleaning);
            }
            else if(member.getName().startsWith(rotated_list.get(1))) {
                Glide.with(img_user_kitchen_cleaning.getContext()).load(member_list.get(1).getUrl_image()).placeholder(R.drawable.ic_user_hilton).error(R.drawable.ic_launcher_background).into(img_user_kitchen_cleaning);
            }
            else if(member.getName().startsWith(rotated_list.get(2))) {
                Glide.with(img_user_kitchen_cleaning.getContext()).load(member_list.get(2).getUrl_image()).placeholder(R.drawable.ic_user_hilton).error(R.drawable.ic_launcher_background).into(img_user_kitchen_cleaning);
            }
            else if(member.getName().startsWith(rotated_list.get(3))) {
                Glide.with(img_user_kitchen_cleaning.getContext()).load(member_list.get(3).getUrl_image()).placeholder(R.drawable.ic_user_hilton).error(R.drawable.ic_launcher_background).into(img_user_kitchen_cleaning);
            }
            else if(member.getName().startsWith(rotated_list.get(4))) {
                Glide.with(img_user_kitchen_cleaning.getContext()).load(member_list.get(3).getUrl_image()).placeholder(R.drawable.ic_user_hilton).error(R.drawable.ic_launcher_background).into(img_user_kitchen_cleaning);
            }*/

        });
        return root;
    }

    private void inicialize_original_list_order() {
        reference_member.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    original_list_order_updated = Arrays.asList(new String[(int) snapshot.getChildrenCount()]);
                    for(DataSnapshot member: snapshot.getChildren()) {
                        Member member1 = member.getValue(Member.class);
                        if(member1.getAssigned_table_order() != 0) {
                            if(member1.getName().contains(" ")) {
                                String[] temp_name = member1.getName().split(" ",2);
                                member1.setName(temp_name[0]);
                            }
                            if(member1.getAssigned_table_order() != 0) {
                                original_list_order_updated.set(member1.getAssigned_table_order() - 1, member1.getName());
                            }
                        }
                    }
                    reference_original_list_order.setValue(original_list_order_updated).addOnCompleteListener(task1 -> System.out.println("members updated to original list"));

                    if(last_rotated_list.isEmpty()) {
                        txt_username_kitchen_cleaning.setText(original_list_order_updated.get(0));
                        txt_username_bathroom_cleaning.setText(original_list_order_updated.get(1));
                        txt_username_livingroom_cleaning.setText(original_list_order_updated.get(2));
                        txt_username_trash_cleaning.setText(original_list_order_updated.get(3));
                        txt_username_free_cleaning.setText(original_list_order_updated.get(4));
                    }
                    else {
                        txt_username_kitchen_cleaning.setText(last_rotated_list.get(0));
                        txt_username_bathroom_cleaning.setText(last_rotated_list.get(1));
                        txt_username_livingroom_cleaning.setText(last_rotated_list.get(2));
                        txt_username_trash_cleaning.setText(last_rotated_list.get(3));
                        txt_username_free_cleaning.setText(last_rotated_list.get(4));
                    }
                }
                else {
                    System.out.println("The member node is empty");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println(error);
            }
        });
    }

    public void inicialize_components(View root) {
        txt_date_selected = root.findViewById(R.id.txt_date_selected);
        btn_see_calendar = root.findViewById(R.id.btn_see_calendar);
        btn_test= root.findViewById(R.id.btn_test);
        txt_username_kitchen_cleaning = root.findViewById(R.id.txt_username_kitchen_cleaning);
        txt_username_bathroom_cleaning = root.findViewById(R.id.txt_username_bathroom_cleaning);
        txt_username_livingroom_cleaning = root.findViewById(R.id.txt_username_livingroom_cleaning);
        txt_username_trash_cleaning = root.findViewById(R.id.txt_username_trash_cleaning);
        txt_username_free_cleaning = root.findViewById(R.id.txt_username_free_cleaning);
        img_user_kitchen_cleaning = root.findViewById(R.id.img_user_kitchen_cleaning);
        img_user_bathroom_cleaning = root.findViewById(R.id.img_user_bathroom_cleaning);
        img_user_livingroom_cleaning = root.findViewById(R.id.img_user_livingroom_cleaning);
        img_user_trash_cleaning = root.findViewById(R.id.img_user_trash_cleaning);
        img_user_free_cleaning = root.findViewById(R.id.img_user_free_cleaning);

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

    private static long countSaturdays(LocalDate startDate, LocalDate endDate) {
        long count = 0;
        while (!startDate.isAfter(endDate)) {
            if(startDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
                count++;
            }
            startDate = startDate.plusDays(1);
        }
        return count;
    }


}