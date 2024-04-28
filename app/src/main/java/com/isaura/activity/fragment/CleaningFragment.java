package com.isaura.activity.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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

        inicialize_components(root);
        original_cleaning_list_order_update();

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
                        System.out.println(member_name);
                    }
                }
                else {
                    System.out.println("Original list is empty");
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
                        System.out.println(member_name);
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

        btn_test.setOnClickListener(v -> {

            calendar = Calendar.getInstance();
            simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String date_now = simpleDateFormat.format(calendar.getTime());

            List<String> rotated_list;

            if(last_rotated_list.isEmpty()) {
                rotated_list = LinkedList.schecule(original_list_order);
            }
            else {
                rotated_list = LinkedList.schecule(last_rotated_list);
            }
            System.out.println(rotated_list);

            reference_last_rotated_list_order.removeValue();
            reference_last_rotated_list_order.setValue(rotated_list).addOnCompleteListener(task -> System.out.println(task));
            original_list_order.clear();
            last_rotated_list.clear();

            //Activity activity = new Activity(Integer.toString((int) ID_NOTIFICATION), 2, date_now, false, "", rotated_list.get(0), "kitchen" );
            //reference_notification.child("test-new-notification").child(Integer.toString((int) ID_NOTIFICATION + 1)).setValue(activity).addOnCompleteListener(task -> System.out.println(task));
        });

        btn_see_calendar.setOnClickListener(v -> {
            MaterialDatePicker<Long> materialDatePicker = MaterialDatePicker.Builder.datePicker().setTitleText("Calendário").setSelection(MaterialDatePicker.todayInUtcMilliseconds()).build();
            materialDatePicker.addOnPositiveButtonClickListener(aLong -> {

                String date_selected = new SimpleDateFormat("dd-MM-yyy", Locale.getDefault()).format(new Date(aLong));
                txt_date_selected.setText(MessageFormat.format("Data: {0}", date_selected));

                calendar = Calendar.getInstance();
                simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                String date_now = simpleDateFormat.format(calendar.getTime());

                String[] selected_date_splited = date_selected.split("-", 3);
                String[] date_now_splited = date_now.split("-", 3);

                LocalDate startDate = LocalDate.of(Integer.parseInt(date_now_splited[2]), Integer.parseInt(date_now_splited[1]), Integer.parseInt(date_now_splited[0]));
                LocalDate endDate = LocalDate.of(Integer.parseInt(selected_date_splited[2]), Integer.parseInt(selected_date_splited[1]), Integer.parseInt(selected_date_splited[0]));

                NUMBER_OF_SATURDAYS = countSaturdays(startDate, endDate);

            });
            materialDatePicker.show(getActivity().getSupportFragmentManager(), "tag");
        });
        return root;
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


    public void original_cleaning_list_order_update() {
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
                }
                else {
                    System.out.println("There's no user");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println(error);
            }
        });

    }

    private static long countSaturdays(LocalDate startDate, LocalDate endDate)
    {
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