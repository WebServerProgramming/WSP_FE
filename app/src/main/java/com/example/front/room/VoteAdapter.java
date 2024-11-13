package com.example.front.room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.front.R;

import java.util.ArrayList;

public class VoteAdapter extends RecyclerView.Adapter<VoteAdapter.CustomViewHolder> {

    private ArrayList<VoteData> arrayList;

    public VoteAdapter(ArrayList<VoteData> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public VoteAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_room_vote, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull VoteAdapter.CustomViewHolder holder, int position) {
        String content = arrayList.get(position).getTvVoteContent();
        holder.tvVoteContent.setText(content);

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

        protected Button btnVoteRadio;
        protected TextView tvVoteContent;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.btnVoteRadio = (Button) itemView.findViewById(R.id.btn_item_room_vote_radio);
            this.tvVoteContent = (TextView) itemView.findViewById(R.id.tv_item_room_vote_content);
        }
    }
}
