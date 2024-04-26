package com.isaura.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.isaura.R;
import com.isaura.model.AllActivities;

import java.util.ArrayList;

public class AllActivitiesAdapter extends RecyclerView.Adapter<AllActivitiesAdapter.AllActivitiesHolder> {

    private Context context;
    private ArrayList<AllActivities> allActivitiesArrayList;

    public AllActivitiesAdapter(Context context, ArrayList<AllActivities> allActivities) {
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
        AllActivities activitiy = allActivitiesArrayList.get(position);
        holder.setDetails(activitiy);
    }

    @Override
    public int getItemCount() {
        return allActivitiesArrayList.size();
    }

    class AllActivitiesHolder extends RecyclerView.ViewHolder {
        ImageView img_user_profile, img_item;
        TextView txt_username, txt_task_description, txt_date_activity;

        public AllActivitiesHolder(@NonNull View itemView) {
            super(itemView);
            img_user_profile = itemView.findViewById(R.id.img_user_profile);
            img_item = itemView.findViewById(R.id.img_item);
            txt_username = itemView.findViewById(R.id.txt_username);
            txt_task_description = itemView.findViewById(R.id.txt_task_description);
            txt_date_activity = itemView.findViewById(R.id.txt_date_activity);
        }

        void setDetails(AllActivities allActivities) {
            txt_username.setText(allActivities.getCleaning().getMember().getName());
            txt_date_activity.setText(allActivities.getCleaning().getDate());
        }

    }
}
