package com.example.front.club;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.front.R;
import com.example.front.main.MainScheduleDetailActivity;

import java.util.ArrayList;

public class ClubReviewAdapter extends RecyclerView.Adapter<ClubReviewAdapter.CustomViewHolder> {

    private ArrayList<ClubReviewData> arrayList;

    public ClubReviewAdapter(ArrayList<ClubReviewData> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_club_review, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.tvContent.setText(arrayList.get(position).getContent());
        String starAdded = "★" + arrayList.get(position).getStar();
        holder.tvStar.setText(starAdded);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
        //return 0;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView tvContent;
        protected TextView tvStar;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvContent = (TextView) itemView.findViewById(R.id.tv_item_club_review_content);
            this.tvStar = (TextView) itemView.findViewById(R.id.tv_item_club_review_star);
        }
    }
}
