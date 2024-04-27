package com.isaura.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.isaura.R;
import com.isaura.model.AllActivities;
import com.isaura.model.Notification;

import java.util.List;

public class AllActivitiesAdapter extends RecyclerView.Adapter<AllActivitiesAdapter.AllActivitiesHolder> {

    private Context context;
    private List<Notification> allActivitiesArrayList;

    public AllActivitiesAdapter(Context context, List<Notification> allActivities) {
        this.context = context;
        this.allActivitiesArrayList = allActivities;
    }


    @NonNull
    @Override
    public AllActivitiesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_item, parent, false);
        return new AllActivitiesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllActivitiesHolder holder, int position) {
        Notification notification = allActivitiesArrayList.get(position);

        if (notification.getType() == 1) {
            Glide.with(holder.img_user_profile.getContext()).load(R.drawable.ic_user_hilton).placeholder(R.drawable.ic_user_hilton).error(R.drawable.ic_launcher_background).into(holder.img_user_profile);
            Glide.with(holder.img_item.getContext()).load(R.drawable.ic_liquid_soap).placeholder(R.drawable.ic_liquid_soap).error(R.drawable.ic_launcher_background).into(holder.img_item);
            holder.txt_username.setText(notification.getRequestUtensil().getMember().getName());
            holder.txt_task_description.setText("fez reposição");

        } else {
            Glide.with(holder.img_user_profile.getContext()).load(R.drawable.ic_user_hilton).placeholder(R.drawable.ic_user_hilton).error(R.drawable.ic_launcher_background).into(holder.img_user_profile);
            Glide.with(holder.img_item.getContext()).load(R.drawable.ic_kitchen).placeholder(R.drawable.ic_kitchen).error(R.drawable.ic_launcher_background).into(holder.img_item);
            holder.txt_username.setText(notification.getCleaning().getMember().getName());
            holder.txt_task_description.setText("limpou a");
            Glide.with(holder.img_date_activity.getContext()).load(R.drawable.ic_to_do).placeholder(R.drawable.ic_to_do).error(R.drawable.ic_launcher_background).into(holder.img_date_activity);
        }
        Glide.with(holder.img_date_activity.getContext()).load(R.drawable.icon_calendar).placeholder(R.drawable.icon_calendar).error(R.drawable.ic_launcher_background).into(holder.img_date_activity);
        holder.txt_date_activity.setText(notification.getDate_done());
        holder.card_item_activity.setCardBackgroundColor(context.getResources().getColor(R.color.pastel_purple));
    }

    @Override
    public int getItemCount() {
        return allActivitiesArrayList.size();
    }

    class AllActivitiesHolder extends RecyclerView.ViewHolder {
        ImageView img_user_profile, img_item, img_date_activity;
        TextView txt_username, txt_task_description, txt_date_activity;
        CardView card_item_activity;

        public AllActivitiesHolder(@NonNull View itemView) {
            super(itemView);
            img_user_profile = itemView.findViewById(R.id.img_user_profile_activity);
            img_date_activity = itemView.findViewById(R.id.img_date_activity);
            img_item = itemView.findViewById(R.id.img_item_activity);
            txt_username = itemView.findViewById(R.id.txt_username_activity);
            txt_task_description = itemView.findViewById(R.id.txt_task_description_activity);
            txt_date_activity = itemView.findViewById(R.id.txt_date_activity);
            card_item_activity = itemView.findViewById(R.id.card_item_activity);

        }
    }
}
