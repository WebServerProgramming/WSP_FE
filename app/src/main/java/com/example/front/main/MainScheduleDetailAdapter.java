package com.example.front.main;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.front.R;
import com.example.front.club.ClubRegistrationActivity;
import com.example.front.club.ClubReviewActivity;

import java.util.ArrayList;

public class MainScheduleDetailAdapter extends RecyclerView.Adapter<MainScheduleDetailAdapter.CustomViewHolder> {

    private ArrayList<MainScheduleDetailData> arrayList;

    public MainScheduleDetailAdapter(ArrayList<MainScheduleDetailData> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_schedule_detail, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.tvMainScheduleDetail.setText(arrayList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
        //return 0;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView tvMainScheduleDetail;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvMainScheduleDetail = itemView.findViewById(R.id.tv_item_main_schedule_detail);
        }
    }
}
