package com.isaura.activity.adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.isaura.R;
import com.isaura.model.Notification;

import java.util.ArrayList;
import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {

    private final Context context;
    private final List<Notification> notificationList;


    public NotificationAdapter(Context context, List<Notification> notificationList) {
        this.context = context;
        this.notificationList = notificationList;
    }

    @NonNull
    @Override
    public NotificationAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.MyViewHolder holder, int position) {
        Notification notification = notificationList.get(position);
        holder.txt_date.setText(notification.getDate());
        Glide.with(holder.img_task_type.getContext()).load(R.drawable.ic_utensils).placeholder(R.drawable.ic_utensils).error(R.drawable.ic_launcher_background).into(holder.img_task_type);
        Glide.with(holder.img_item_utensil.getContext()).load(R.drawable.ic_liquid_soap).placeholder(R.drawable.ic_liquid_soap).error(R.drawable.ic_launcher_background).into(holder.img_item_utensil);
        Glide.with(holder.img_type_notification.getContext()).load(R.drawable.ic_bell).placeholder(R.drawable.ic_bell).error(R.drawable.ic_launcher_background).into(holder.img_type_notification);
        holder.card_item.setCardBackgroundColor(context.getResources().getColor(R.color.pastel_purple));
    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        CardView card_item;
        ImageView img_task_type, img_item_utensil, img_type_notification;
        TextView txt_task_description, txt_date;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            card_item = itemView.findViewById(R.id.card_item_notification);
            img_task_type = itemView.findViewById(R.id.img_type_task_notification);
            img_item_utensil = itemView.findViewById(R.id.img_item_utensil_notification);
            img_type_notification = itemView.findViewById(R.id.img_type_notification);
            txt_task_description = itemView.findViewById(R.id.txt_task_description);
            txt_date = itemView.findViewById(R.id.txt_date_notification);
        }
    }

}

