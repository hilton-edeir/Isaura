package com.isaura.ui.cleaning;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.Configuration;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.isaura.R;
import com.isaura.model.Place;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CleaningAdapter extends RecyclerView.Adapter<CleaningAdapter.MyViewHolder> {

    private final Context context;
    private final List<Place> placeList;
    private SelectCleaningListener listener;


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

        holder.layout.setOnClickListener(v -> {
            int visib = (holder.detailsText.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;
            TransitionManager.beginDelayedTransition(holder.layout, new AutoTransition());
            holder.detailsText.setVisibility(visib);
        });

    }

    @Override
    public int getItemCount() {
        return placeList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        LinearLayout layout;
        CardView card_item_cleaning;
        ImageView img_place_to_clean, img_user_cleaning;
        TextView detailsText;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            card_item_cleaning = itemView.findViewById(R.id.card_item_cleaning);
            img_place_to_clean = itemView.findViewById(R.id.img_place_to_clean);
            img_user_cleaning = itemView.findViewById(R.id.img_user_cleaning);
            detailsText = itemView.findViewById(R.id.detailsText);
            layout = itemView.findViewById(R.id.layout);

        }
    }

}