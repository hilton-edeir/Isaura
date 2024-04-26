package com.isaura.activity.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
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
        //holder.txt_task_description.setText(notification.getDate());
        Glide.with(holder.img_task_type.getContext()).load(R.drawable.ic_utensils).placeholder(R.drawable.ic_utensils).error(R.drawable.ic_launcher_background).into(holder.img_task_type);
        Glide.with(holder.img_item_utensil.getContext()).load(R.drawable.ic_liquid_soap).placeholder(R.drawable.ic_liquid_soap).error(R.drawable.ic_launcher_background).into(holder.img_item_utensil);
        Glide.with(holder.img_type_notification.getContext()).load(R.drawable.ic_bell).placeholder(R.drawable.ic_bell).error(R.drawable.ic_launcher_background).into(holder.img_type_notification);
    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView img_task_type, img_item_utensil, img_type_notification;
        TextView txt_task_description, txt_date;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img_task_type = itemView.findViewById(R.id.img_type_task_notification);
            img_item_utensil = itemView.findViewById(R.id.img_item_utensil_notification);
            img_type_notification = itemView.findViewById(R.id.img_type_notification);
            txt_task_description = itemView.findViewById(R.id.txt_task_description);
            txt_date = itemView.findViewById(R.id.txt_date_notification);
        }
    }


    /*
    public NotificationAdapter(@NonNull FirebaseRecyclerOptions<Notification> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int i, @NonNull Notification notification) {
        holder.txt_date.setText(notification.getDate());
        holder.txt_task_description.setText(notification.getType());
        Glide.with(holder.img_task_type.getContext()).load(notification.getDate()).placeholder(R.drawable.ic_utensils).error(R.drawable.ic_launcher_background).into(holder.img_task_type);
        Glide.with(holder.img_item_utensil.getContext()).load(notification.getDate()).placeholder(R.drawable.ic_liquid_soap).error(R.drawable.ic_launcher_background).into(holder.img_item_utensil);
        Glide.with(holder.img_type_notification.getContext()).load(notification.getDate()).placeholder(R.drawable.ic_bell).error(R.drawable.ic_launcher_background).into(holder.img_type_notification);
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item, parent, false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder {

        ImageView img_task_type, img_item_utensil, img_type_notification;
        TextView txt_task_description, txt_date;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            img_task_type = itemView.findViewById(R.id.img_type_task_notification);
            img_item_utensil = itemView.findViewById(R.id.img_item_utensil_notification);
            img_type_notification = itemView.findViewById(R.id.img_type_notification);
            txt_task_description = itemView.findViewById(R.id.txt_task_description);
            txt_date = itemView.findViewById(R.id.txt_date_notification);
        }
    }

     */
}

