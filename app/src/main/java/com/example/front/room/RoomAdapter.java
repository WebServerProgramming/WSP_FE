package com.example.front.room;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.front.R;

import java.util.ArrayList;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.CustomViewHolder>{

    private ArrayList<RoomData> arrayList;

    public RoomAdapter(ArrayList<RoomData> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public RoomAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_room_content, parent,false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RoomAdapter.CustomViewHolder holder, int position) {
        String category = arrayList.get(position).getTvRoomCategory();
        holder.tvRoomCategory.setText(category);

        int backgroundColor = Color.parseColor("#CCCCCC");
        switch (category) {
            case "공지":
                backgroundColor = Color.parseColor("#000000");
                break;
            case "투표":
                backgroundColor = Color.parseColor("#3C55EE");
                break;
            case "행사":
                backgroundColor = Color.parseColor("#FF7E3E");
                break;
            case "정기행사":
                backgroundColor = Color.parseColor("#30E3C8");
                break;
        }
        // ColorStateList 생성
        ColorStateList colorStateList = ColorStateList.valueOf(backgroundColor);
        holder.tvRoomCategory.setBackgroundTintList(colorStateList);



        holder.tvRoomContent.setText(arrayList.get(position).getTvContent());

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), NoticeActivity.class);
                view.getContext().startActivity(intent);
                //String curName = holder.tvClub.getText().toString();
                //Toast.makeText(view.getContext(), curName, Toast.LENGTH_SHORT).show();
            }
        });

        /**holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                return true;
                // return false;
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
        //return 0;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView tvRoomCategory;
        protected TextView tvRoomContent;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvRoomCategory = (TextView) itemView.findViewById(R.id.tv_item_room_content_category);
            this.tvRoomContent = (TextView) itemView.findViewById(R.id.tv_item_room_content_content);
        }
    }
}
