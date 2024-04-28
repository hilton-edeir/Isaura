package com.isaura.activity.adapter;

import android.content.Context;
import android.content.res.Configuration;
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
import com.isaura.activity.fragment.SelectNotificationListener;
import com.isaura.model.Activity;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {

    private final Context context;
    private final List<Activity> activityList;
    private SelectNotificationListener listener;


    public NotificationAdapter(Context context, List<Activity> activityList, SelectNotificationListener listener) {
        this.context = context;
        this.activityList = activityList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NotificationAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.MyViewHolder holder, int position) {
        Activity activity = activityList.get(position);

        if(activity.getType() == 1) {
            holder.txt_task_description.setText("Pedido de reposição");
            Glide.with(holder.img_type_notification.getContext()).load(R.drawable.ic_bell).placeholder(R.drawable.ic_bell).error(R.drawable.ic_launcher_background).into(holder.img_type_notification);
            Glide.with(holder.img_task_type.getContext()).load(R.drawable.ic_utensils).placeholder(R.drawable.ic_utensils).error(R.drawable.ic_launcher_background).into(holder.img_task_type);

        }
        else {
            holder.txt_task_description.setText("Lembrete de limpeza");
            Glide.with(holder.img_type_notification.getContext()).load(R.drawable.ic_to_do).placeholder(R.drawable.ic_to_do).error(R.drawable.ic_launcher_background).into(holder.img_type_notification);
            Glide.with(holder.img_task_type.getContext()).load(R.drawable.ic_cleaning).placeholder(R.drawable.ic_cleaning).error(R.drawable.ic_launcher_background).into(holder.img_task_type);
        }
        holder.txt_date.setText(activity.getDate_created());
        Glide.with(holder.img_item_utensil.getContext()).load(R.drawable.ic_liquid_soap).placeholder(R.drawable.ic_liquid_soap).error(R.drawable.ic_launcher_background).into(holder.img_item_utensil);

        int nightModeFlags = context.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if(nightModeFlags == Configuration.UI_MODE_NIGHT_YES) {
            holder.card_item.setCardBackgroundColor(0);
        }
        else {
            holder.card_item.setCardBackgroundColor(context.getResources().getColor(R.color.pastel_purple));
        }
        holder.btn_do_task_notification.setOnClickListener(v -> listener.onItemClicked(activityList.get(position)));
    }

    @Override
    public int getItemCount() {
        return activityList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        CardView card_item, btn_do_task_notification;
        ImageView img_task_type, img_item_utensil, img_type_notification;
        TextView txt_task_description, txt_date;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            card_item = itemView.findViewById(R.id.card_item_notification);
            btn_do_task_notification = itemView.findViewById(R.id.btn_do_task_notification);
            img_task_type = itemView.findViewById(R.id.img_type_task_notification);
            img_item_utensil = itemView.findViewById(R.id.img_item_utensil_notification);
            img_type_notification = itemView.findViewById(R.id.img_type_notification);
            txt_task_description = itemView.findViewById(R.id.txt_task_description_notification);
            txt_date = itemView.findViewById(R.id.txt_date_notification);
        }
    }

}

