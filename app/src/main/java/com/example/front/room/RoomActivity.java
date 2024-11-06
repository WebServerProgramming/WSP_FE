package com.example.front.room;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.front.R;
import com.example.front.login.LoginSelectionActivity;
import com.example.front.main.Main2Adapter;
import com.example.front.main.Main2Data;
import com.example.front.main.Main3Data;
import com.example.front.main.MainActivity;
import com.example.front.room.challenge.ChallengeActivity;
import com.example.front.room.challenge.ChallengeListActivity;

import java.util.ArrayList;

public class RoomActivity extends AppCompatActivity {

    // RecyclerView
    private ArrayList<RoomData> arrayList;
    private RoomAdapter roomAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    private ImageView ivRoomProgress;
    private ConstraintLayout clRoomProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);


        recyclerView = findViewById(R.id.rv_room);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();
        roomAdapter = new RoomAdapter(arrayList);
        recyclerView.setAdapter(roomAdapter);

        RoomData roomData = new RoomData("공지", "테스트용 공지");
        arrayList.add(roomData);
        arrayList.add(new RoomData("투표", "테스트용 투표"));
        arrayList.add(new RoomData("행사", "테스트용 행사"));
        arrayList.add(new RoomData("정기행사", "테스트용 정기행사"));
        roomAdapter.notifyDataSetChanged();

        ivRoomProgress = (ImageView) findViewById(R.id.iv_room_progress);
        clRoomProgress = (ConstraintLayout) findViewById(R.id.cl_room_progress);

        ivRoomProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clRoomProgress.getVisibility() == View.GONE) {
                    ivRoomProgress.setImageResource(R.drawable.baseline_keyboard_arrow_down_24);
                    clRoomProgress.setVisibility(View.VISIBLE);
                }
                else  {
                    ivRoomProgress.setImageResource(R.drawable.baseline_keyboard_arrow_up_24);
                    clRoomProgress.setVisibility(View.GONE);
                }

            }
        });

        Button btnFilter = findViewById(R.id.btn_room_filter);
        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RoomActivity.this, RoomReviewActivity.class);
                startActivity(intent);
            }
        });

        Button btnMenu = findViewById(R.id.btn_room_menu);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RoomActivity.this, ChallengeListActivity.class);
                startActivity(intent);
            }
        });
    }
}