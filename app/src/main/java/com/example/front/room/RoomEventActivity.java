package com.example.front.room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.front.R;
import com.example.front.main.MainScheduleDetailAdapter;
import com.example.front.main.MainScheduleDetailData;

import java.util.ArrayList;

public class RoomEventActivity extends AppCompatActivity {

    // RecyclerView
    private ArrayList<MainScheduleDetailData> arrayList;
    private MainScheduleDetailAdapter mainScheduleDetailAdapter;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private static boolean alreadyHappened = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_room_event);
        Button btnBack = findViewById(R.id.btn_room_event_back);
        Button btnDone = findViewById(R.id.btn_room_event_done);

        recyclerView = findViewById(R.id.rv_room_event);
        gridLayoutManager = new GridLayoutManager(this, 4);
        recyclerView.setLayoutManager(gridLayoutManager);

        arrayList = new ArrayList<>();
        mainScheduleDetailAdapter = new MainScheduleDetailAdapter(arrayList);
        recyclerView.setAdapter(mainScheduleDetailAdapter);

        if(alreadyHappened) {
            MainScheduleDetailData mainScheduleDetailData = new MainScheduleDetailData("ㅊㅊㅎ");
            arrayList.add(mainScheduleDetailData);
            mainScheduleDetailAdapter.notifyDataSetChanged();
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!alreadyHappened) {
                    alreadyHappened = true;
                }
                finish();
            }
        });
    }
}