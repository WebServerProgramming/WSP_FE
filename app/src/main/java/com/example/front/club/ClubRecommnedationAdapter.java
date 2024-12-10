package com.example.front.club;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.front.R;

import java.util.ArrayList;

public class ClubRecommnedationAdapter extends RecyclerView.Adapter<ClubRecommnedationAdapter.CustomViewHolder> {

    private ArrayList<ClubRecommendationData> arrayList;

    public ClubRecommnedationAdapter(ArrayList<ClubRecommendationData> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_club_recommendation, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.tvItemClubRecommendation.setText(arrayList.get(position).getContent());
        if (!arrayList.get(position).isQuery) {
            int backgroundColor = Color.parseColor("#FFFFFF");
            ColorStateList colorStateList = ColorStateList.valueOf(backgroundColor);
            holder.tvItemClubRecommendation.setBackgroundTintList(colorStateList);
            holder.llItemClubRecommendation.setGravity(Gravity.START);
        } else {
            int backgroundColor = Color.parseColor("#9CD1FF");
            ColorStateList colorStateList = ColorStateList.valueOf(backgroundColor);
            holder.tvItemClubRecommendation.setBackgroundTintList(colorStateList);
            holder.llItemClubRecommendation.setGravity(Gravity.END);
        }
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
        //return 0;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView tvItemClubRecommendation;
        protected LinearLayout llItemClubRecommendation;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvItemClubRecommendation = itemView.findViewById(R.id.tv_item_club_recommendation);
            this.llItemClubRecommendation = itemView.findViewById(R.id.ll_item_club_recommendation);
        }
    }
}
