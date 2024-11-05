package com.example.front.room;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.front.R;

import java.util.ArrayList;

public class VoteActivity extends AppCompatActivity {


    // RecyclerView
    private ArrayList<VoteData> arrayList;
    private VoteAdapter voteAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);


        recyclerView = findViewById(R.id.rv_vote);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();
        voteAdapter = new VoteAdapter(arrayList);
        recyclerView.setAdapter(voteAdapter);

        arrayList.add(new VoteData("투표"));
        arrayList.add(new VoteData("행사"));
        arrayList.add(new VoteData("정기행사"));
        voteAdapter.notifyDataSetChanged();
    }
}