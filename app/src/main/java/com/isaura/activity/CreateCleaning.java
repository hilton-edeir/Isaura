package com.isaura.activity;

import android.animation.LayoutTransition;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.isaura.R;
import com.isaura.model.Member;
import com.isaura.model.Place;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class CreateCleaning extends AppCompatActivity {
    LinearLayout layout_empty_house_division, layout_fld_name_house_division, layout_chip_group, layout_hidden_show;
    Button btn_add_house_division, btn_show_fld_to_add_house, btn_cancel_fld_to_add_house, btn_create_list;
    TextInputLayout lyt_name_house_division;
    TextInputEditText fld_name_house_division;
    TextView txt_house_division_selected, txt_cleaning_list;
    ChipGroup chip_group_name_house_division, chip_group_member_for_list;
    DatabaseReference reference_place, reference_member;
    List<Place> placeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_create_cleaning);
        Objects.requireNonNull(getSupportActionBar()).hide();
        inicialize_components();
        layout_fld_name_house_division.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);

        reference_place = FirebaseDatabase.getInstance().getReference("place");
        reference_member = FirebaseDatabase.getInstance().getReference("member");

        reference_member.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    for(DataSnapshot member: snapshot.getChildren()) {
                        Member member1 = member.getValue(Member.class);
                        Chip chip = (Chip) LayoutInflater.from(CreateCleaning.this).inflate(R.layout.chip_member_list, null);
                        Random random = new Random();
                        assert member1 != null;
                        String[] first_name = member1.getName().split(" ", 2);
                        chip.setText(first_name[0]);
                        chip.setTextAppearanceResource(R.style.chipText);
                        chip.setId(random.nextInt());
                        chip_group_member_for_list.addView(chip);
                    }
                }
                else {
                    System.out.println("member reference empty");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println(error);
            }
        });

        reference_place.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    chip_group_name_house_division.removeAllViews();
                    layout_empty_house_division.setVisibility(View.GONE);
                    placeList.clear();
                    for(DataSnapshot place: snapshot.getChildren()){
                        Place place1 = place.getValue(Place.class);
                        placeList.add(place1);

                        Chip chip = (Chip) LayoutInflater.from(CreateCleaning.this).inflate(R.layout.chip_house_division, null);
                        Random random = new Random();
                        assert place1 != null;
                        chip.setText(place1.getName());
                        chip.setTextAppearanceResource(R.style.chipText);

                        if(place1.getName().equals("cozinha")) {
                            chip.setChipIcon(getResources().getDrawable(R.drawable.ic_kitchen));
                        }
                        else if(place1.getName().equals("sala")) {
                            chip.setChipIcon(getResources().getDrawable(R.drawable.ic_living_room));
                        }
                        else if(place1.getName().equals("casa de banho")) {
                            chip.setChipIcon(getResources().getDrawable(R.drawable.ic_bathroom));
                        }
                        else if(place1.getName().equals("lixo")) {
                            chip.setChipIcon(getResources().getDrawable(R.drawable.ic_trash));
                        }
                        else if(place1.getName().equals("garagem")) {
                            chip.setChipIcon(getResources().getDrawable(R.drawable.ic_garage));
                        }
                        else if(place1.getName().equals("quarto")) {
                            chip.setChipIcon(getResources().getDrawable(R.drawable.ic_bedroom));
                        }
                        else if(place1.getName().equals("escritório")) {
                            chip.setChipIcon(getResources().getDrawable(R.drawable.ic_office));
                        }
                        chip.setId(random.nextInt());
                        chip_group_name_house_division.addView(chip);

                        chip.setOnCloseIconClickListener(v -> {
                            reference_place.child(place1.getName()).removeValue();
                            chip_group_name_house_division.removeView(chip);
                            placeList.clear();
                        });
                    }
                    layout_chip_group.setVisibility(View.VISIBLE);
                    layout_empty_house_division.setVisibility(View.GONE);
                }
                else {
                    txt_house_division_selected.setText("Escolha o cômodo acima");
                    txt_cleaning_list.setText(null);
                    chip_group_member_for_list.clearCheck();
                    placeList.clear();
                    layout_chip_group.setVisibility(View.GONE);
                    layout_empty_house_division.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println(error);
            }
        });

        chip_group_name_house_division.setOnCheckedStateChangeListener((chipGroup, list) -> {
            if(list.isEmpty()) {
                txt_house_division_selected.setText("Escolha o cômodo acima");
            }
            else {
                StringBuilder stringBuilder = new StringBuilder();
                for(int i: list) {
                    Chip chip = findViewById(i);
                    stringBuilder.append(", ").append(chip.getText());
                }
                txt_house_division_selected.setText(stringBuilder.toString().replaceFirst(", ", ""));
            }
        });

        chip_group_member_for_list.setOnCheckedStateChangeListener((chipGroup, list) -> {
            if(placeList.isEmpty()) {
                chipGroup.clearCheck();
                Toast.makeText(CreateCleaning.this, "Escolha o cômodo", Toast.LENGTH_SHORT).show();
            }
            else {
                if(list.isEmpty()) {
                    txt_cleaning_list.setText("");
                }
                else {
                    StringBuilder stringBuilder = new StringBuilder();
                    for(int i: list) {
                        Chip chip = findViewById(i);
                        stringBuilder.append(" » ").append(chip.getText());
                    }
                    txt_cleaning_list.setText(stringBuilder.toString().replaceFirst(" » ", ""));
                }
            }
        });

        btn_show_fld_to_add_house.setOnClickListener(v -> {
            btn_show_fld_to_add_house.setVisibility(View.GONE);
            btn_cancel_fld_to_add_house.setVisibility(View.VISIBLE);
            btn_add_house_division.setVisibility(View.VISIBLE);
            expand_add_division_card();
        });

        btn_cancel_fld_to_add_house.setOnClickListener(v -> {
            btn_show_fld_to_add_house.setVisibility(View.VISIBLE);
            btn_cancel_fld_to_add_house.setVisibility(View.GONE);
            btn_add_house_division.setVisibility(View.GONE);
            fld_name_house_division.setText(null);
            lyt_name_house_division.setHelperText(null);
            expand_add_division_card();
        });

        btn_add_house_division.setOnClickListener(v -> {
            String house_division = Objects.requireNonNull(fld_name_house_division.getText()).toString().toLowerCase().trim();
            if(house_division.isEmpty()) {
                lyt_name_house_division.setHelperText("Insira o nome do cômodo");
            }
            else if(house_division.contains(".") || house_division.contains("#") || house_division.contains("$") ||
                    house_division.contains("[") || house_division.contains("]")) {
                lyt_name_house_division.setHelperText("Não podes usar . # $ [ ]");
            }
            else {
                lyt_name_house_division.setHelperText(null);

                if(placeList.isEmpty()) {
                    Place newPlace = new Place(house_division);
                    reference_place.child(house_division).setValue(newPlace).addOnCompleteListener(task -> {
                        Toast.makeText(CreateCleaning.this, "Cômodo registado", Toast.LENGTH_SHORT).show();
                        fld_name_house_division.setText(null);
                        lyt_name_house_division.setHelperText(null);
                        btn_show_fld_to_add_house.setVisibility(View.VISIBLE);
                        btn_cancel_fld_to_add_house.setVisibility(View.GONE);
                        btn_add_house_division.setVisibility(View.GONE);
                        expand_add_division_card();
                    }).addOnFailureListener(System.out::println);
                }
                else {
                    boolean exists = false;
                    for(Place place: placeList) {
                        if (place.getName().equals(house_division)) {
                            exists = true;
                        }
                    }
                    if(exists) {
                        lyt_name_house_division.setHelperText("Este cômodo já está registado");
                    }
                    else {
                        Place newPlace = new Place(house_division);
                        reference_place.child(house_division).setValue(newPlace).addOnCompleteListener(task -> {
                            Toast.makeText(CreateCleaning.this, "Cômodo registado", Toast.LENGTH_SHORT).show();
                            fld_name_house_division.setText(null);
                            lyt_name_house_division.setHelperText(null);
                            btn_show_fld_to_add_house.setVisibility(View.VISIBLE);
                            btn_cancel_fld_to_add_house.setVisibility(View.GONE);
                            btn_add_house_division.setVisibility(View.GONE);
                            expand_add_division_card();
                        }).addOnFailureListener(System.out::println);
                    }
                }
            }
        });

        btn_create_list.setOnClickListener(v -> {
            txt_cleaning_list.getText();
        });
    }

    private void inicialize_components() {
        layout_chip_group = findViewById(R.id.layout_chip_group_house_division);
        layout_empty_house_division = findViewById(R.id.layout_empty_house_division);
        layout_fld_name_house_division = findViewById(R.id.layout_fld_name_house_division);
        layout_hidden_show = findViewById(R.id.layout_hidden_show);
        btn_add_house_division = findViewById(R.id.btn_add_house_division);
        btn_show_fld_to_add_house = findViewById(R.id.btn_show_fld_to_add_house);
        btn_create_list = findViewById(R.id.btn_create_list);
        btn_cancel_fld_to_add_house = findViewById(R.id.btn_cancel_fld_to_add_house);
        lyt_name_house_division = findViewById(R.id.lyt_name_house_division);
        fld_name_house_division = findViewById(R.id.fld_name_house_division);
        txt_house_division_selected = findViewById(R.id.txt_house_division_selected);
        txt_cleaning_list = findViewById(R.id.txt_cleaning_list);
        chip_group_name_house_division = findViewById(R.id.chip_group_name_house_division);
        chip_group_member_for_list = findViewById(R.id.chip_group_member_for_list);
    }

    public void expand_add_division_card() {
        int visib = (layout_hidden_show.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;
        TransitionManager.beginDelayedTransition(layout_fld_name_house_division, new AutoTransition());
        layout_hidden_show.setVisibility(visib);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int focus = (fld_name_house_division.getFocusable() == View.FOCUSABLE)? View.FOCUSABLE: View.NOT_FOCUSABLE;
            fld_name_house_division.setFocusable(focus);
        }

    }
}