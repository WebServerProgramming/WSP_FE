package com.example.front.room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.front.R;

import java.util.ArrayList;

public class RoomVoteAdapter extends RecyclerView.Adapter<RoomVoteAdapter.CustomViewHolder> {

    private ArrayList<RoomVoteData> arrayList;

    private int selectedPosition = -1;
    public int selectedVoteId = -1;


    public RoomVoteAdapter(ArrayList<RoomVoteData> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public RoomVoteAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_room_vote, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RoomVoteAdapter.CustomViewHolder holder, int position) {
        String content = arrayList.get(position).getTvVoteContent();
        holder.tvVoteContent.setText(content);
        holder.rbVote.setChecked(position == selectedPosition);
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedPosition != position) {
                    selectedPosition = position;
                    selectedVoteId = arrayList.get(position).getVoteId();
                    notifyItemChanged(selectedPosition);
                }
                //Intent intent = new Intent(view.getContext(), RoomActivity.class);
                ///view.getContext().startActivity(intent);
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

        protected RadioButton rbVote;
        protected TextView tvVoteContent;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.rbVote = (RadioButton) itemView.findViewById(R.id.rb_item_room_vote);
            this.tvVoteContent = (TextView) itemView.findViewById(R.id.tv_item_room_vote_content);
        }
    }
}
