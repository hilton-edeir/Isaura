package com.isaura.ui.cleaning;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.Configuration;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.isaura.R;
import com.isaura.model.Member;
import com.isaura.model.Place;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;

public class CleaningAdapter extends RecyclerView.Adapter<CleaningAdapter.MyViewHolder> {

    private final Context context;
    private final List<Place> placeList;
    private SelectCleaningListener listener;
    DatabaseReference reference_member = FirebaseDatabase.getInstance().getReference("member");

    public CleaningAdapter(Context context, List<Place> placeList, SelectCleaningListener listener) {
        this.context = context;
        this.placeList = placeList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CleaningAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CleaningAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cleaning_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CleaningAdapter.MyViewHolder holder, int position) {
        Place place = placeList.get(position);
        Picasso.with(context).load(place.getUrl_image()).into(holder.img_place_to_clean);

        int nightModeFlags = context.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if(nightModeFlags == Configuration.UI_MODE_NIGHT_YES) {
            holder.card_item_cleaning.setCardBackgroundColor(0);
        }
        else {
            holder.card_item_cleaning.setCardBackgroundColor(context.getResources().getColor(R.color.pastel_purple));
        }
        holder.card_item_cleaning.setOnClickListener(v -> listener.onItemClicked(placeList.get(position)));

        holder.layout.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);

        holder.card_item_cleaning.setOnClickListener(v -> {
            int visib = (holder.detail_list.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;
            TransitionManager.beginDelayedTransition(holder.layout, new AutoTransition());
            holder.detail_list.setVisibility(visib);
        });

        reference_member.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    for(DataSnapshot member: snapshot.getChildren()) {
                        Member member1 = member.getValue(Member.class);
                        Chip chip = (Chip) LayoutInflater.from(context).inflate(R.layout.chip_member_list, null);
                        Random random = new Random();
                        assert member1 != null;
                        String[] first_name = member1.getName().split(" ", 2);
                        chip.setText(first_name[0]);
                        chip.setTextAppearanceResource(R.style.chipText);
                        chip.setId(random.nextInt());
                        holder.chip_group_member_for_list.addView(chip);
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

        holder.chip_group_member_for_list.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return placeList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layout, detail_list;
        CardView card_item_cleaning;
        ImageView img_place_to_clean, img_user_cleaning;
        ChipGroup chip_group_member_for_list;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            card_item_cleaning = itemView.findViewById(R.id.card_item_cleaning);
            img_place_to_clean = itemView.findViewById(R.id.img_place_to_clean);
            img_user_cleaning = itemView.findViewById(R.id.img_user_cleaning);
            detail_list = itemView.findViewById(R.id.detail_list);
            layout = itemView.findViewById(R.id.layout);
            chip_group_member_for_list = itemView.findViewById(R.id.chip_group_member_for_li);

        }
    }

}