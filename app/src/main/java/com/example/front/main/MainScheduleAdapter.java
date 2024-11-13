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

import java.util.ArrayList;

public class MainScheduleAdapter extends RecyclerView.Adapter<MainScheduleAdapter.CustomViewHolder> {

    private ArrayList<MainScheduleData> arrayList;

    public MainScheduleAdapter(ArrayList<MainScheduleData> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MainScheduleAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_calendar, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainScheduleAdapter.CustomViewHolder holder, int position) {
        holder.ivCalendarList.setImageResource(arrayList.get(position).getIvCalendarList());
        holder.tvCalendarList.setText(arrayList.get(position).getTvCalendarList());

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainScheduleDetailActivity.class);
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

        protected ImageView ivCalendarList;
        protected TextView tvCalendarList;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.ivCalendarList = (ImageView) itemView.findViewById(R.id.iv_calendar_list);
            this.tvCalendarList = (TextView) itemView.findViewById(R.id.tv_calendar_list);
        }
    }
}
