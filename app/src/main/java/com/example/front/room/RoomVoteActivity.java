package com.example.front.room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.front.R;

import java.util.ArrayList;

public class RoomVoteActivity extends AppCompatActivity {


    // RecyclerView
    private ArrayList<RoomVoteData> arrayList;
    private RoomVoteAdapter roomVoteAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_vote);


        recyclerView = findViewById(R.id.rv_room_vote);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();
        roomVoteAdapter = new RoomVoteAdapter(arrayList);
        recyclerView.setAdapter(roomVoteAdapter);

        arrayList.add(new RoomVoteData("투표"));
        arrayList.add(new RoomVoteData("행사"));
        arrayList.add(new RoomVoteData("정기행사"));
        roomVoteAdapter.notifyDataSetChanged();

        Button btnBack = findViewById(R.id.btn_room_vote_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}