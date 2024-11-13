package com.example.front.club;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.front.R;

import java.util.ArrayList;

public class ClubListActivity extends AppCompatActivity {

    // RecyclerView
    private ArrayList<ClubListData> arrayList;
    private ClubListAdapter clubListAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_club_list);

        recyclerView = findViewById(R.id.rv_club_list);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();
        clubListAdapter = new ClubListAdapter(arrayList);
        recyclerView.setAdapter(clubListAdapter);


        ClubListData clubListData = new ClubListData(R.drawable.basketball, "농구 동아리", "부원 상시 모집 중", "★ (0.0)");
        arrayList.add(clubListData);
        clubListData = new ClubListData(R.drawable.tennis, "테니스 동아리", "유일 테니스 동아리입니다.", "★ (4.9)");
        arrayList.add(clubListData);
        clubListAdapter.notifyDataSetChanged();


        Button btnBack = findViewById(R.id.btn_club_list_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}