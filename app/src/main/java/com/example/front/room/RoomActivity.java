package com.example.front.room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.front.R;
import com.example.front.club.ClubReviewData;
import com.example.front.retrofit.RetrofitAPI;
import com.example.front.retrofit.RetrofitClientInstance;
import com.example.front.retrofit.ReviewResponse;
import com.example.front.retrofit.RoomResponse;
import com.example.front.room.challenge.ChallengeListActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoomActivity extends AppCompatActivity {

    // RecyclerView
    private ArrayList<RoomData> arrayList;
    private RoomAdapter roomAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    private ImageView ivRoomProgress;
    private ConstraintLayout clRoomProgress;

    private RoomData roomData;


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

        RetrofitAPI apiService = RetrofitClientInstance.getApiService(this);
        // 동아리 목록 조회 API 호출
        apiService.getRoom().enqueue(new Callback<RoomResponse>() {
            @Override
            public void onResponse(Call<RoomResponse> call, Response<RoomResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    RoomResponse roomResponse = response.body();

                    // 결과 로그 출력
                    Log.d("API Result", "Code: " + roomResponse.getResult().getCode());
                    Log.d("API Message", "Message: " + roomResponse.getResult().getMessage());

                    // 리뷰 목록 출력
                    for (RoomResponse.Payload payload : roomResponse.getPayload()) {
                        roomData = new RoomData("공지", payload.getTitle(), payload.getNoticeId());
                        arrayList.add(roomData);
                        Log.d("Review", "Title: " + payload.getTitle() + ", Id: " + payload.getNoticeId());
                    }
                    roomAdapter.notifyDataSetChanged();
                } else {
                    Log.e("API Error", "Response code: " + response.code());
                }
            }
            @Override
            public void onFailure(Call<RoomResponse> call, Throwable t) {
                Log.e("API Error", "Failure: " + t.getMessage());
            }
        });
        /*RoomData roomData = new RoomData("공지", "테스트용 공지");
        arrayList.add(roomData);
        arrayList.add(new RoomData("투표", "테스트용 투표"));
        arrayList.add(new RoomData("행사", "테스트용 행사"));
        arrayList.add(new RoomData("정기행사", "테스트용 정기행사"));
        roomAdapter.notifyDataSetChanged();*/

        ivRoomProgress = (ImageView) findViewById(R.id.iv_room_progress);
        clRoomProgress = (ConstraintLayout) findViewById(R.id.cl_room_progress);

        ivRoomProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clRoomProgress.getVisibility() == View.GONE) {
                    ivRoomProgress.setImageResource(R.drawable.baseline_keyboard_arrow_down_24);
                    clRoomProgress.setVisibility(View.VISIBLE);
                } else {
                    ivRoomProgress.setImageResource(R.drawable.baseline_keyboard_arrow_up_24);
                    clRoomProgress.setVisibility(View.GONE);
                }

            }
        });

        /*Button btnFilter = findViewById(R.id.btn_room_filter);
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
        });*/

        Button btnBack = findViewById(R.id.btn_room_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button btnRoomMenu = findViewById(R.id.btn_room_menu);
        btnRoomMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openOptionsMenu();
            }
        });


        Toolbar toolbar = findViewById(R.id.tb_room);  // 툴바 레이아웃에서 ID를 가져옵니다.
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.right_menu_room, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        if (item.getItemId() == R.id.menu_room_challenge) {
            intent = new Intent(RoomActivity.this, ChallengeListActivity.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.menu_room_review) {
            intent = new Intent(RoomActivity.this, RoomReviewActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

}