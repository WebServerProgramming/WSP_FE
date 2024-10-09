package com.example.front.main;

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

public class Main3Adapter extends RecyclerView.Adapter<Main3Adapter.CustomViewHolder>{

    private ArrayList<Main3Data> arrayList;

    public Main3Adapter(ArrayList<Main3Data> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Main3Adapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_room_list, parent,false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Main3Adapter.CustomViewHolder holder, int position) {
        holder.ivRoomListClub.setImageResource(arrayList.get(position).getIvRoomListClub());
        holder.tvRoomListName.setText(arrayList.get(position).getTvRoomListName());
        holder.tvRoomListIntroduction.setText(arrayList.get(position).getTvRoomListIntroduction());

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(view.getContext(), RoomActivity.class);
                ///view.getContext().startActivity(intent);
                //String curName = holder.tvClub.getText().toString();
                //Toast.makeText(view.getContext(), curName, Toast.LENGTH_SHORT).show();
            }
        });

        holder.btnRoomListAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

        protected ImageView ivRoomListClub;
        protected TextView tvRoomListName;
        protected TextView tvRoomListIntroduction;
        protected Button btnRoomListAdd;


        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.ivRoomListClub = (ImageView) itemView.findViewById(R.id.iv_room_list_club);
            this.tvRoomListName = (TextView) itemView.findViewById(R.id.tv_room_list_name);
            this.tvRoomListIntroduction = (TextView) itemView.findViewById(R.id.tv_room_list_introduction);
            this.btnRoomListAdd = (Button) itemView.findViewById(R.id.btn_room_list_add);
        }
    }
}
