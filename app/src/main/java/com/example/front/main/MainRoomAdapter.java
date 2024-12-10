package com.example.front.main;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.front.R;
import com.example.front.room.RoomActivity;

import java.util.ArrayList;

public class MainRoomAdapter extends RecyclerView.Adapter<MainRoomAdapter.CustomViewHolder> {

    private ArrayList<MainRoomData> arrayList;

    public MainRoomAdapter(ArrayList<MainRoomData> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MainRoomAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_club, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainRoomAdapter.CustomViewHolder holder, int position) {
        holder.ivClub.setImageResource(arrayList.get(position).getIvClub());
        holder.tvClub.setText(arrayList.get(position).getTvClub());

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), RoomActivity.class);
                intent.putExtra("clubId", arrayList.get(position).getId());
                view.getContext().startActivity(intent);
                //String curName = holder.tvClub.getText().toString();
                //Toast.makeText(view.getContext(), curName, Toast.LENGTH_SHORT).show();
            }
        });

        /**holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
        @Override public boolean onLongClick(View view) {

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

        protected ImageView ivClub;
        protected TextView tvClub;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.ivClub = (ImageView) itemView.findViewById(R.id.iv_club);
            this.tvClub = (TextView) itemView.findViewById(R.id.tv_club);
        }
    }
}
