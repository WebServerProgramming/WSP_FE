package com.example.front.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.front.R;

import java.util.ArrayList;

public class RoomListActivity extends AppCompatActivity {

    // RecyclerView
    private ArrayList<Main3Data> arrayList;
    private Main3Adapter main3Adapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);

        recyclerView = findViewById(R.id.rv_room_list);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();
        main3Adapter = new Main3Adapter(arrayList);
        recyclerView.setAdapter(main3Adapter);


        Main3Data main3Data = new Main3Data(R.drawable.basketball,"농구 동아리", "부원 상시 모집 중");
        arrayList.add(main3Data);
        main3Data = new Main3Data(R.drawable.tennis, "테니스 동아리", "유일 테니스 동아리입니다.");
        arrayList.add(main3Data);
        main3Adapter.notifyDataSetChanged();


        Button btnRoomListback = findViewById(R.id.btn_room_list_back);
        btnRoomListback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RoomListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button btnRoomListNew = findViewById(R.id.btn_room_list_new);
        btnRoomListNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RoomListActivity.this, RoomCreateActivity.class);
                startActivity(intent);
            }
        });

    }
}