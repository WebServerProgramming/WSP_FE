package com.example.front.club;

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

import java.util.ArrayList;

public class ClubListAdapter extends RecyclerView.Adapter<ClubListAdapter.CustomViewHolder> {

    private ArrayList<ClubListData> arrayList;

    public ClubListAdapter(ArrayList<ClubListData> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ClubListAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_club_club, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ClubListAdapter.CustomViewHolder holder, int position) {
        holder.ivRoomListClub.setImageResource(arrayList.get(position).getIvRoomListClub());
        holder.tvRoomListName.setText(arrayList.get(position).getTvRoomListName());
        holder.tvRoomListIntroduction.setText(arrayList.get(position).getTvRoomListIntroduction());
        holder.tvRoomListStar.setText(arrayList.get(position).getTvRoomListStar());

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ClubReviewActivity.class);
                view.getContext().startActivity(intent);
                //String curName = holder.tvClub.getText().toString();
                //Toast.makeText(view.getContext(), curName, Toast.LENGTH_SHORT).show();
            }
        });
        holder.tvRoomListStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ClubReviewActivity.class);
                intent.putExtra("clubId", holder.getAdapterPosition());
                view.getContext().startActivity(intent);
            }
        });

        holder.btnRoomListAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ClubRegistrationActivity.class);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
        //return 0;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected ImageView ivRoomListClub;
        protected TextView tvRoomListName;
        protected TextView tvRoomListIntroduction;
        protected Button btnRoomListAdd;
        protected TextView tvRoomListStar;


        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.ivRoomListClub = (ImageView) itemView.findViewById(R.id.iv_item_club_club);
            this.tvRoomListName = (TextView) itemView.findViewById(R.id.tv_item_club_club_name);
            this.tvRoomListIntroduction = (TextView) itemView.findViewById(R.id.tv_item_club_club_introduction);
            this.btnRoomListAdd = (Button) itemView.findViewById(R.id.btn_item_club_list_add);
            this.tvRoomListStar = (TextView) itemView.findViewById(R.id.tv_item_club_club_star);
        }
    }
}
